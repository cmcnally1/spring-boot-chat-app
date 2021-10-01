package com.cmcnally.jwdnd.c1.review.service;

import com.cmcnally.jwdnd.c1.review.mapper.UserMapper;
import com.cmcnally.jwdnd.c1.review.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    // Method to check that the entered username is not taken by another user
    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    // Method to create a user entry in the database via the user mapper
    public int createUser(User user) {

        // Generate a random salt for the new user and hash their password
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);

        // Insert the new user into the database via the mapper
        return userMapper.insert(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstname(), user.getLastname()));
    }

    // Method to retrieve the user from the database via the user mapper
    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}
