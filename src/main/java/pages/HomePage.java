package pages;

import org.openqa.selenium.WebDriver;
import pages.base_pages.BasePage;
import utilities.Links;

/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */
public class HomePage extends BasePage {
    public HomePage(WebDriver driver){
        super(driver);
    }
    //-----------VARIABLES-----------
    private String baseURL = Links.URL_DASHBOARD;
    ///----------PAGE METHODS--------------
    public LoginPage goToLoginPage(){
        navigateToPage(baseURL);
        return new LoginPage(driver);
    }
}
