package com.example.appwarehouse.entity;

import com.example.appwarehouse.entity.template.AbsEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Supplier extends AbsEntity {

    @Column(nullable = false,unique = true)
    private String phone_number;
}
