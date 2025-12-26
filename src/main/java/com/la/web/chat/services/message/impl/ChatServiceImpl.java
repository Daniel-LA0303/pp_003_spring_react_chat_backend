package com.la.web.chat.services.message.impl;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.la.web.chat.model.Message;
import com.la.web.chat.model.Room;
import com.la.web.chat.repositories.MessageRepository;
import com.la.web.chat.repositories.RoomRepository;
import com.la.web.chat.services.message.ChatService;
import com.la.web.chat.utils.dtos.chat.MessageRequestDTO;

@Service
public class ChatServiceImpl implements ChatService {

	private final RoomRepository roomRepository;
	private final MessageRepository messageRepository;

	public ChatServiceImpl(RoomRepository roomRepository, MessageRepository messageRepository) {
		this.roomRepository = roomRepository;
		this.messageRepository = messageRepository;
	}

	public Page<Message> getMessages(String roomId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("timeStamp").ascending());
		return messageRepository.findByRoomId(roomId, pageable);
	}

	@Override
	public Message sendMessage(String roomId, MessageRequestDTO messageRequestDTO) {
		Room room = roomRepository.findByRoomId(roomId).get();
		if (room == null) {
			throw new RuntimeException("Room not found!");
		}

		Message message = new Message();
		message.setRoomId(roomId);
		message.setContent(messageRequestDTO.getContent());
		message.setSender(messageRequestDTO.getSender());
		message.setTimeStamp(LocalDateTime.now());

		return messageRepository.save(message);
	}

}
