package com.tutorial.library.user;

import com.tutorial.library.exception.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("kullanici bulunamadi" + username));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
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
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

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
        existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User updatedUser = userRepository.save(existingUser);
        return toResponseDTO(updatedUser);
    }


    private UserResponseDTO toResponseDTO(User user){
        return new UserResponseDTO(user.getId(),user.getUsername(), user.getEmail());
    }
}