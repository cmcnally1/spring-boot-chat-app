package com.cmcnally.jwdnd.c1.review.model;

public class ChatMessage {

    private int messageId;
    private String senderUsername;
    private String chatMessage;
    private String mode;


    /*
        ChatMessage constructor
     */
    public ChatMessage(String senderUsername, String chatMessage, String mode) {
        //this.messageId = messageId;
        this.senderUsername = senderUsername;
        this.chatMessage = chatMessage;
        this.mode = mode;
    }

    /*
        Getters and Setters
     */
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
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
