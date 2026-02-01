package com.optim.Pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.optim.Listeners.ExtentTestManager;

public class HomePage
{
    WebDriver driver;
    Wait<WebDriver> wait ;
    public HomePage(WebDriver driver)
    {
        try
        {
            this.driver = driver;
            wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(300)).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).ignoring(ElementClickInterceptedException.class);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Page Properties
    
    By home_Link = By.xpath("//a[normalize-space()=\"Home\"]");
    By products_Link = By.xpath("//a[@href=\"/products\"]");
    By cart_Link = By.xpath("//a[normalize-space()=\"Cart\"]");
    By signupOrLogin_Link = By.xpath("//a[normalize-space()=\"Signup / Login\"]");
    By testCases_Link = By.xpath("//a[contains(text(),\"Test Cases\")]");
    By apiTesting_Link = By.xpath("//a[normalize-space()=\"API Testing\"]");
    
    

    
    
    // Page Methods
    public void clickOn_home_Link()
    {
        try
        {
            wait.until(ExpectedConditions.elementToBeClickable(home_Link)).click();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void clickOn_products_Link()
    {
        try
        {
            wait.until(ExpectedConditions.elementToBeClickable(products_Link)).click();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void clickOn_cart_Link()
    {
        try
        {
            wait.until(ExpectedConditions.elementToBeClickable(cart_Link)).click();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public LoginPage clickOn_signupOrLogin_Link()
    {
        try
        {
            wait.until(ExpectedConditions.elementToBeClickable(signupOrLogin_Link)).click();
            ExtentTestManager.getTest().info("Successfully Clicked On \"SignUp Or Login\" Link On Home Page");                                               
        } catch (Exception e)
        {
            e.printStackTrace();
        }        
        return new LoginPage(driver);
    }

    public void clickOn_testCases_Link()
    {
        try
        {
            wait.until(ExpectedConditions.elementToBeClickable(testCases_Link)).click();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
        
    public void clickOn_apiTesting_Link()
    {
        try
        {
            wait.until(ExpectedConditions.elementToBeClickable(apiTesting_Link)).click();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
