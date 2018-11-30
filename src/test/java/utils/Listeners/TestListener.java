package utils.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import utils.ExtentReports.ExtentManager;

import java.io.IOException;


/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */
public class TestListener extends BaseTest implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    String methodDes;

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Extent Reports Version 3 Test Suite started!");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Extent Reports Version 3  Test Suite is ending!"));
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String description = result.getMethod().getDescription();
        String methodName = result.getMethod().getMethodName();
        if(description != null) {
            methodDes = description;
        }else
        {
            methodDes = methodName;
        }
        System.out.println("==="+methodDes + "=== started!");
        ExtentTest extentTest = extent.createTest(methodDes,methodDes);
        test.set(extentTest);
        test.get().assignAuthor(System.getProperty("user"));
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println("==="+methodDes + "=== passed!");
        test.get().pass("==="+methodDes + "=== passed");

    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println("==="+methodDes + "=== failed!");
        try {
            WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
            String base64Screenshot = ExtentManager.getBase64Screenshot(driver, result.getName());
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build();
            test.get().fail("image:", mediaModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        test.get().fail(result.getThrowable().getMessage());
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println(("==="+methodDes + "=== skipped!"));
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + "==="+methodDes + "==="));
    }

}
