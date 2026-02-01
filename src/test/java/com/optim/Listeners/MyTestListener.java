package com.optim.Listeners;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.optim.Base.Base;
import com.optim.Utilities.ScreenshotUtil;
import com.optim.Utilities.Utils;

public class MyTestListener implements ITestListener {

    private final ExtentReports extent = ExtentReporter.getExtentReport();

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        ExtentTest test = extent.createTest(testName);
        ExtentTestManager.setTest(test);

        ExtentTestManager.getTest().info("Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test PASSED");
        ExtentTestManager.unload();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().fail(result.getThrowable());

        WebDriver driver = ((Base) result.getInstance()).getDriver();
        String methodName = result.getMethod().getMethodName();

        // Save screenshot (absolute path returned)
        String absPath = ScreenshotUtil.captureScreenshot(driver, methodName);

        // Convert to relative path from test-output folder
        String relativePath = "Screenshots" + File.separator + new File(absPath).getName();
        System.out.println("Relative Path Before Replacement : "+relativePath);
        relativePath = relativePath.replace("\\", "/"); // IMPORTANT for HTML        
        System.out.println("Relative Path After Replacement : "+relativePath);

        try {
            ExtentTestManager.getTest().addScreenCaptureFromPath(relativePath, "Failure Screenshot");
        } catch (Exception e) {
            ExtentTestManager.getTest().warning("Screenshot attach failed: " + e.getMessage());
        }

        ExtentTestManager.unload();
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().skip("Test SKIPPED");
        ExtentTestManager.unload();
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        Utils.copyPngFromReportsToRootScreenshots();
    }
}
