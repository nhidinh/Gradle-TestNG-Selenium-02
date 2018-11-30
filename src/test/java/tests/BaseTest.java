package tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */
public class BaseTest {
    public WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser, ITestContext context) {
        if (browser.equals("edge")) {
            EdgeDriverManager.getInstance().setup();
            driver = new EdgeDriver();
        }else if(browser.equals("firefox")){
            FirefoxDriverManager.getInstance().setup();
            driver = new FirefoxDriver();
        }else if(browser.equals("ie")){
            InternetExplorerDriverManager.getInstance().setup();
            driver = new InternetExplorerDriver();
        }else{
            ChromeDriverManager.getInstance().setup();
            driver = new ChromeDriver();
        }
        context.setAttribute("driver", driver);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void closeBrowser() {
//        driver.manage().deleteAllCookies();
//        driver.quit();
    }

    @BeforeMethod
    public void BeforeMethod() {
        System.out.println("Test is starting");
    }

    @AfterMethod
    public void AfterMethod() {
        System.out.println("Test is ending");
    }


}
