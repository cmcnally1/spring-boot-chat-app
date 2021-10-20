package com.cmcnally.jwdnd.c1.review;

import com.cmcnally.jwdnd.c1.review.PageObjects.ChatPage;
import com.cmcnally.jwdnd.c1.review.PageObjects.LoginPage;
import com.cmcnally.jwdnd.c1.review.PageObjects.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
    This tests the following expected user flow:
        - Sign up and create a user
        - Login using credentials
        - Submit a chat message
        - Check that username and chat message is displayed

        NOTE: Tests must be run in order.
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpectedUserFlowTest {

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
    @Order(1)
    public void testSignUp() {
        // Set up web driver and page object for test
        driver.get("http://localhost:" + port +"/signup");
        signupPage = new SignupPage(driver);

        // Fill out signup page text fields
        signupPage.setFirstName(testFirstName);
        signupPage.setLastName(testLastName);
        signupPage.setUsername(testUsername);
        signupPage.setPassword(testPassword);

        // Click submit
        signupPage.clickSubmit();

        // Check that the user is successfully create by checking success message is displayed
        assertTrue(driver.findElement(By.id("success-msg")).isDisplayed());
    }

    @Test
    @Order(2)
    public void testLogin() {
        // Set up web driver and page object for test
        driver.get("http://localhost:" + port +"/login");
        loginPage = new LoginPage(driver);

        // Fill in login page text fields
        loginPage.setUsername(testUsername);
        loginPage.setPassword(testPassword);

        // Click login
        loginPage.clickSubmit();

        // Check that the chat page is loaded after successful login
        assertEquals("Chat Page", driver.getTitle());
    }

    @Test
    @Order(3)
    public void testChatMessage() {
        // Set up web driver and page object for test
        driver.get("http://localhost:" + port +"/chat");
        chatPage = new ChatPage(driver);

        // Fill in chat message field
        chatPage.setMessageField(testMessage);

        // Click the submit button
        chatPage.clickSubmit();

        // Check username displayed correctly
        assertEquals(testUsername, chatPage.getDisplayedUsername());

        // Check chat message displayed correctly
        assertEquals(testMessage, chatPage.getDisplayedMessage());
    }

}
