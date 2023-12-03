package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;

public class Task {
    WebDriver driver = new ChromeDriver();
    @BeforeTest
    void setup() {
        // To intiate the chrome driver
        System.setProperty("webdriver.chrome.driver", "C://Users//areej//Downloads//chromedriver-win64");
        driver.manage().window().maximize();
        // To open the portal
        driver.get("https://www.google.com");
    }

    @Test
    public void Login() {
        driver.get("https://oyn-adminportal-qc-demo.salmonsky-1edff179.westeurope.azurecontainerapps.io/");
        // Get element locators
        WebElement Email = driver.findElement(By.id("mat-input-0"));
        WebElement Password = driver.findElement(By.id("mat-input-1"));
        WebElement SignIn = driver.findElement(By.xpath("/html/body/app-root/anonymous-layout/section/div[2]/div[2]/login/div/div[3]/loader-button/button"));
        // Send keys into fields
        Email.sendKeys("store@admin.com");
        Password.sendKeys("P@ssw0rd");
        // Clicking on the Sign-in button
        SignIn.click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        // Make assertion to ensure the login done successfully and the user is redirected to home page
        String expectedUrl = "https://oyn-adminportal-qc-demo.salmonsky-1edff179.westeurope.azurecontainerapps.io/admin/dashboard";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
        // Print the title of the home screen
        WebElement pageTitle = driver.findElement(By.xpath("/html/body/app-root/admin-layout/section/div[2]/breadcrumb/section/p"));
        String pageTitleText = pageTitle.getText();
        System.out.printf("The page title is:" + pageTitleText);
    }

   @Test public void Logout() {
        // Get locators
        WebElement pofileIcon = driver.findElement(By.xpath("/html/body/app-root/admin-layout/section/div[1]/header/section/navbar/div/div[2]/nav-actions/div/ul/li[4]/div/div[2]"));
        WebElement logoutButton = driver.findElement(By.xpath("/html/body/app-root/admin-layout/section/div[1]/header/section/navbar/div/div[2]/nav-actions/div/ul/li[4]/div/div[2]/ul/li[3]"));
        pofileIcon.click();
        logoutButton.click();
        // Checking that the user is logged out sucessully
        String expectedUrl = "https://oyn-adminportal-qc-demo.salmonsky-1edff179.westeurope.azurecontainerapps.io/identity/login";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
        driver.findElement(By.xpath("/html/body/app-root/anonymous-layout/section/div[2]/div[2]/login/div/div[3]/loader-button/button")).isDisplayed();
    }

    @Test public void closeBrowser() {
        driver.quit();
    }
}
