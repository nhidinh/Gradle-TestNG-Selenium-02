package tests;

import commontests.CommonLoginTest;
import org.sikuli.script.FindFailed;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DashboardPage;

/**
 * User: Nhi Dinh
 * Date: 30/11/2018
 */
public class CustomizeSiteTest extends BaseTest {
    @BeforeClass
    @Parameters({"username", "encodedPassword"})
    public void login(String username, String encodedPassword) throws InterruptedException {
        CommonLoginTest loginTest = new CommonLoginTest();
        loginTest.LoginSuccessfully(username, encodedPassword, driver);
    }
    @Test
    public void openCustomeizeSite() throws FindFailed {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickButtonCustomizeSite();
    }
}
