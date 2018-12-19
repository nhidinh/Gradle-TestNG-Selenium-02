package utilities.driver;

import org.openqa.selenium.WebDriver;

/**
 * User: Nhi Dinh
 * Date: 5/12/2018
 */
public abstract class DriverManager {
    protected WebDriver driver;
    protected abstract WebDriver createDriver();

    public WebDriver getDriver(){
        if(driver == null){
            driver = createDriver();
        }
        return driver;
    }
}
