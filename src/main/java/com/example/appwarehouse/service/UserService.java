package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.User;
import com.example.appwarehouse.entity.Warehouse;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.payload.UsersDto;
import com.example.appwarehouse.repository.UserRepository;
import com.example.appwarehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addUser(UsersDto usersDto) {
        if (userRepository.existsByPhoneNumber(usersDto.getPhoneNumber()))
            return new Result("This phone number is already used", false);
        User user = new User();
        user.setFirstName(usersDto.getFirstName());
        user.setLastName(usersDto.getLastName());
        user.setPassword(usersDto.getPassword());
        user.setPhoneNumber(usersDto.getPhoneNumber());
        Set<Warehouse> warehousesById = (Set<Warehouse>) warehouseRepository.findAllByIdIn(usersDto.getWarehouses_id());
        user.setWarehouseSet(warehousesById);
        userRepository.save(user);
        return new Result("User is added", true);
    }

    public Result getUserById(Integer id) {
        if (userRepository.existsById(id))
            return new Result("User with ID: " + id, true, userRepository.getById(id));
        return new Result("User not found", false);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Result editUser(Integer id, UsersDto usersDto) {
        if (!userRepository.existsById(id))
            return new Result("User not found", false);
        if (userRepository.existsByPhoneNumber(usersDto.getPhoneNumber()))
            return new Result("This phone number is already used", false);
        User userById = userRepository.getById(id);
        userById.setFirstName(usersDto.getFirstName());
        userById.setLastName(usersDto.getLastName());
        userById.setPassword(usersDto.getPassword());
        userById.setPhoneNumber(usersDto.getPhoneNumber());
        Set<Warehouse> warehousesById = (Set<Warehouse>) warehouseRepository.findAllByIdIn(usersDto.getWarehouses_id());
        userById.setWarehouseSet(warehousesById);
        userRepository.save(userById);
        return new Result("User data is edited", true);
    }

    public Result deleteUser(Integer id) {
        if (!userRepository.existsById(id))
            return new Result("User not found", false);
        userRepository.deleteById(id);
        return new Result("User is deleted", true);
    }
}
