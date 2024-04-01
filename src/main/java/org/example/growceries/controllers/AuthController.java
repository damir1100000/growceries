package org.example.growceries.controllers;

import org.example.growceries.models.user.PasswordResettingDTO;
import org.example.growceries.models.user.RegisteringUserDTO;
import org.example.growceries.models.user.UserDTO;
import org.example.growceries.security.JwtTokenProvider;
import org.example.growceries.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
        );
        return ResponseEntity.ok("Bearer " + tokenProvider.generateToken(authentication));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser (@RequestBody RegisteringUserDTO userDTO) {
        return ResponseEntity.ok(this.userService.createUser(userDTO));
    }

    @PutMapping("/recover")
    public ResponseEntity<Boolean> createNewPassword (@RequestBody PasswordResettingDTO passwordResettingDTO) {
        return ResponseEntity.ok(this.userService.resetPassword(passwordResettingDTO));
    }
}
