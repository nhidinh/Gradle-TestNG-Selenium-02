package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */
public class DashboardPage extends BasePage {
    //Constructor
    public DashboardPage(WebDriver driver){
        super(driver);
    }

    //------------ELEMENT-------//
    @FindBy(xpath = "//li[@id='menu-posts']//div[text()='Posts']")
    WebElement lnkPosts;

    //------------METHODS--------//
    public PostsPage navigateToPostPage(){
        click(lnkPosts);
        return new PostsPage(driver);
    }
}
