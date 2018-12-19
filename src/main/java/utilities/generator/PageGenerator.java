package utilities.generator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.DashboardPage;
import pages.LoginPage;
import pages.base_pages.BasePage;
import pages.base_pages.LeftNavigation;
import pages.base_pages.TopNavigation;
import pages.media_pages.MediaLibraryPage;
import pages.post_pages.*;

/**
 * User: Nhi Dinh
 * Date: 17/12/2018
 */
public class PageGenerator {
    public static WebDriver driver;

    public PageGenerator(WebDriver driver) {
        this.driver = driver;
    }

    private <TPage extends BasePage> TPage GetPage(Class<TPage> pageClass) {
        try {
            //Initialize the Page with its elements and return it
            return PageFactory.initElements(driver, pageClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public LoginPage Login() {
        return GetPage(LoginPage.class);
    }

    public DashboardPage Dashboard() {
        return GetPage(DashboardPage.class);
    }

    public AddMediaForm AddMediaForm() {
        return GetPage(AddMediaForm.class);
    }

    public AddNewPostPage AddNewPost() {
        return GetPage(AddNewPostPage.class);
    }

    public EditPostPage EditPost() {
        return GetPage(EditPostPage.class);
    }

    public PostDetailPage PostDetail() {
        return GetPage(PostDetailPage.class);
    }

    public PostsPage Posts() {
        return GetPage(PostsPage.class);
    }

    public MediaLibraryPage MediaLibrary() {
        return GetPage(MediaLibraryPage.class);
    }

    public TopNavigation TopNavigation() {
        return GetPage(TopNavigation.class);
    }

    public LeftNavigation LeftNavigation(){
        return GetPage(LeftNavigation.class);
    }
}
