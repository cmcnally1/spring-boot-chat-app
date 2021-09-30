package com.cmcnally.jwdnd.c1.review.service;

import com.cmcnally.jwdnd.c1.review.mapper.MessageMapper;
import com.cmcnally.jwdnd.c1.review.mapper.UserMapper;
import com.cmcnally.jwdnd.c1.review.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {
    private UserMapper userMapper;
    private HashService hashService;

    public AuthenticationService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // variables to hold the username and password entered during login
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Get the user to match the username entered during login
        User user = userMapper.getUser(username);

        // Check the user exists
        if (user != null){
            // variables to hold the user salt and hashed password created using the user inputted password and salt
            String encodedSalt = user.getSalt();
            String hashedPassword = hashService.getHashedValue(password, encodedSalt);

            // Check the user's password is a match for the stored password
            if (user.getPassword().equals(hashedPassword)) {
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
