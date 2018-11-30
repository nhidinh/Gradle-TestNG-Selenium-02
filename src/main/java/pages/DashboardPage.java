package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import pages.base_pages.BasePage;
import pages.media_pages.MediaLibraryPage;
import pages.post_pages.PostsPage;

/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */
public class DashboardPage extends BasePage {
    //Constructor
    public DashboardPage(WebDriver driver){
        super(driver);
    }

    //------------VARIABLE------//
    Screen screen = new Screen();
    //------------ELEMENT-------//
    @FindBy(xpath = "//li[@id='menu-posts']//div[text()='Posts']")
    WebElement lnkPosts;

    @FindBy(id = "menu-media")
    WebElement lnkMedia;

    Pattern btnCustomizeSite =new Pattern(System.getProperty("user.dir")+"/src/object_images/btnCustomizeSite.png");


    //------------METHODS--------//
    public PostsPage navigateToPostPage(){
        click(lnkPosts);
        return new PostsPage(driver);
    }

    public void clickButtonCustomizeSite() throws FindFailed {
        screen.click(btnCustomizeSite);
    }

    public MediaLibraryPage navigateToMediaPage(){
        click(lnkMedia);
        return new MediaLibraryPage(driver);
    }
}
