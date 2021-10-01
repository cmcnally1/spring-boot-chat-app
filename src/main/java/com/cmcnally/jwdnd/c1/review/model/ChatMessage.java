package com.cmcnally.jwdnd.c1.review.model;

public class ChatMessage {

    private int messageid;
    private String username;
    private String messagetext;
    private String mode;


    /*
        ChatMessage constructor
     */
    public ChatMessage(String username, String messagetext, String mode) {
        //this.messageId = messageId;
        this.username = username;
        this.messagetext = messagetext;
        this.mode = mode;
    }

    /*
        Getters and Setters
     */
    public int getMessageid() {
        return messageid;
    }

    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessagetext() {
        return messagetext;
    }

    public void setMessagetext(String messagetext) {
        this.messagetext = messagetext;
    }
}
