package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.extentreports.ExtentManager;
import utilities.logger.Log;

import java.io.IOException;

/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */
public class TestListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static String methodDes;
    private static String message;

    @Override
    public synchronized void onStart(ITestContext context) {
        //Perform Onstart action
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        extent.flush();
        message = "TEST COMPLETED - REPORT IS UPDATED";
        Log.info(message);
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String description = result.getMethod().getDescription();
        String methodName = result.getMethod().getMethodName();
        if (description != null) {
            methodDes = description;
        } else {
            methodDes = methodName;
        }
        message = setMessage("START", methodDes);

        ExtentTest extentTest = extent.createTest(methodDes, methodDes);
        test.set(extentTest);
        test.get().assignAuthor(System.getProperty("user"));
        Log.info(message);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        message = setMessage("PASS", methodDes);
        test.get().pass(message);
        Log.info(message);
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        message = setMessage("FAILED", methodDes);
        test.get().fail(message);
        try {
            WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
            String base64Screenshot = ExtentManager.getBase64Screenshot(driver, result.getName());
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build();
            test.get().fail("image:", mediaModel);
        } catch (IOException e) {
            Log.error(e.getMessage());
        }
        Throwable error = result.getThrowable();
        test.get().fail(error.getMessage());
        Log.error(error.toString());
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        message = setMessage("SKIPPED", methodDes);
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Log.info("onTestFailedButWithinSuccessPercentage for " + methodDes );
    }

    public String setMessage(String status, String description){
        return  "++++++++ TEST "+status+": " + description + " ++++++++";
    }
}
