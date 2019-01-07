package pages.base_pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.media_pages.MediaLibrary_Page;
import pages.post_pages.Post_All_Page;

/**
 * User: Nhi Dinh
 * Date: 17/12/2018
 */
public class LeftNavigation extends BasePage {
    public LeftNavigation(){
        super(driver);
    }

    @FindBy(xpath = "//li[@id='menu-posts']//div[text()='Posts']")
    private WebElement lnkPosts;
    @FindBy(id = "menu-media")
    private WebElement lnkMedia;

    public Post_All_Page NavigateToPostPage(){
        click(lnkPosts);
        return new Post_All_Page(driver);
    }

    public MediaLibrary_Page NavigateToMediaPage(){
        click(lnkMedia);
        return new MediaLibrary_Page(driver);
    }
}
