package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;
import utilities.Browser;
import utilities.Links;
import utilities.driver.DriverType;
import utilities.generator.PageGenerator;
import utilities.logger.Log;
import utils.setup_testsuite.LoginAPI;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */
public class BaseTest {
    WebDriver driver;
    PageGenerator Page;

    @BeforeSuite
    public void beforeSuite(XmlTest test) {
        String suite = test.getSuite().getName();
        Log.innitLog();
        Log.info("START SUITE: " + suite);
    }

    @BeforeTest
    @Parameters("browser")
    public void Setup(DriverType browser, ITestContext context) {
        Browser.Setup(browser, context);
        Browser.Maximize();
        driver = (WebDriver) context.getAttribute("driver");
    }

    @BeforeTest
    public void setPage(){
        Page = new PageGenerator(driver);
    }

    @BeforeTest
    public void SetUpLoggerBeforeTest(ITestContext context) {
        String testCaseName = context.getName();
        Log.startTestCase(testCaseName);
    }

    @BeforeClass
    @Parameters({"username", "encodedPassword"})
    public void LoginBeforeTest(String username, String encodedPassword, ITestContext testContext){
        if(!testContext.getName().contains("Login Test")) {
            String loginURI = Links.API_URI_LOGIN;
            LoginAPI loginAPI = new LoginAPI();
            try {
                loginAPI.loginAPI(driver, loginURI);
            } catch (ExecutionException | InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
        Page.Dashboard().Goto();
    }

    @AfterTest
    public void EndLogAfterTest(ITestContext context) {
        String testCaseName = context.getName();
        Log.endTestCase(testCaseName);
    }

    @AfterTest
    public void CloseBrowser() {
        Log.info("Closing browser after test");
        Browser.Close();
    }

    @AfterSuite
    public void AfterSuite() {
        Log.info("ENDING SUITE");
    }

}
