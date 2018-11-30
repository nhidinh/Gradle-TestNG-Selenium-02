package tests;

import commontests.CommonLoginTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */
@Test
public class LoginTests extends BaseTest {
    @Test(description = "Perform login successfully")
    @Parameters(value = {"username", "encodedPassword"})
    public void LoginSuccessfully(String username, String encodedPassword) throws InterruptedException {
        CommonLoginTest loginTest = new CommonLoginTest();
        loginTest.LoginSuccessfully(username, encodedPassword, driver);
    }
}
