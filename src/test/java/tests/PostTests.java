package tests;

import commontests.CommonPostTests;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.HomePage;
import pages.LoginPage;
import pages.post_pages.AddNewPostPage;
import pages.post_pages.PostsPage;
import utilities.datasetup.DataSetUp;

import java.awt.*;

/**
 * User: Nhi Dinh
 * Date: 22/11/2018
 */
public class PostTests extends BaseTest {
    @BeforeClass
    @Parameters({"username", "encodedPassword"})
    public void LoginStep(String username, String encodedPassword) {
        HomePage homepage = new HomePage(driver);
        LoginPage loginpage = homepage.goToLoginPage();
        loginpage.loginWithUsername(username, encodedPassword);
    }

    @BeforeClass
    public void setUpData(ITestContext context) {
        DataSetUp data = new DataSetUp();
        data.setUpPostData(context, driver);
    }

    @Test(description = "Create A New Post")
    public void CreateANewPost(ITestContext context) {
        CommonPostTests postTests = new CommonPostTests();
        postTests.CreateANewPostStep(context);

        String title = (String) context.getAttribute("title");
        AddNewPostPage addNewPostPage = new AddNewPostPage(driver);
        addNewPostPage.verifyNewPostIdAdded(title+"333");
    }

    @Test(description = "Create A New Post With Media Uploaded")
    public void CreateNewPostWithMedia(ITestContext context) throws AWTException {
        DashboardPage dashboardPage = (DashboardPage) context.getAttribute("dashboardPage");
        String title = (String) context.getAttribute("title");
        String body = (String) context.getAttribute("body");

        PostsPage postsPage = dashboardPage.navigateToPostPage();
        AddNewPostPage addNewPostPage = postsPage.clickAddNewPost();

        String imagePath = System.getProperty("user.dir") + "\\media\\dog.jpg";
        addNewPostPage.addMediaToPost(imagePath);
    }

    @Test(description = "Create A New Post With New Gallery")
    public void CreateNewPostWithGalery(ITestContext context) throws InterruptedException {
        DashboardPage dashboardPage = (DashboardPage) context.getAttribute("dashboardPage");
        String title = (String) context.getAttribute("title");
        String body = (String) context.getAttribute("body");
        String imagePath = System.getProperty("user.dir") + "\\media\\dog.jpg";

        PostsPage postsPage = dashboardPage.navigateToPostPage();
        AddNewPostPage addNewPostPage = postsPage.clickAddNewPost();
        addNewPostPage.addGalleryToPost(imagePath);
    }

}
