package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pageObjects.DriverUtility;

public class ExtentReportsManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;

    // Thread-safe storage for each test thread
    public static ThreadLocal<ExtentTest> testsExtentTest = new ThreadLocal<>();

    String repNameString;

    @Override
    public void onStart(ITestContext testContext) {

        String timeStampString =
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        repNameString = "Test-Report-" + timeStampString + ".html";

        sparkReporter = new ExtentSparkReporter("./Reports/" + repNameString);

        sparkReporter.config().setDocumentTitle("OpenKart Automation Report");
        sparkReporter.config().setReportName("OpenKart Functional Testing");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        extentReports.setSystemInfo("Application", "OpenKart");
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("User", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment", "QA");
    }

    
    
    
    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test =
                extentReports.createTest(result.getMethod().getMethodName());

        test.assignCategory(result.getMethod().getGroups());

        testsExtentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        testsExtentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        testsExtentTest.get().log(Status.FAIL, result.getThrowable());

        try {
            testsExtentTest.get()
                    .addScreenCaptureFromPath(DriverUtility.takeScreenShot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        testsExtentTest.get().log(Status.SKIP, "Test Skipped");

        if (result.getThrowable() != null) {
            testsExtentTest.get().log(Status.SKIP,
                    result.getThrowable().getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext testContext) {

        extentReports.flush();

        testsExtentTest.remove();

        try {
            File file = new File("./Reports/" + repNameString);
            Desktop.getDesktop().browse(file.toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}