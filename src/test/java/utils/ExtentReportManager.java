package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance("reports/extent-report.html");
        }
        return extent;
    }

    private static void createInstance(String filePath) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filePath);
        sparkReporter.config().setReportName("OrangeHRM Automation Report");
        sparkReporter.config().setDocumentTitle("Automation Test Results");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Framework", "Selenium with TestNG");
        extent.setSystemInfo("Author", "QA Automation");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tool", "ExtentReports 5.1.1");
    }
}
