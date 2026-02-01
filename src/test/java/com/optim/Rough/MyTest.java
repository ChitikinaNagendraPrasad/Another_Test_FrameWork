package com.optim.Rough;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.optim.Base.Base;
import com.optim.Listeners.ExtentTestManager;
import com.optim.Pages.HomePage;
import com.optim.Pages.LoginPage;
import com.optim.Pages.SignUpPage;
import com.optim.Utilities.Utils;


public class MyTest extends Base
{
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    SignUpPage signUpPage;

    public void initializePages()
    {
        try
        {
            homePage = new HomePage(driver);
            loginPage = new LoginPage(driver);
            signUpPage = new SignUpPage(driver);
        } catch (Exception e)
        {
            throw new RuntimeException("Exception In MyTest::initializePages() Method",e);
        }
    }

    @Parameters(
    { "browser" }) @BeforeMethod
    public void setUP(@Optional("chrome") String browser)
    {
        try
        {
            driver = initializeWebDriverAndLaunchURL(browser);
            initializePages();
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Exception In MyTest::setUP() Method",e);
        }
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
    
    public void loggingURLInfoInReport()
    {
        ExtentTestManager.getTest().info("Successfully Opened The Browser And Launched The Base URL ("+baseURL+")");
        ExtentTestManager.getTest().info("Successfully Maximized The Browser Window");
    }

    @Test
    public void testCase1()
    {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        try
        {
            String emailAddress="ch.nagendra@nag.com";
            String password="12345";

            loggingURLInfoInReport();
            
            loginPage = homePage.clickOn_signupOrLogin_Link();             
            loginPage.fillDataIn_loginForm_EmailAddress_TextBox(emailAddress);            
            loginPage.fillDataIn_loginForm_Password_TextBox(password);            
            loginPage.clickOn_loginForm_Login_Button();            
            String actualErrorMessage = loginPage.get_EmailOrPassword_Incorrect_Message();
            String expectedErrorMessage = "Your email or password is incorrect!";            
            Utils.displayInfoInExtentReport("Expected Error Message : "+expectedErrorMessage);                       
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error Message Not Matched");
        } catch (Exception e)
        {
            throw new RuntimeException("Exception In MyTest::"+methodName+"() Method",e);
        }
    }

    @Test
    public void testCase2()
    {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();        
        try
        {
            String email = Utils.generateEmail();
            String password = email.replaceAll("\\D", "");
            
            loggingURLInfoInReport();
            
            loginPage = homePage.clickOn_signupOrLogin_Link();             
            loginPage.fillDataIn_signUpForm_Name_TextBox("Nagendra");
            loginPage.fillDataIn_signUpForm_EmailAddress_TextBox(email);
            signUpPage = loginPage.clickOn_signUpForm_Signup_Button();
            signUpPage.clickOn_accountInformation_Mr_RadioButton();
            signUpPage.fillDataIn_accountInformation_Password_TextBox(password);
            //signUpPage.selectDOB("12/10/2005");
            //System.out.println();

        } catch (Exception e)
        {
            throw new RuntimeException("Exception In MyTest::"+methodName+"() Method",e);
        }
    }
}