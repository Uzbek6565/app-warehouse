package com.example.appwarehouse.entity;

import com.example.appwarehouse.entity.template.AbsEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Currency extends AbsEntity {

}
