package org.example.cropmonitoringsystem.service;

import org.example.cropmonitoringsystem.customObj.UserResponse;
import org.example.cropmonitoringsystem.dto.impl.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserResponse getSelectedUser(String email);
    void updateUser(String email, UserDTO userDTO);
    void deleteUser(String email);
}
