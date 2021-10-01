package com.cmcnally.jwdnd.c1.review.controller;

import com.cmcnally.jwdnd.c1.review.model.ChatForm;
import com.cmcnally.jwdnd.c1.review.model.ChatMessage;
import com.cmcnally.jwdnd.c1.review.service.AuthenticationService;
import com.cmcnally.jwdnd.c1.review.service.MessageService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private MessageService messageService;
    // Authentication variable used to get information on the authenticated user
    private AuthenticationService authenticationService;

    // Constructor
    public ChatController(MessageService messageService, AuthenticationService authenticationService) {
        this.messageService = messageService;
        this.authenticationService = authenticationService;
    }

    // GET method to get the chat page
    @GetMapping
    public String getChatPage(@ModelAttribute("newChatMessage") ChatForm newChatMessage, Model model){
        model.addAttribute("chatMessages", this.messageService.getChatMessages());
        return "chat";
    }

    // POST method to handle a user posting a chat message
    @PostMapping
    public String postChatMessage(@ModelAttribute("newChatMessage") ChatForm chatForm, Model model){
        messageService.addMessage(new ChatMessage(authenticationService.getUsername(), chatForm.getMessageText(), chatForm.getModeSelection()));
        model.addAttribute("chatMessages", this.messageService.getChatMessages());
        chatForm.setMessageText("");
        chatForm.setUsernameText("");
        chatForm.setModeSelection("say");
        return "chat";
    }
}
