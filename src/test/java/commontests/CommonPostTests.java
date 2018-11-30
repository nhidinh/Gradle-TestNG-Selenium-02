package commontests;

import org.testng.ITestContext;
import pages.post_pages.AddNewPostPage;
import pages.DashboardPage;
import pages.post_pages.PostsPage;

/**
 * User: Nhi Dinh
 * Date: 26/11/2018
 */
public class CommonPostTests {
    public void CreateANewPostStep(ITestContext context){
        DashboardPage dashboardPage = (DashboardPage) context.getAttribute("dashboardPage");
        String title = (String) context.getAttribute("title");
        String body = (String) context.getAttribute("body");

        PostsPage postsPage = dashboardPage.navigateToPostPage();
        AddNewPostPage addNewPostPage = postsPage.clickAddNewPost();
        addNewPostPage.addNewPost(title, body);
    }

    public void DeleteTheAddedPost(ITestContext context) {
        DashboardPage dashboardPage = (DashboardPage) context.getAttribute("dashboardPage");
        String title = (String) context.getAttribute("title");
        PostsPage postsPage = dashboardPage.navigateToPostPage();
        postsPage.deleteAPostByTile(title);
        postsPage.verifyPostIsMovedToTrash();
    }
}
