package org.example.cropmonitoringsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.cropmonitoringsystem.dto.impl.UserDTO;
import org.example.cropmonitoringsystem.exception.DataPersistFailedException;
import org.example.cropmonitoringsystem.jwtModels.JWTResponse;
import org.example.cropmonitoringsystem.jwtModels.SignIn;
import org.example.cropmonitoringsystem.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthenticationService authenticationService;
    static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @PostMapping(value = "signin")
    public ResponseEntity<JWTResponse> signIn(@RequestBody SignIn signIn) {
        logger.info("Sign-in successful for email: {}", signIn.getEmail());
        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }

    @PostMapping(value = "signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTResponse> signUp(@RequestBody UserDTO userDTO) {
        try {
            logger.info("Sign-up successful for email: {}", userDTO.getEmail());
            return ResponseEntity.ok(authenticationService.signUp(userDTO));
        }catch (DataPersistFailedException e){
            logger.error("Sign-up failed for email: {}. Error: {}", userDTO.getEmail(), e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error("Unexpected error during sign-up for email: {}. Error: {}", userDTO.getEmail(), e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("refresh")
    public ResponseEntity<JWTResponse> refreshToken (@RequestParam("refreshToken") String refreshToken) {
        logger.info("Token refresh successful.");
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}
