package com.optim.Base;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.optim.Utilities.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base
{
    WebDriver driver;
    public String browser;
    public String baseURL;
    
    WebDriverWait wait;
    ConfigReader configReader = new ConfigReader();
          
    public WebDriver initializeWebDriverAndLaunchURL(String browser)
    {      
        //browser=configReader.getBrowser();
        baseURL=configReader.getBaseURL();
        try
        {                 
            System.out.println("Browser Name From Testng.xml File : "+browser);
            switch(browser)
            {
                case "chrome":
                {
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    break;
                }
                case "firefox":
                {
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                }
                case "edge":
                {
                    WebDriverManager.edgedriver().setup();
                    driver= new EdgeDriver();
                    break;
                }                    
            }
            driver.get(baseURL);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return driver;
    }
    
    public void captureScreenshot(WebDriver driver, String testCaseName)
    {
        try
        {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File dest = new File(System.getProperty("user.dir") + "\\Screenshots\\" + testCaseName + ".png");
            FileUtils.copyFile(src, dest);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    } 

    public WebDriver getDriver() {
        return driver;
    }

}
