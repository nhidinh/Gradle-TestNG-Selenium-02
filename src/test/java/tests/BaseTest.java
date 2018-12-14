package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;
import utilities.driver.DriverManager;
import utilities.driver.DriverManagerFactory;
import utilities.driver.DriverType;
import utilities.logger.Log;

/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */
public class BaseTest {
    public WebDriver driver;
    public DriverManager driverManager;

    @BeforeSuite
    public void beforeSuite(XmlTest test){
        String suite = test.getSuite().getName();
        Log.innitLog();
        Log.info("START SUITE: "+ suite);
    }
    @BeforeTest
    @Parameters("browser")
    public void setup(DriverType browser, ITestContext context) {
        driverManager = DriverManagerFactory.getManager(browser);
        driver = driverManager.getDriver();
        context.setAttribute("driver", driver);
        driver.manage().window().maximize();
    }

    @BeforeTest
    public void setUpLoggerBeforeTest(ITestContext context){
        String testCaseName = context.getName();
        Log.startTestCase(testCaseName);
    }

    @AfterTest
    public void endLogAfterTest(ITestContext context){
        String testCaseName = context.getName();
        Log.endTestCase(testCaseName);
    }

    @AfterTest
    public void closeBrowser() {
        Log.info("Closing browser after test");
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @AfterSuite
    public void afterSuite() {
        Log.info("ENDING SUITE");
    }

}
