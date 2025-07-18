package com.example.demo.services;

import com.example.model.User;
import com.example.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<User> findByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }


    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThtow(()) -> RuntimeException("Пользователь не найден"));
    }

    public User updateAvatar(Long userId, MultipartFile avatar) throws IOException {
        User user = getUserById(userId);
        String avatarFilename = fileStorageService.storeFile(avatar);
        user.setAvatar(avatarFilename);
        return userRepository.save(user);
    }

    public String saveAvatar(MultipartFile avatar) throws IOException {
        return fileStorageService.storeFile(avatar);
    }

    public List<User> getEmployers() {
        return userRepository.findByAccountType("работодатель");
    }

    public List<User> getApplicants() {
        return userRepository.findByAccountType("заявитель");
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
