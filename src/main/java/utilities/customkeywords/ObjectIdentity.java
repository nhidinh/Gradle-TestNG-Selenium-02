package utilities.customkeywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;

/**
 * User: Nhi Dinh
 * Date: 3/12/2018
 */
public class ObjectIdentity {
    public WebElement findElementByXpath(String xpath, ITestContext context){
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        return driver.findElement(By.xpath(xpath));
    }
}
