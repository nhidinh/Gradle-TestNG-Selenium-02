package tests;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.Links;
import utilities.data.DataSetUp;

/**
 * User: Nhi Dinh
 * Date: 22/11/2018
 */
public class PostTests extends BaseTest {
    @BeforeClass
    public void setUpData(ITestContext context) {
        DataSetUp data = new DataSetUp();
        data.setUpPostData(context, driver);
    }

    @Test(description = "Create A New Post")
    public void CreateANewPost(ITestContext context) {
        String title = (String) context.getAttribute("title");
        String body = (String) context.getAttribute("body");

        Page.LeftNavigation().NavigateToPostPage();
        Page.Posts().clickAddNewPost();
        Page.AddNewPost().addNewPost(title, body);

        Page.AddNewPost().verifyNewPostIdAdded(title);
    }

    @Test(description = "Create A New Post With Media Uploaded")
    public void CreateNewPostWithMedia(ITestContext context) {
        String title = (String) context.getAttribute("title");
        String body = (String) context.getAttribute("body");
        String imagePath = Links.IMAGE_PATH;

        Page.LeftNavigation().NavigateToPostPage();
        Page.Posts().clickAddNewPost();

        Page.AddNewPost().addMediaToPost(imagePath);
        Page.AddNewPost().addNewPost(title, body);
    }

    @Test(description = "Create A New Post With New Gallery")
    public void CreateNewPostWithGallery(ITestContext context) {
        String title = (String) context.getAttribute("title");
        String body = (String) context.getAttribute("body");
        String imagePath = Links.IMAGE_PATH;

        Page.LeftNavigation().NavigateToPostPage();
        Page.AddNewPost().addGalleryToPost(imagePath);
        Page.AddNewPost().addNewPost(title, body);
    }

}
