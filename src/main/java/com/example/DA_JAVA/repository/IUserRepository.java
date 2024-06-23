package com.example.DA_JAVA.repository;

import com.example.DA_JAVA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository  extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}