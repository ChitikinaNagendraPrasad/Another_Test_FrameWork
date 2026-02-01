package com.optim.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtil {

    /**
     * Captures a screenshot and saves it under:
     * <project>/test-output/screenshots/
     *
     * @param driver         WebDriver instance
     * @param screenshotName Name you want for the screenshot (ex: test method name)
     * @return Absolute path of the saved screenshot file
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {

        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";        
        //String destinationPath = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "screenshots" + File.separator + fileName;        
        //String destinationPath = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + fileName;
        

        String destinationPath = System.getProperty("user.dir")
                + File.separator + "Reports"
                + File.separator + "Screenshots"
                + File.separator + fileName;

        
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File destFile = new File(destinationPath);
            destFile.getParentFile().mkdirs(); // create folder if not exists

            FileHandler.copy(srcFile, destFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to capture screenshot: " + destinationPath, e);
        }

        return destinationPath;
    }
    

    public static String captureScreenshotBase64(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

}
