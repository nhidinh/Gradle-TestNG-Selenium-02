package tests;

import commontests.CommonPostTests;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.DataSetup.DataSetUp;

/**
 * User: Nhi Dinh
 * Date: 22/11/2018
 */
public class PostTests extends BaseTest {
    @BeforeClass
    @Parameters({"username", "encodedPassword"})
    public void LoginStep(String username, String encodedPassword){
        HomePage homepage = new HomePage(driver);
        LoginPage loginpage = homepage.goToLoginPage();
        loginpage.login(username, encodedPassword);
    }

    @BeforeClass
    public void setUpData(ITestContext context){
        DataSetUp data = new DataSetUp();
        data.setUpPostData(context, driver);
    }

    @Test(description = "Create A New Post")
    public void CreateANewPost(ITestContext context){
        CommonPostTests postTests = new CommonPostTests();
        postTests.CreateANewPostStep(context);
    }

}
