package com.example.listtasks.dtos;

import com.example.listtasks.domains.UserType;

public record UserDto(String name, String email, String password, UserType userType) {
}
