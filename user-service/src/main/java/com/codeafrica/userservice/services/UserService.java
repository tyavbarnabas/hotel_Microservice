package com.codeafrica.userservice.services;

import com.codeafrica.userservice.dtos.UserDto;
import com.codeafrica.userservice.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(Long id);

    User createUser(UserDto userDto);

    void DeleteUser(Long id);

    void updateUser(Long id, String name, String email, String address);
}
