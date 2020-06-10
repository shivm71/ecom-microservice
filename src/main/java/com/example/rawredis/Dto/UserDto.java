package com.example.rawredis.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private ArrayList<String> roles;
    private String email;

}
