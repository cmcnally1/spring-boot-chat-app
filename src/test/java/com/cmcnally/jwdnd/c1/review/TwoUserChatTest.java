package com.cmcnally.jwdnd.c1.review;

import com.cmcnally.jwdnd.c1.review.PageObjects.ChatPage;
import com.cmcnally.jwdnd.c1.review.PageObjects.LoginPage;
import com.cmcnally.jwdnd.c1.review.PageObjects.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TwoUserChatTest {

    @LocalServerPort
    private Integer port;

    // Initialise web driver and page objects
    private static WebDriver driver;
    private ChatPage chatPage;
    private LoginPage loginPage;
    private SignupPage signupPage;

    // Initialise common test variables
    private String testFirstName = "Ciaran";
    private String testLastName = "McNally";
    private String testUsername = "cmcnally";
    private String testPassword = "CiArAnSpAsSwOrd";
    private String testMessage = "Test Message";


    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @Test
    public void testTwoUsersChat() {
        // Set up web driver and page object for test
        driver.get("http://localhost:" + port +"/signup");
        signupPage = new SignupPage(driver);

        // Fill out signup page text fields
        signupPage.setFirstName("testFirstName");
        signupPage.setLastName("testLastName");
        signupPage.setUsername("testUsername");
        signupPage.setPassword("testPassword");

        // Click submit
        signupPage.clickSubmit();

        // Get the login page
        driver.get("http://localhost:" + port +"/login");
        loginPage = new LoginPage(driver);

        // Set the username and passsword for first test user
        loginPage.setUsername("testUsername");
        loginPage.setPassword("testPassword");

        // Click submit
        loginPage.clickSubmit();

        // Get the chat page
        driver.get("http://localhost:" + port +"/chat");
        chatPage = new ChatPage(driver);

        // Write a chat message as the first user and submit
        chatPage.setMessageField("testMessage");
        chatPage.clickSubmit();

        // Logout as first user
        chatPage.clickLogout();

        // Get signup page again
        driver.get("http://localhost:" + port +"/signup");
        signupPage = new SignupPage(driver);

        // Fill out signup page text fields for second user
        signupPage.setFirstName("testFirstName2");
        signupPage.setLastName("testLastName2");
        signupPage.setUsername("testUsername2");
        signupPage.setPassword("testPassword2");

        // Click submit
        signupPage.clickSubmit();

        // Get login page again
        driver.get("http://localhost:" + port +"/login");
        loginPage = new LoginPage(driver);

        // Login as second user
        loginPage.setUsername("testUsername2");
        loginPage.setPassword("testPassword2");
        loginPage.clickSubmit();

        // Get chat page again
        driver.get("http://localhost:" + port +"/chat");
        chatPage = new ChatPage(driver);

        // Write a chat message as second user and submit
        chatPage.setMessageField("testMessage2");
        chatPage.clickSubmit();

        // Gather the chat messages and usernames displayed as lists for verifying
        List<String> messages = chatPage.getDisplayedMessages();
        List<String> usernames = chatPage.getDisplayedUsernames();

        // Check the first username is still displayed
        assertEquals("testUsername", usernames.get(0));

        // Check the first chat message is still displayed
        assertEquals("testMessage", messages.get(0));

        // Check the second username is also displayed
        assertEquals("testUsername2", usernames.get(1));

        // Check the second chat message is also displayed
        assertEquals("testMessage2", messages.get(1));
    }

}