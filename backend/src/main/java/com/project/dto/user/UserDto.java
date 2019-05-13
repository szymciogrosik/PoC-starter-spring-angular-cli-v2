package com.project.dto.user;

import com.project.dto.user.role.RoleDto;
import com.project.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private Boolean banned;

    private String token;

    private List<RoleDto> roles = new ArrayList<>();

    public static UserDto parse(User user) {
        List<RoleDto> roles = user
                .getRoles()
                .stream()
                .map(RoleDto::parse)
                .collect(Collectors.toList());

        return UserDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password("")
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .banned(user.getBanned())
                .roles(roles)
                .build();
    }

}

