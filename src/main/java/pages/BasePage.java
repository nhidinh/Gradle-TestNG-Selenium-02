package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */
public class BasePage {
    public WebDriver driver;
    private WebDriverWait wait;
    private static final int TIMEOUT = 10;
    private static final int POLLING = 100;


    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT);
        PageFactory.initElements(driver, this);
    }

    // Wait Element Visibility
    public void waitElementVisibility(WebElement ele){
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    //Wait Element Disappears
    public void waitElementDisappear(WebElement ele) {
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    // Navigate to page
    public void navigateToPage(String URL){
        driver.get(URL);
    }

    // Click
    public void click(WebElement ele) {
        waitElementVisibility(ele);
        ele.click();
    }

    // Click with Javascript
    public void jsClick(WebElement ele) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);
    }

    // Set Text
    public void setText(WebElement ele, String text){
        waitElementVisibility(ele);
        ele.sendKeys(text);
    }

    // Get Text
    public String getText(WebElement ele) {
        waitElementVisibility(ele);
        return ele.getText();
    }

    //Assert Equals
    public void assertText(WebElement ele, String expectedText) {
        waitElementVisibility(ele);
        Assert.assertEquals(getText(ele), expectedText);
    }
}
