package tests;

import commontests.CommonPostTests;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;
import utils.DataSetup.DataSetUp;

/**
 * User: Nhi Dinh
 * Date: 23/11/2018
 */
public class DeletePostTests extends BaseTest {
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

    @Test(description = "Delete a newly added Post", priority = 1)
    public void DeleteTheAddedPost(ITestContext context) {
        CommonPostTests commonPostTests = new CommonPostTests();
        commonPostTests.CreateANewPostStep(context);
        commonPostTests.DeleteTheAddedPost(context);
    }


}
