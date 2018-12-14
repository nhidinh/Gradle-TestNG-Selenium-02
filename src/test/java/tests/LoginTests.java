package tests;

import commontests.CommonLoginTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;

/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */

public class LoginTests extends BaseTest {
    @Test(description = "Perform login successfully")
    @Parameters(value = {"username", "encodedPassword"})
    public void LoginSuccessfully(String username, String encodedPassword) throws InterruptedException {
        CommonLoginTest loginTest = new CommonLoginTest();
        LoginPage loginPage = new LoginPage(driver);

        loginTest.LoginSuccessfully(username, encodedPassword, driver);
        loginPage.verifyLoginIsSuccessfully(username);
    }

    @Test(description = "Perform invalid login")
    @Parameters(value = {"username", "encodedPassword"})
    public void LoginUnsuccessfully(String username, String invalidPass) throws InterruptedException{
        CommonLoginTest loginTest = new CommonLoginTest();
        loginTest.LoginSuccessfully(username,"invalidPassword", driver);
    }
}
