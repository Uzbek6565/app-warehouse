package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.User;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.payload.UsersDto;
import com.example.appwarehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public Result addUser(@RequestBody UsersDto usersDto){
        return userService.addUser(usersDto);
    }

    @GetMapping("/{id}")
    public Result getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public Result editUser(@PathVariable Integer id, @RequestBody UsersDto usersDto){
        return userService.editUser(id, usersDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }
}
