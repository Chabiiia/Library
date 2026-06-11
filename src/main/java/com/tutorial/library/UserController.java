package com.tutorial.library;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){this.userService=userService;}

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserResponseDTO addUser(@Valid @RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return id + "id'li user silindi";
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id,@Valid @RequestBody UserDTO userDTO){
        return userService.updateUserById(id,userDTO);
    }


}

