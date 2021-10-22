package com.cmcnally.jwdnd.c1.review;

import com.cmcnally.jwdnd.c1.review.service.MessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/*
	This application was created to review topics learned from an online course
	on Spring and Spring boot

	It is a simple chat application that allows users to:
		- Sign up and create an account
		- Log in using their credentials (Will block unknown credentials using Spring Security)
		- Write a chat message on a chat page
		- Log out of their account

	The application supports multiple users by using MyBatis to interact with a SQL database
 */
@SpringBootApplication
public class ReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);
	}


	@Bean
	public static String message(){
		System.out.println("message() Bean");
		return "Hello, Spring!";
	}

	@Bean
	public String uppercaseMessage(MessageService messageService){
		System.out.println("uppercaseMessage() Bean");
		return messageService.upperCase();
	}

	@Bean
	public String lowercaseMessage(MessageService messageService){
		System.out.println("lowercaseMessage() Bean");
		return messageService.lowerCase();
	}

}
