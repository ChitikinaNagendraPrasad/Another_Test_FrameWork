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

import com.optim.Utilities.Utils;

public class LoginPage
{
    WebDriver driver;
    Wait<WebDriver> wait;
    

    public LoginPage(WebDriver driver)
    {
        try
        {
            this.driver=driver;
            wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(300)).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).ignoring(ElementClickInterceptedException.class);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    // Page Properties
    
    //Login Form
    By loginForm_EmailAddress_TextBox = By.xpath("//div[@class=\"login-form\"]//input[@name='email']"); 
    By loginForm_Password_TextBox = By.xpath("//div[@class=\"login-form\"]//input[@name='password']");
    By loginForm_Login_Button = By.xpath("//div[@class=\"login-form\"]//button[contains(text(),'Login')]");
    
    
    //Error Messages
    By errorMsg_loginForm_EmailOrPassword_Incorrect = By.xpath("//p[normalize-space()=\"Your email or password is incorrect!\"]");
    
    public void fillDataIn_loginForm_EmailAddress_TextBox(String email)
    {
        try
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm_EmailAddress_TextBox)).sendKeys(email);
            Utils.displayInfoInExtentReport("Successfully Entered \"Email Address\" ("+email+") in Email Address Text Box On Login Page");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void fillDataIn_loginForm_Password_TextBox(String password)
    {
        try
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm_Password_TextBox)).sendKeys(password);
            Utils.displayInfoInExtentReport("Successfully Entered \"Password\" ("+password+") in Password Text Box On Login Page");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void clickOn_loginForm_Login_Button()
    {
        try
        {
            wait.until(ExpectedConditions.elementToBeClickable(loginForm_Login_Button)).click();
            Utils.displayInfoInExtentReport("Successfully Clicked On \"Login\" Button On Login Page");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public String get_EmailOrPassword_Incorrect_Message()
    {
        String returnValue=null;
        try
        {
            returnValue=wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg_loginForm_EmailOrPassword_Incorrect)).getText();
            Utils.displayInfoInExtentReport("Actual Error Message : "+returnValue);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return returnValue;
    }
    
    
    //SignUp Form
        
    By signUpForm_Name_TextBox = By.xpath("//div[@class=\"signup-form\"]//input[@name='name']");
    By signUpForm_EmailAddress_TextBox = By.xpath("//div[@class=\"signup-form\"]//input[@name='email']");
    By signUpForm_Signup_Button = By.xpath("//div[@class=\"signup-form\"]//button[@data-qa='signup-button']");
    
    public void fillDataIn_signUpForm_Name_TextBox(String name)
    {
        try
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(signUpForm_Name_TextBox)).sendKeys(name);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void fillDataIn_signUpForm_EmailAddress_TextBox(String emailAddress)
    {
        try
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(signUpForm_EmailAddress_TextBox)).sendKeys(emailAddress);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public SignUpPage clickOn_signUpForm_Signup_Button()
    {
        try
        {
            wait.until(ExpectedConditions.elementToBeClickable(signUpForm_Signup_Button)).click();  
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return new SignUpPage(driver);
    }
}
