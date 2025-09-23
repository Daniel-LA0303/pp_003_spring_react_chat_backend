package com.la.web.chat.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.la.web.chat.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

	@Query("{ '$or': [ { 'username': { $regex: ?0, $options: 'i' } }, { 'email': { $regex: ?0, $options: 'i' } } ] }")
	List<User> searchUsers(String query);

}
