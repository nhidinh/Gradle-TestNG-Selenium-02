package utilities.CustomKeywords;

import org.openqa.selenium.WebDriver;
import pages.base_pages.BasePage;

/**
 * User: Nhi Dinh
 * Date: 29/11/2018
 */
public class DriverFactory extends BasePage {
    public DriverFactory(WebDriver driver){
        super(driver);
    }
    public WebDriver getWebDriver(){
        return this.driver;
    }
}
