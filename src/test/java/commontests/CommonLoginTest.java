package commontests;

import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;

/**
 * User: Nhi Dinh
 * Date: 26/11/2018
 */
public class CommonLoginTest {
    public void LoginSuccessfully(String username, String encodedPassword, WebDriver driver) throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        LoginPage loginpage = homepage.goToLoginPage();
        loginpage.loginWithUsername(username, encodedPassword);
    }
}
