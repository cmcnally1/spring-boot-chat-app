package com.cmcnally.jwdnd.c1.review.model;

public class ChatMessage {

    private int messageId;
    private String senderUsername;
    private String chatMessage;
    private String mode;


    public ChatMessage(String senderUsername, String chatMessage, String mode) {
        this.senderUsername = senderUsername;
        this.chatMessage = chatMessage;
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }
}
