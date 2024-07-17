package com.example.mybatis.controller;


import com.example.mybatis.entity.User;
import com.example.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
    //pagination
    @GetMapping("/page")
    public List<User> getUsersWithPagination(@RequestParam int offset, @RequestParam int limit) {
        return userService.getUsersWithPagination(offset, limit);
    }
    //Dynamic Queries
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam(required = false) String firstName,
                                  @RequestParam(required = false) String lastName,
                                  @RequestParam(required = false) String email) {
        return userService.searchUsers(firstName, lastName, email);
    }
    //Batch Operations
    @PostMapping("/batch")
    public void batchInsertUsers(@RequestBody List<User> users) {
        userService.batchInsertUsers(users);
    }
    @PutMapping("/batch")
    public void batchUpdateUsers(@RequestBody List<User> users) {
        userService.batchUpdateUsers(users);
    }







}
