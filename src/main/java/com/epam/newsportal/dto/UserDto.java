package com.epam.newsportal.dto;

import com.epam.newsportal.domain.Role;

import javax.persistence.*;
import java.util.Set;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private boolean active;
    private Set<Role> roles;

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public boolean isAuthor() {
        return roles.contains(Role.AUTHOR);
    }
}
