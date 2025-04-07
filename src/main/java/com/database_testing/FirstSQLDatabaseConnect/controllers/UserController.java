package com.database_testing.FirstSQLDatabaseConnect.controllers;

import com.database_testing.FirstSQLDatabaseConnect.domain.DTO.UserDTO;
import com.database_testing.FirstSQLDatabaseConnect.domain.entities.UserEntity;
import com.database_testing.FirstSQLDatabaseConnect.mappers.Mapper;
import com.database_testing.FirstSQLDatabaseConnect.services.UserService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private UserService userService;
    private Mapper<UserEntity, UserDTO> userMapper;

    public UserController(UserService userService, Mapper<UserEntity, UserDTO> userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PutMapping(path = "/users/{rfidcode}")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO, @PathVariable ("rfidcode") String rfidcode){
        if(userService.userExists(rfidcode)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userDTO.setRfidcode(rfidcode);
        UserEntity userEntity = userMapper.mapFrom(userDTO);
        userEntity = userService.createUser(userEntity,rfidcode);
        return new ResponseEntity<>(userMapper.mapTo(userEntity),HttpStatus.CREATED);
    }
    @GetMapping(path = "/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserEntity> users = userService.getAllUsers();
        return new ResponseEntity<>(users.stream().map(userMapper::mapTo).collect(Collectors.toList()),HttpStatus.OK);
    }
    @GetMapping(path = "/users/{rfidcode}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("rfidcode") String rfidcode){
        return userService.getUser(rfidcode).map(userEntity1 -> {
            return new ResponseEntity<>(userMapper.mapTo(userEntity1),HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping(path = "/users")
    public ResponseEntity<?> deleteAllUsers(){
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(path = "/users/{rfidcode}")
    public ResponseEntity<?> deleteUser(@PathVariable("rfidcode") String rfidcode){
        if(!userService.userExists(rfidcode)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.removeUser(rfidcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
