package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * User: Nhi Dinh
 * Date: 22/11/2018
 */
public class PostDetailPage extends BasePage {
    public PostDetailPage(WebDriver driver){
        super(driver);
    }
    //========== WEB ELEMENT ============//
    @FindBy(xpath = "//div[@id='primary']//h1[contains(@class, 'entry-title')]")
    WebElement lblTitle;

    public String getPostTitle(){
        return getText(lblTitle);
    }
}
