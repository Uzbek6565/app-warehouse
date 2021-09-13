package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
