package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.User;
import com.example.appwarehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByPhoneNumber(Integer phoneNumber);

}
