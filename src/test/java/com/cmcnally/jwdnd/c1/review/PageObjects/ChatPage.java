package com.cmcnally.jwdnd.c1.review.PageObjects;

import com.cmcnally.jwdnd.c1.review.model.ChatMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    private WebElement chatMessageDisplay;

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
    // Method to extract the message from the displayed username and message text
    public String getDisplayedMessage() {
        //Create a String variable to hold the full text displayed
        String fullMessage = chatMessageDisplay.getText();
        //Return a substring starting from the delimiter and ending at the end of message
        return fullMessage.substring(fullMessage.indexOf(": ")+2);
    }

    //Method to extract the username from the displayed username and message text
    public String getDisplayedUsername() {
        // Create a String variable to hold the full text displayed
        String fullMessage = chatMessageDisplay.getText();
        //Return a substring starting from the beginning and ending at the delimiter
        return fullMessage.substring(0, fullMessage.indexOf(": "));
    }
}
