package com.cmcnally.jwdnd.c1.review.service;

import com.cmcnally.jwdnd.c1.review.mapper.MessageMapper;
import com.cmcnally.jwdnd.c1.review.model.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    public String message;
    public List<ChatMessage> chatMessages;
    private MessageMapper messageMapper;

//    public MessageService(String message) {
//        this.message = message;
//    }

//    @PostConstruct
//    public void postContstruct(){
//        this.message = new String();
//        this.chatMessages = new ArrayList<>();
//        this.messageMapper = messageMapper;
//    }


    public MessageService(String message, List<ChatMessage> chatMessages, MessageMapper messageMapper) {
        this.message = message;
        this.chatMessages = chatMessages;
        this.messageMapper = messageMapper;
    }

    public void addMessage(ChatMessage message) {
        ChatMessage finalMessage = message;

        String mode = message.getMode();

        if(mode.equals("shout")){
            finalMessage.setMessagetext(message.getMessagetext().toUpperCase());
        } else if (mode.equals("whisper")) {
            finalMessage.setMessagetext(message.getMessagetext().toLowerCase());
        }

        messageMapper.insert(finalMessage);
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
        return new ArrayList<>(messageMapper.getChatMessage());
    }
}
