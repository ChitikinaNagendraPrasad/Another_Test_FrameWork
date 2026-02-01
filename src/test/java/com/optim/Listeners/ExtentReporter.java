package com.optim.Listeners;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

    private static ExtentReports extent;

    public static ExtentReports getExtentReport() {

        if (extent == null) {

/*            String reportPath = System.getProperty("user.dir")
                    + File.separator + "test-output"
                    + File.separator + "ExtentReport.html";*/
            
            String reportPath = System.getProperty("user.dir")
                    + File.separator + "Reports"
                    + File.separator + "ExtentReport.html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setReportName("Automation Report");
            spark.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Nagendra");
        }

        return extent;
    }
}
