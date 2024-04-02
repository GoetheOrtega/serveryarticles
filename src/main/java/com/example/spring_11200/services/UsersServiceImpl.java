package com.example.spring_11200.services;

import com.example.spring_11200.dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.spring_11200.repositores.UsersRepository;

import java.util.List;

import static com.example.spring_11200.dto.UsersDto.userList;

@Component
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UsersDto> getAllUsers() {
        return userList(usersRepository.findAll());
    }
}