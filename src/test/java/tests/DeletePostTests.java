package tests;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.IPage;
import pages.post_pages.Post_AddNew_Page;
import pages.post_pages.Post_All_Page;
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
        Post_All_Page postsPage = Page.LeftNavigation().NavigateToPostPage();
//        Post_AddNew_Page addNewPostPage = postsPage.clickAddNewPost();
//          addNewPostPage.addNewPost(title, body);

        IPage addNewPostPage = postsPage.clickAddNewPost();
        addNewPostPage.testmethod();
        ((Post_AddNew_Page) addNewPostPage).addNewPost(title, body);

        postsPage = Page.LeftNavigation().NavigateToPostPage();
        postsPage.deleteAPostByTile(title);
        postsPage.verifyPostIsMovedToTrash();
    }
}
