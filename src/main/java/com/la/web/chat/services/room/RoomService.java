package com.la.web.chat.services.room;

import java.util.List;

import com.la.web.chat.model.Room;
import com.la.web.chat.utils.exceptions.ServiceException;

public interface RoomService {

	Room createRoom(String roomId, String userId) throws ServiceException;

	Room getOrCreatePrivateRoom(String userId1, String userId2) throws ServiceException;

	List<Room> getRoomsByUserId(String userId) throws ServiceException;

	Room joinRoom(String roomId, String userId) throws ServiceException;

}
