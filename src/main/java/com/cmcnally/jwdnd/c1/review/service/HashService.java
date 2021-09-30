package com.cmcnally.jwdnd.c1.review.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/*
    HashService used to hash user's passwords
 */
@Component
public class HashService {
    // Logger used to log an error thrown during hashing
    private Logger logger = LoggerFactory.getLogger(HashService.class);

    // Method to create a hashed password given the password data and a salt for user
    public String getHashedValue(String data, String salt) {
        byte[] hashedValue = null;

        // PBE key specification for the user password
        KeySpec spec = new PBEKeySpec(data.toCharArray(), salt.getBytes(), 5000, 128);
        try {
            // Hash the user password
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hashedValue = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        }

        // return the user's hashed password
        return Base64.getEncoder().encodeToString(hashedValue);
    }
}
