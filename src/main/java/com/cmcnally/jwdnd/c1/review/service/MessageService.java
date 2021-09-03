package com.cmcnally.jwdnd.c1.review.service;

import com.cmcnally.jwdnd.c1.review.model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    public String message;
    public List<ChatMessage> chatMessages;

//    public MessageService(String message) {
//        this.message = message;
//    }

    @PostConstruct
    public void postContstruct(){
        this.message = new String();
        this.chatMessages = new ArrayList<>();
    }

    public void addMessage(ChatMessage message) {
        ChatMessage finalMessage = message;

        String mode = message.getMode();

        if(mode.equals("shout")){
            finalMessage.setChatMessage(message.getChatMessage().toUpperCase());
        } else if (mode.equals("whisper")) {
            finalMessage.setChatMessage(message.getChatMessage().toLowerCase());
        }

        chatMessages.add(finalMessage);
    }

    public String upperCase(){
        System.out.println("MessageService upperCase()");
        return this.message.toUpperCase();
    }

    public String lowerCase(){
        System.out.println("MessageService lowerCase()");
        return this.message.toLowerCase();
    }

    public List<ChatMessage> getChatMessages() {
        return new ArrayList<>(this.chatMessages);
    }
}
