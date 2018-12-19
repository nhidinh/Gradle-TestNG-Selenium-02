package tests;

import org.testng.annotations.Optional;
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
    public void LoginSuccessfully(String username, String encodedPassword) {
        LoginPage loginPage = Page.Login().Goto();
        loginPage.LoginWithUsername(username, encodedPassword);
        loginPage.VerifyLoginIsSuccessfully(username);
        Page.TopNavigation().LogOut();
    }

    @Test(description = "Perform invalid login")
    @Parameters(value = {"username", "encodedPassword"})
    public void LoginUnsuccessfully(@Optional String username, @Optional String invalidPass){
        LoginPage loginPage = Page.Login().Goto();
        loginPage.LoginWithUsername(username, "invalidPassword");
        loginPage.VerifyErrorMessageDisplays();
    }
}
