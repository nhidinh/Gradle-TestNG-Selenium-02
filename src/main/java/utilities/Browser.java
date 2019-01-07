package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import utilities.driver.DriverManager;
import utilities.driver.DriverManagerFactory;
import utilities.driver.DriverType;

/**
 * User: Nhi Dinh
 * Date: 17/12/2018
 */
public class Browser {
    private static WebDriver driver;
    private static DriverManager driverManager;
    public void Initialize(){
    }
    public static void Setup(DriverType browser, ITestContext context){
        driverManager = DriverManagerFactory.getManager(browser);
        driver = driverManager.getDriver();
        context.setAttribute("driver", driver);
    }
    public static void Maximize(){
        driver.manage().window().maximize();
    }
    public static String GetTitle(){
        return driver.getTitle();
    }
    public static void Close(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
    public static void Quit(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
    public static WebDriver GetDriver(ITestContext context){
        return (WebDriver) context.getAttribute("driver");
    }
}
