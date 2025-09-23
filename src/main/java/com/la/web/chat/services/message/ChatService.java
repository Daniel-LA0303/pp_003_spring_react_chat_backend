package com.la.web.chat.services.message;

import com.la.web.chat.model.Message;
import com.la.web.chat.utils.dtos.chat.MessageRequestDTO;

public interface ChatService {

	Message sendMessage(String roomId, MessageRequestDTO messageRequestDTO);

}
