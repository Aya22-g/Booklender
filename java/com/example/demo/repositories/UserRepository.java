package com.example.demo.repositories;

import com.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByNameContainingIgnoreCase(String name);
    Optional<User> findByPhone(String phone);
    List<User> findByAccountType(String accountType);
}
