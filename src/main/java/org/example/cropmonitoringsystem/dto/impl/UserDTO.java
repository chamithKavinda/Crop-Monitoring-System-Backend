package org.example.cropmonitoringsystem.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cropmonitoringsystem.customObj.UserResponse;
import org.example.cropmonitoringsystem.dto.SuperDTO;
import org.example.cropmonitoringsystem.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements SuperDTO, UserResponse {
    private String email;
    private String password;
    private Role role;
}
