package com.company.etp.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.assertTrue;

public class LoginSteps {

    private WebDriver driver;


    @Before
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("http://localhost:8080/login");

        System.out.println("Login page opened");
    }


    @Given("the employee is on the login page")
    public void employeeIsOnLoginPage() {

        String currentUrl = driver.getCurrentUrl();

        assertTrue(
                currentUrl.contains("/login"),
                "Login page was not opened"
        );

        System.out.println("Employee is on login page");
    }


    @When("the employee enters username {string}")
    public void employeeEntersUsername(String username) {

        driver.findElement(By.id("username"))
                .sendKeys(username);

        System.out.println("Username entered: " + username);
    }


    @When("the employee enters password {string}")
    public void employeeEntersPassword(String password) {

        driver.findElement(By.id("password"))
                .sendKeys(password);

        System.out.println("Password entered");
    }


    @When("the employee clicks the Login button")
    public void employeeClicksLoginButton() {

        driver.findElement(
                By.cssSelector("button[type='submit']")
        ).click();

        System.out.println("Login button clicked");
    }


    @Then("the employee should see the dashboard")
    public void employeeShouldSeeDashboard() {

        String currentUrl = driver.getCurrentUrl();

        System.out.println(
                "Current URL: " + currentUrl
        );

        assertTrue(
                currentUrl.contains("/dashboard"),
                "Dashboard was not opened"
        );

        System.out.println(
                "Employee login BDD test PASSED"
        );
    }


    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }

        System.out.println("Browser closed");
    }
}