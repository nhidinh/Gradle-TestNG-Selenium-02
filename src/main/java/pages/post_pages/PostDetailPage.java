package pages.post_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_pages.BasePage;

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
    @FindBy(xpath = "//a[@class='post-edit-link']")
    WebElement btnEdit;

    public String getPostTitle(){
        return getText(lblTitle);
    }

    public EditPostPage editPost(){
        click(btnEdit);
        return new EditPostPage(driver);
    }
}
