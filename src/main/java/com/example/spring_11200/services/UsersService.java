package com.example.spring_11200.services;

import com.example.spring_11200.dto.UsersDto;

import java.util.List;

public interface UsersService {
    List<UsersDto> getAllUsers();
}
