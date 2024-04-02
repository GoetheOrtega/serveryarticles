package com.example.spring_11200.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.spring_11200.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private Long id;
    private String email;
    private String firstname;
    private String lastname;


    public static UsersDto from(User user) {
        return UsersDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .build();
    }

    public static List<UsersDto> userList(List<User> users){
        return users.stream()
                .map(UsersDto::from)
                .collect(Collectors.toList());
    }
}