package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    boolean existsByPhoneNumber(Integer phoneNumber);
}
