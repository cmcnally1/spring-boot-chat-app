package com.cmcnally.jwdnd.c1.review.controller;

import com.cmcnally.jwdnd.c1.review.model.User;
import com.cmcnally.jwdnd.c1.review.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
    Login controller to get the signup view and handle user input when signing up
 */
@Controller
@RequestMapping("/signup")
public class SignupController {

    // User service variable used to when creating user
    private final UserService userService;

    // Constructor
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    // Method to get the signup view
    @GetMapping
    public String signupView() {
        return "signup";
    }

    // Method to handle the PUT request from the user when signing up
    @PostMapping
    public String signupUser(@ModelAttribute User user, Model model) {
        // Variable to hold an error message to display
        String signupError = null;

        // Check if username is available. If it is not, set an error to be displayed.
        if (!userService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists. Please choose another.";
        }

        // Check if an error has occurred already. If not, proceed to create the user.
        if (signupError == null) {
            int rowsAdded = userService.createUser(user);

            // Check if user was created. If not, set an error to be displayed
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        // Check if an error has occurred.
        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}
