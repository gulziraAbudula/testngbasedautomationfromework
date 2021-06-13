package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private ExtentReports extent;
    private ExtentSparkReporter spark;
    private static ExtentTest extentTest;
    // ------------- Test Context ( Individual test ) -------------
    @Override
    public void onStart(ITestContext context) {
        extent = new ExtentReports();
        String reportPath = System.getProperty("user.dir") +"/reports/result.html";
        spark = new ExtentSparkReporter(reportPath);

        extent.attachReporter(spark);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // ------------ Individual test cases ----------
    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extent.createTest(result.getMethod().getMethodName());
    }

    public static ExtentTest step(){
        return extentTest;
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.pass("This test case has passed!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.fail("This test case has failed!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.skip("This test case has skipped!");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

}
