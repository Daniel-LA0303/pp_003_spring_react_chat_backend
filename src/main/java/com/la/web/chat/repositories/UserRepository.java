package com.la.web.chat.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.la.web.chat.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

}
