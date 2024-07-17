package com.example.mybatis.service;

import com.example.mybatis.entity.User;
import com.example.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    //transaction management
    @Transactional
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    @Transactional
    public List<User> getUsersWithPagination(int offset, int limit) {
        return userMapper.getUsersWithPagination(offset, limit);
    }

    @Transactional
    public List<User> searchUsers(String firstName, String lastName, String email) {
        return userMapper.searchUsers(firstName, lastName, email);
    }

    @Transactional
    public void batchInsertUsers(List<User> users) {
        for (User user : users) {
            userMapper.insertUser(user);
        }
    }

    @Transactional
    public void batchUpdateUsers(List<User> users) {
        for (User user : users) {
            userMapper.updateUser(user);
        }
    }
}
