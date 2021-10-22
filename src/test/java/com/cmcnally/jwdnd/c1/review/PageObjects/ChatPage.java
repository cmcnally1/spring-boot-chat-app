package com.cmcnally.jwdnd.c1.review.PageObjects;

import com.cmcnally.jwdnd.c1.review.model.ChatMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ChatPage {

    @FindBy(id = "messageText")
    private WebElement messageField;

    @FindBy(id = "messageType")
    private WebElement messageTypeSelection;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "chat-messages")
    private List<WebElement> chatMessageDisplay;

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickSubmit(){
        submitButton.click();
    }

    public void clickLogout() {
        logoutButton.click();
    }

    public void setMessageField(String message) {
        messageField.sendKeys(message);
    }

    /*
        TODO: Test the use of the below methods.
         The below messages may only work if one message is displayed.
     */
    // Method to extract the messages from the displayed username and message text
    public List<String> getDisplayedMessages() {

        List<String> messages = new ArrayList<>();

        for (int i = 0; i < chatMessageDisplay.size(); i++){
            //Create a String variable to hold the full text displayed
            String fullMessage = chatMessageDisplay.get(i).getText();
            //Add a substring starting from the delimiter and ending at the end of message
            messages.add(fullMessage.substring(fullMessage.indexOf(": ")+2));
        }
        //Return the list of messages
        return messages;
    }

    //Method to extract the usernames from the displayed username and message text
    public List<String> getDisplayedUsernames() {

        List<String> usernames = new ArrayList<>();

        for (int i = 0; i < chatMessageDisplay.size(); i++){
            //Create a String variable to hold the full text displayed
            String fullMessage = chatMessageDisplay.get(i).getText();
            //Add a substring starting from the beginning and ending at the delimiter
            usernames.add(fullMessage.substring(0, fullMessage.indexOf(": ")));
        }
        //Return the list of usernames
        return usernames;
    }
}
