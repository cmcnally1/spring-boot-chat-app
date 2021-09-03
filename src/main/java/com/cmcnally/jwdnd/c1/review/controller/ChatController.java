package com.cmcnally.jwdnd.c1.review.controller;

import com.cmcnally.jwdnd.c1.review.model.ChatForm;
import com.cmcnally.jwdnd.c1.review.model.ChatMessage;
import com.cmcnally.jwdnd.c1.review.service.MessageService;
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

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String getChatPage(@ModelAttribute("newChatMessage") ChatForm newChatMessage, Model model){
        model.addAttribute("chatMessages", this.messageService.getChatMessages());
        return "chat";
    }

    @PostMapping
    public String postChatMessage(@ModelAttribute("newChatMessage") ChatForm chatForm, Model model){
        messageService.addMessage(new ChatMessage(chatForm.getUsernameText(), chatForm.getMessageText(), chatForm.getModeSelection()));
        model.addAttribute("chatMessages", this.messageService.getChatMessages());
        chatForm.setMessageText("");
        chatForm.setUsernameText("");
        chatForm.setModeSelection("say");
        return "chat";
    }
}
