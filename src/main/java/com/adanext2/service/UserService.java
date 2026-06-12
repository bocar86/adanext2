package com.adanext2.service;

import com.adanext2.dto.request.CreateUserRequest;
import com.adanext2.dto.response.UserResponse;
import com.adanext2.exception.ResourceNotFoundException;
import com.adanext2.model.User;
import com.adanext2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé : " + request.getEmail());
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userRepository.save(user);
        return UserResponse.from(user);
    }

    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable : " + id));
        return UserResponse.from(user);
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll()
            .stream()
            .map(UserResponse::from)
            .collect(Collectors.toList());
    }
}
