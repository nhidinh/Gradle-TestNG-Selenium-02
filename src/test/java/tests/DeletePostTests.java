package tests;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.post_pages.AddNewPostPage;
import pages.post_pages.PostsPage;
import utilities.data.DataSetUp;

/**
 * User: Nhi Dinh
 * Date: 23/11/2018
 */
public class DeletePostTests extends BaseTest {
    @BeforeClass
    public void setUpData(ITestContext context){
        DataSetUp data = new DataSetUp();
        data.setUpPostData(context, driver);
    }

    @Test(description = "Delete a newly added Post", priority = 1)
    public void DeleteTheAddedPost(ITestContext context) {
        String title = (String) context.getAttribute("title");
        String body = (String) context.getAttribute("body");
        PostsPage postsPage;
        postsPage = Page.LeftNavigation().NavigateToPostPage();
        AddNewPostPage addNewPostPage = postsPage.clickAddNewPost();
        addNewPostPage.addNewPost(title, body);

        postsPage = Page.LeftNavigation().NavigateToPostPage();
        postsPage.deleteAPostByTile(title);
        postsPage.verifyPostIsMovedToTrash();
    }
}
