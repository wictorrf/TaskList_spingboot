package com.example.listtasks.dtos;

import com.example.listtasks.domains.UserRole;

public record UserDto(String name, String email, String password, UserRole userRole) {
}
