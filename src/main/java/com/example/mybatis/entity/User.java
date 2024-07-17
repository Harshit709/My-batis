package com.example.mybatis.entity;

import lombok.Data;


@Data
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;

}
