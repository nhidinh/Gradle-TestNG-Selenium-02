package pages.base_pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import utilities.WebElementHelper;
import utilities.generator.PageGenerator;
import utilities.logger.Log;

import java.time.Duration;
import java.util.NoSuchElementException;

/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */
public class BasePage extends PageGenerator {
    private static WebDriverWait wait;
    private Wait waitF;
    private static final int TIMEOUT = 10;
    private static final Duration duration = Duration.ofSeconds(20);
    private static final Duration polling = Duration.ofSeconds(5);
    private static final WebElementHelper elementHelper = new WebElementHelper();

    //Constructor
    public BasePage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, TIMEOUT);
        waitF = new FluentWait<WebDriver>(driver).withTimeout(duration).pollingEvery(polling).ignoring(NoSuchElementException.class);
        PageFactory.initElements(driver, this);
    }

    //Hover Mouse
    public void hoverMouseToElement(WebElement element){
        Actions action = new Actions(driver);
        ((Actions) action).moveToElement(element).perform();
    }

    // Wait Element Visibility
    public static void waitElementVisibility(WebElement ele){
        String elementLocator = elementHelper.getLocatorOfElement(ele) ;
        Log.info(setStartMessage("waiting for", "",elementLocator, "is visibility" ));
        wait.until(ExpectedConditions.visibilityOf(ele));
        Log.info(setEndMessage("waiting for", "", elementLocator, "has been found"));
    }

    // Wait for Element is clickable
    public void waitForElementIsClickable(WebElement element){
        String elementLocator = elementHelper.getLocatorOfElement(element) ;
        Log.info(setStartMessage("waiting for","", elementLocator, "is clickable"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Log.info(setEndMessage("waiting for","",elementLocator ,"is clickable"));
    }

    //Verify Element is Presented
    public boolean isElementPresent(WebElement element){
        String elementLocator = elementHelper.getLocatorOfElement(element) ;
        Log.info(setStartMessage("checking for","", elementLocator, "is presented"));
        return element.isDisplayed();
    }

    //Wait Element Disappears
    public void waitElementDisappear(WebElement ele) {
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    // Navigate to page
    public static void navigateToPage(String url){
        Log.info("Navigating to " + url);
        try{
            driver.get(url);
        }catch (Exception e){
            System.out.println(e.getMessage());
            Log.error("Failed navigating to " + url);
            Log.error(e.toString());
        }
        Log.info("End of navigating to " + url + " step");
    }

    // Click
    public static void click(WebElement ele) {
        String elementLocator = elementHelper.getLocatorOfElement(ele) ;
        waitElementVisibility(ele);
        Log.info(setStartMessage("clicking to","", elementLocator,""));
        ele.click();
        Log.info(setEndMessage("clicking to", "", elementLocator, ""));
    }

    // Click with Javascript
    public void jsClick(WebElement ele) {
        String elementLocator = elementHelper.getLocatorOfElement(ele) ;
        Log.info(setStartMessage("clicking (JS) to","", elementLocator,""));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);
        Log.info(setEndMessage("clicking (JS) to", "", elementLocator, ""));
    }

    // Set Text
    public static void setText(WebElement ele, String text){
        String elementLocator = elementHelper.getLocatorOfElement(ele) ;
        waitElementVisibility(ele);
        Log.info(setStartMessage("setting text:", text, elementLocator, ""));
        ele.sendKeys(text);
        Log.info(setEndMessage("setting text:", text, elementLocator, ""));
    }

    // Get Text
    public static String getText(WebElement ele) {
        String elementLocator = elementHelper.getLocatorOfElement(ele) ;
        waitElementVisibility(ele);
        Log.info(setStartMessage("getting text of", "", elementLocator, ""));
        String text = ele.getText();
        Log.info(setEndMessage("getting text of", "", elementLocator, ""));
        return text;
    }

    //Assert Equals
    public static void assertText(WebElement ele, String expectedText) {
        String elementLocator = elementHelper.getLocatorOfElement(ele) ;
        waitElementVisibility(ele);
        Log.info(setStartMessage("asserting text:", expectedText, elementLocator, ""));
        Assert.assertEquals(getText(ele), expectedText);
        Log.info(setEndMessage("asserting text:", expectedText, elementLocator, ""));
    }

    // Get Page Title
    public String getPageTitle(){
        Log.info("Start getting page title");
        String title = driver.getTitle();
        Log.info("Return Page Title");
        return title;
    }

    // Back to Previous Page
    public void backToPreviousPage(){
        Log.info("Start backing to previous page");
        driver.navigate().back();
        Log.info("End backing to previous page");
    }

    //Wait for page load
    public static void waitForPageLoad(){
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(500);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Exception error) {
            Log.error(error.getMessage());
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }


    private static String setStartMessage(String action, String value, String elementLocator, String tail){
        if (!value.equals("")){
            value = "[" + value+"]";
        }
        return "Start " + action + value + " element with locator " + elementLocator + " " + tail;
    }
    private static String setEndMessage(String action, String value, String elementLocator, String tail){
        if (!value.equals("")){
            value = "[" + value+"]";
        }
        return "End " + action + " " + value + " element with locator " + elementLocator + " " + tail;
    }

}
