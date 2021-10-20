package com.cmcnally.jwdnd.c1.review;

import com.cmcnally.jwdnd.c1.review.PageObjects.ChatPage;
import com.cmcnally.jwdnd.c1.review.PageObjects.LoginPage;
import com.cmcnally.jwdnd.c1.review.PageObjects.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExpectedUserFlowTest {

    @LocalServerPort
    private Integer port;

    // Initialise web driver and page objects
    private static WebDriver driver;
    private ChatPage chatPage;
    private LoginPage loginPage;
    private SignupPage signupPage;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }


}
