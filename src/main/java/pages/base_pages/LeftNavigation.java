package pages.base_pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.media_pages.MediaLibraryPage;
import pages.post_pages.PostsPage;

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

    public PostsPage NavigateToPostPage(){
        click(lnkPosts);
        return new PostsPage(driver);
    }

    public MediaLibraryPage NavigateToMediaPage(){
        click(lnkMedia);
        return new MediaLibraryPage(driver);
    }
}
