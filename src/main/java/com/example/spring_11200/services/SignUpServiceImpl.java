package com.example.spring_11200.services;

import com.example.spring_11200.dto.UserForm;
import com.example.spring_11200.models.Role;
import com.example.spring_11200.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.spring_11200.repositores.UsersRepository;


@Component
public class SignUpServiceImpl implements SignUpService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserForm form) {
        User user = User.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .firstName(form.getFirstname())
                .lastName(form.getLastname())
                .confirmed("CONFIRMED")
                .role(Role.USER)
                .build();
        usersRepository.save(user);
    }
}