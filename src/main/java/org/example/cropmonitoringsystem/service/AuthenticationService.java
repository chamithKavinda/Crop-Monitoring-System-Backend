package org.example.cropmonitoringsystem.service;

import org.example.cropmonitoringsystem.jwtModels.SignIn;

public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse refreshToken(String accessToken);
}
