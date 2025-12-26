package com.la.web.chat.controllers.chat;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.la.web.chat.model.Message;
import com.la.web.chat.services.message.ChatService;
import com.la.web.chat.utils.dtos.chat.MessageRequestDTO;

@Controller
@CrossOrigin("http://localhost:5173")
public class ChatController {

	private final ChatService chatService;

	public ChatController(ChatService chatService) {
		this.chatService = chatService;
	}

	@MessageMapping("/sendMessage/{roomId}")
	@SendTo("/topic/room/{roomId}")
	public Message sendMessage(@DestinationVariable String roomId, MessageRequestDTO request) {
		return chatService.sendMessage(roomId, request);
	}
}