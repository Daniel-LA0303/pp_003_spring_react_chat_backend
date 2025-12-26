package com.la.web.chat.controllers.chat;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.la.web.chat.model.Message;
import com.la.web.chat.model.Room;
import com.la.web.chat.repositories.RoomRepository;
import com.la.web.chat.services.message.impl.ChatServiceImpl;
import com.la.web.chat.services.room.impl.RoomServiceImpl;
import com.la.web.chat.utils.dtos.room.CreateRoomRequest;
import com.la.web.chat.utils.dtos.room.JoinRoomRequest;
import com.la.web.chat.utils.exceptions.ServiceException;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("http://localhost:5173")
public class RoomController {

	private final RoomRepository roomRepository;
	private final ChatServiceImpl chatService;
	private final RoomServiceImpl roomService;

	public RoomController(RoomRepository roomRepository, ChatServiceImpl chatService, RoomServiceImpl roomService) {
		this.roomRepository = roomRepository;
		this.chatService = chatService;
		this.roomService = roomService;
	}

	@PostMapping
	public ResponseEntity<?> createRoom(@RequestBody CreateRoomRequest request) throws ServiceException {
		try {

			System.out.println("user: " + request.getUserId() + "\n room:" + request.getRoomId());
			Room room = roomService.createRoom(request.getRoomId(), request.getUserId());
			return ResponseEntity.status(HttpStatus.CREATED).body(room);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{roomId}/messages")
	public ResponseEntity<?> getMessages(@PathVariable String roomId, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size) {
		Page<Message> messages = chatService.getMessages(roomId, page, size);
		return ResponseEntity.ok(messages.getContent());
	}

	@PostMapping("/private")
	public ResponseEntity<?> getOrCreatePrivateRoom(@RequestParam String userId1, @RequestParam String userId2) {

		try {
			Room room = roomService.getOrCreatePrivateRoom(userId1, userId2);
			return ResponseEntity.ok(room);
		} catch (ServiceException e) {
			// return ResponseEntity.status(e.getStatusCode())
			// .body(new ErrorResponse(e.getMessage(), e.getPath(), e.getMethod()));
		}
		return null;
	}

	@GetMapping("/by-user")
	public ResponseEntity<?> getRoomsByUser(@RequestParam String userId) throws ServiceException {
		try {
			System.out.println("Fetching rooms for user: " + userId);
			List<Room> rooms = roomService.getRoomsByUserId(userId);
			return ResponseEntity.ok(rooms);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/{roomId}/join")
	public ResponseEntity<?> joinRoom(@PathVariable String roomId, @RequestBody JoinRoomRequest request)
			throws ServiceException {

		try {

			System.out.println("room: " + roomId + "\n userId: " + request.getUserId());
			Room room = roomService.joinRoom(roomId, request.getUserId());
			return ResponseEntity.ok(room);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}