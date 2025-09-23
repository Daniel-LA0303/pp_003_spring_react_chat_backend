package com.la.web.chat.controllers.chat;

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

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("http://localhost:5173")
public class RoomController {

	private final RoomRepository roomRepository;
	private final ChatServiceImpl chatService;

	public RoomController(RoomRepository roomRepository, ChatServiceImpl chatService) {
		this.roomRepository = roomRepository;
		this.chatService = chatService;
	}

	@PostMapping
	public ResponseEntity<?> createRoom(@RequestBody String roomId) {
		if (roomRepository.findByRoomId(roomId) != null) {
			return ResponseEntity.badRequest().body("Room already exists!");
		}

		Room room = new Room();
		room.setRoomId(roomId);
		return ResponseEntity.status(HttpStatus.CREATED).body(roomRepository.save(room));
	}

	@GetMapping("/{roomId}/messages")
	public ResponseEntity<?> getMessages(@PathVariable String roomId, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size) {
		Page<Message> messages = chatService.getMessages(roomId, page, size);
		return ResponseEntity.ok(messages.getContent());
	}

	@GetMapping("/{roomId}")
	public ResponseEntity<?> joinRoom(@PathVariable String roomId) {
		Room room = roomRepository.findByRoomId(roomId);
		return room == null ? ResponseEntity.badRequest().body("Room not found!") : ResponseEntity.ok(room);
	}
}