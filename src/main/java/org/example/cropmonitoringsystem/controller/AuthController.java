package org.example.cropmonitoringsystem.controller;

import org.example.cropmonitoringsystem.jwtModels.JWTResponse;
import org.example.cropmonitoringsystem.jwtModels.SignIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")

public class AuthController {
    @PostMapping(value = "signin")
    public ResponseEntity<JWTResponse> signIn(@RequestBody SignIn signIn) {
        return null;
    }
}
