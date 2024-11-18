package org.example.cropmonitoringsystem.service;

import org.example.cropmonitoringsystem.dto.impl.UserDTO;
import org.example.cropmonitoringsystem.jwtModels.JWTResponse;
import org.example.cropmonitoringsystem.jwtModels.SignIn;

public interface AuthenticationService {
    JWTResponse signIn(SignIn signIn);
    JWTResponse signUp(UserDTO userDTO);
    JWTResponse refreshToken(String accessToken);
}
