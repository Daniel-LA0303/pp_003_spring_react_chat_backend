package com.la.web.chat.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import com.la.web.chat.model.Room;

public interface RoomRepository extends MongoRepository<Room, String> {

	// Agrega un usuario a la sala (operación atómica optimizada)
	@Query("{ 'roomId': ?0 }")
	@Update("{ $addToSet: { users: { $ref: 'users', $id: ?1 } } }") // Usando DBRef explícito
	void addUserToRoom(String roomId, ObjectId userId);

	// Nueva consulta útil: Cuenta usuarios en una sala
	@Query(value = "{ 'roomId': ?0 }", count = true)
	long countUsersInRoom(String roomId);

	// Verifica si existe una sala por roomId (nueva adición importante)
	boolean existsByRoomId(String roomId);

	// Obtiene una sala por roomId (versión mejorada)
	Optional<Room> findByRoomId(String roomId);

	@Query("{ $and: [" + "{ 'users': { $size: 2 } }," + "{ 'users.userId': ?0 }," + "{ 'users.userId': ?1 }" + "] }")
	Optional<Room> findPrivateRoomBetweenUsers(String userId1, String userId2);

	// Encuentra todas las salas de un usuario (versión mejorada)
	@Query("{ 'users.$id': ?0 }")
	List<Room> findRoomsByUser(ObjectId userId);

	// Verifica si un usuario está en una sala (versión corregida)
	@Query(value = "{ 'roomId': ?0, 'users._id': ?1 }", exists = true)
	boolean isUserInRoom(String roomId, ObjectId userId);
}