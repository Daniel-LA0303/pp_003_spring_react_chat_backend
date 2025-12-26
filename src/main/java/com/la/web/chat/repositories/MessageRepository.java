package com.la.web.chat.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.la.web.chat.model.Message;

public interface MessageRepository extends MongoRepository<Message, String> {

	@Query("{ 'roomId' : ?0 }")
	Page<Message> findByRoomId(String roomId, Pageable pageable);

	List<Message> findTop20ByRoomIdOrderByTimeStampDesc(String roomId); // opcional para últimos
}
