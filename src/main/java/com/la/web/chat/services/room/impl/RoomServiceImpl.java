package com.la.web.chat.services.room.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.la.web.chat.model.Room;
import com.la.web.chat.model.User;
import com.la.web.chat.repositories.RoomRepository;
import com.la.web.chat.repositories.UserRepository;
import com.la.web.chat.services.room.RoomService;
import com.la.web.chat.utils.constants.PathsConstants;
import com.la.web.chat.utils.enums.MethodEnum;
import com.la.web.chat.utils.exceptions.ServiceException;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Room createRoom(String roomId, String userId) throws ServiceException {
		// Verificar si la sala ya existe (CONFLICT 409)
		if (roomRepository.existsByRoomId(roomId)) {
			throw new ServiceException("Room with ID " + roomId + " already exists", HttpStatus.CONFLICT.value(),
					PathsConstants.PATH_AUTH, MethodEnum.POST);
		}

		// Obtener usuario (NOT_FOUND 404)
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ServiceException("User with ID " + userId + " not found",
						HttpStatus.NOT_FOUND.value(), PathsConstants.PATH_USER, MethodEnum.GET));

		// Crear y guardar la sala
		Room room = new Room();
		room.setRoomId(roomId);
		room.getUsers().add(user);

		return roomRepository.save(room);
	}

	@Override
	@Transactional
	public Room getOrCreatePrivateRoom(String userId1, String userId2) throws ServiceException {
		// 1. Validar que ambos usuarios existen
		if (!userRepository.existsById(userId1)) {
			throw new ServiceException("User with ID " + userId1 + " not found", HttpStatus.NOT_FOUND.value(),
					PathsConstants.PATH_USER, MethodEnum.GET);
		}

		if (!userRepository.existsById(userId2)) {
			throw new ServiceException("User with ID " + userId2 + " not found", HttpStatus.NOT_FOUND.value(),
					PathsConstants.PATH_USER, MethodEnum.GET);
		}

		// 2. Buscar sala existente (orden inverso para cubrir ambas combinaciones)
		Optional<Room> existingRoom = roomRepository.findPrivateRoomBetweenUsers(userId1, userId2);

		if (existingRoom.isPresent()) {
			return existingRoom.get();
		}

		// 3. Crear nueva sala privada
		String roomId = generatePrivateRoomId(userId1, userId2);

		// Verificar que no exista (aunque es muy improbable)
		if (roomRepository.existsByRoomId(roomId)) {
			throw new ServiceException("Room already exists", HttpStatus.CONFLICT.value(), PathsConstants.PATH_AUTH,
					MethodEnum.POST);
		}

		// Crear usuarios referencia
		User userRef1 = new User();
		userRef1.setUserId(userId1);

		User userRef2 = new User();
		userRef2.setUserId(userId2);

		// Crear y guardar sala
		Room newRoom = new Room();
		newRoom.setRoomId(roomId);
		newRoom.getUsers().add(userRef1);
		newRoom.getUsers().add(userRef2);

		return roomRepository.save(newRoom);
	}

	@Override
	public List<Room> getRoomsByUserId(String userId) throws ServiceException {
		try {
			ObjectId objectUserId = new ObjectId(userId); // Conversión segura
			List<Room> rooms = roomRepository.findRoomsByUser(objectUserId);

			if (rooms.isEmpty()) {
				throw new ServiceException("No rooms found for user", HttpStatus.NOT_FOUND.value(),
						PathsConstants.PATH_AUTH, MethodEnum.GET);
			}

			return rooms;
		} catch (IllegalArgumentException e) {
			throw new ServiceException("Invalid user ID format", HttpStatus.BAD_REQUEST.value(),
					PathsConstants.PATH_USER, MethodEnum.GET);
		}
	}

	@Override
	@Transactional
	public Room joinRoom(String roomId, String userId) throws ServiceException {
		try {
			// 1. Validar que el usuario existe (sin cargarlo completo)
			if (!userRepository.existsById(userId)) {
				throw new ServiceException("User not found", HttpStatus.NOT_FOUND.value(), PathsConstants.PATH_USER,
						MethodEnum.GET);
			}

			// 2. Buscar la sala
			Room room = roomRepository.findByRoomId(roomId).orElseThrow(() -> new ServiceException("Room not found",
					HttpStatus.NOT_FOUND.value(), PathsConstants.PATH_AUTH, MethodEnum.GET));

			// 3. Verificar si el usuario ya está en la sala
			boolean userInRoom = room.getUsers().stream().anyMatch(u -> u.getUserId().equals(userId));

			if (!userInRoom) {
				// 4. Crear solo la referencia (DBRef) con el ID del usuario
				User userRef = new User();
				userRef.setUserId(userId); // Solo establecemos el ID como String

				// 5. Agregar la referencia a la sala
				room.getUsers().add(userRef);
				roomRepository.save(room);
			}

			return room;
		} catch (Exception e) {
			throw new ServiceException("Error joining room: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(), PathsConstants.PATH_AUTH, MethodEnum.POST);
		}
	}

	private String generatePrivateRoomId(String userId1, String userId2) {
		// Ordenamos los IDs para garantizar consistencia
		String[] ids = { userId1, userId2 };
		Arrays.sort(ids);

		// Generamos un hash único basado en los IDs ordenados
		String combined = ids[0] + "_" + ids[1];
		return "private_" + DigestUtils.md5DigestAsHex(combined.getBytes()).substring(0, 10);
	}
}
