package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import pages.base_pages.BasePage;
import utilities.Links;

import java.util.Base64;

/**
 * User: Nhi Dinh
 * Date: 20/11/2018
 */
public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver){
        super(driver);
    }

    //----------WEB ELEMENT -------------
    @FindBy(id = "user_login")
    @CacheLookup
    private static WebElement txt_username;
    @FindBy(id = "user_pass")
    @CacheLookup
    private static
    WebElement txt_password;
    @FindBy(id = "wp-submit")
    @CacheLookup
    private static
    WebElement btnLogin;
    @FindBy(xpath = "//li[@id='wp-admin-bar-my-account']/a[@class='ab-item']/span[@class='display-name']")
    private static
    WebElement lblLoggedUsername;
    @FindBy(id = "login_error")
    private static WebElement lblLoginError;

    // ------------PAGE METHODS ---------------------

    public void LoginWithUsername(String username, String encodedPassword) {
        byte[] decodedPassword = Base64.getDecoder().decode(encodedPassword);
        String password = new String(decodedPassword);
        waitForPageLoad();
        setText(txt_username, username);
        setText(txt_password, password);
        click(btnLogin);
    }

    public void LoginToPage(String username, String encodedPassword, String url) {
        byte[] decodedPassword = Base64.getDecoder().decode(encodedPassword);
        String password = new String(decodedPassword);
        waitForPageLoad();
        setText(txt_username, username);
        setText(txt_password, password);
        click(btnLogin);
        navigateToPage(url);
    }

    public void VerifyLoginIsSuccessfully(String username){
        assertText(lblLoggedUsername, username);
    }

    public void VerifyErrorMessageDisplays(){
        waitElementVisibility(lblLoginError);
    }

    public LoginPage Goto(){
        navigateToPage(Links.URL_LOGIN);
        return new LoginPage(driver);
    }

}
