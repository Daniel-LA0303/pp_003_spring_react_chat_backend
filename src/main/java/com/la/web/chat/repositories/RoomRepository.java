package com.la.web.chat.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.la.web.chat.model.Room;

public interface RoomRepository extends MongoRepository<Room, String> {
	// get room using room id
	Room findByRoomId(String roomId);
}