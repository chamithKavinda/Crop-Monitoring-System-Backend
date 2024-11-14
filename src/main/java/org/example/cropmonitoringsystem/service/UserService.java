package org.example.cropmonitoringsystem.service;

import org.example.cropmonitoringsystem.dto.impl.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
}
