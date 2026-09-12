package com.company.etp.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class LocatorTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("http://localhost:8080/login");
    }

    @Test
    public void findUsernameField() {

        var usernameField =
                driver.findElement(By.id("username"));

        assertNotNull(usernameField);

        System.out.println(
                "Username field found successfully"
        );
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}