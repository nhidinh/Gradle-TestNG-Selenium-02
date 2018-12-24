package tests;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import data.ExcelHelper;
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
        for(int i = 1; i<countRow; i++){
            XSSFRow row = ExcelHelper.getRowData(i);
            username = row.getCell(1).toString();
            password = row.getCell(2).toString();
            testcaseName = row.getCell(0).toString();
            LoginPage loginPage = Page.Login().Goto();
            loginPage.LoginWithUsername(username, password);

            Log.info("Complete Test case: "+ testcaseName);
            System.out.println("Compete Test case: " + testcaseName);
            Page.TopNavigation().LogOut();
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
