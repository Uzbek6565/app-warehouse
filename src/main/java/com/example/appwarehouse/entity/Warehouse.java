package com.example.appwarehouse.entity;

import com.example.appwarehouse.entity.template.AbsEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Warehouse extends AbsEntity {

}
