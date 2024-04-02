package com.example.spring_11200.repositores;


import com.example.spring_11200.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fasterxml.jackson.annotation.OptBoolean;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}