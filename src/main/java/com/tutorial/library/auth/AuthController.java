package com.tutorial.library.auth;

import com.tutorial.library.security.JwtUtil;
import com.tutorial.library.user.UserDTO;
import com.tutorial.library.user.UserResponseDTO;
import com.tutorial.library.user.UserService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private UserService userService;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    public AuthController(UserService userService,JwtUtil jwtUtil,AuthenticationManager authenticationManager){
        this.userService=userService;
        this.jwtUtil=jwtUtil;
        this.authenticationManager=authenticationManager;
    }

    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );
        String token =jwtUtil.generateToken(request.getUsername());
        return new AuthResponse(token);
    }
}
