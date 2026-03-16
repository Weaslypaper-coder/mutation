package com.example.mutation.service;

import com.example.mutation.entity.User;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    @PostConstruct
    public void initDefaultUser() {
        users.add(new User(1L, "admin", "123456"));
    }

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    public User create(User user) {
        long nextId = users.stream().mapToLong(User::getId).max().orElse(0L) + 1;
        user.setId(nextId);
        users.add(user);
        return user;
    }

    public void delete(Long id) {
        users.removeIf(u -> u.getId().equals(id));
    }
}

