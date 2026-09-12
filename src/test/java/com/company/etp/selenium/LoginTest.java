package com.company.etp.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTest {
    // Selenium object that controls the browser.
    private WebDriver driver;


    // ==========================================
    // BEFORE EACH TEST
    // ==========================================

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("http://localhost:8080/login");
    }


    // ==========================================
    // LOGIN TEST
    // ==========================================

    @Test
    public void validEmployeeLoginTest() {

        // Enter username
        driver.findElement(By.id("username"))
                .sendKeys("employee1");

        // Enter password
        driver.findElement(By.id("password"))
                .sendKeys("Employee@123");


        // Click Login
        driver.findElement(By.cssSelector("button[type='submit']"))
                .click();


        // Get current URL after login
        String currentUrl = driver.getCurrentUrl();

        System.out.println(
                "Current URL: " + currentUrl
        );


        // Verify that dashboard is opened
        assertTrue(
                currentUrl.contains("dashboard")
                        || currentUrl.endsWith("/"),
                "Login was not successful"
        );

        System.out.println(
                "Employee login test PASSED"
        );
    }


    // ==========================================
    // AFTER EACH TEST
    // ==========================================

    @AfterMethod
    public void tearDown() {

//        if (driver != null) {
//            driver.quit();
//        }
        System.out.println("Test completed");
    }
}