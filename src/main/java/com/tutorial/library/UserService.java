package com.tutorial.library;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public UserResponseDTO saveUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        User savedUser = userRepository.save(user);
        return toResponseDTO(savedUser);
    }

    public UserResponseDTO getUserById(Long id){
        User user = userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(id + "idli user bulunamadi"));
        return toResponseDTO(user);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public UserResponseDTO updateUserById(Long id , UserDTO userDTO){
        User existingUser = userRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException(id + "id'li user bulunamadi"));
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPassword(userDTO.getPassword());

        User updatedUser = userRepository.save(existingUser);
        return toResponseDTO(updatedUser);
    }


    private UserResponseDTO toResponseDTO(User user){
        return new UserResponseDTO(user.getId(),user.getUsername(), user.getEmail());
    }
}