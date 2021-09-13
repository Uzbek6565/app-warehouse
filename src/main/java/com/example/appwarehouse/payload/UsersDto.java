package com.example.appwarehouse.payload;

import lombok.Data;

import java.util.List;

@Data
public class UsersDto {
    private String firstName;
    private String lastName;
    private Integer phoneNumber;
    private String code;
    private String password;
    private List<Integer> warehouses_id;
}
