package com.cliniquems.userservice.repository;

import com.cliniquems.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByCode(String email);
    boolean existsByEmail(String email);
    void deleteByEmail(String email);
}