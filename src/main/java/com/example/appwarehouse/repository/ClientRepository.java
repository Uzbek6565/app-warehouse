package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    boolean existsByNameAndPhoneNumber(String name, Integer phoneNumber);
}
