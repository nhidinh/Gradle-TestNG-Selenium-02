package tests;

import data.ExcelHelper;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.logger.Log;

/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */

public class LoginTests extends BaseTest {
    @BeforeTest
    public void setUpTestData( ){
        Log.info("Setting up Test Data");
        ExcelHelper.setDataFileLocation("ExcelTestData.xlsx");
        ExcelHelper.setExcelFileSheet("Login");
    }
    @Test
    public void LoginTestWithDataFile(){
        int countRow = ExcelHelper.getNumberOfRow();
        String username;
        String password;
        String testcaseName;
        String status;
        int usernameCell = ExcelHelper.getCellIndexByText("username");
        int passwordCell = ExcelHelper.getCellIndexByText("password");
        int tcNameCell = ExcelHelper.getCellIndexByText("testcasename");
        int statusCell = ExcelHelper.getCellIndexByText("status");
        for(int i = 1; i<countRow; i++){
            username = ExcelHelper.getCellData(i, usernameCell);
            password = ExcelHelper.getCellData(i, passwordCell);
            testcaseName = ExcelHelper.getCellData(i, tcNameCell);
            status = ExcelHelper.getCellData(i, statusCell);

            LoginPage loginPage = Page.Login().Goto();
            loginPage.LoginWithUsername(username, password);

            Log.info("Complete Test case: "+ testcaseName);
            System.out.println("Compete Test case: " + testcaseName);
            if (status.equals("pass")){
                loginPage.VerifyLoginIsSuccessfully(username);
                Page.TopNavigation().LogOut();
            }else if(status.equals("failed")) {
                loginPage.VerifyErrorMessageDisplays();
            }
        }
    }


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
