package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base_pages.BasePage;

import java.util.Base64;

/**
 * User: Nhi Dinh
 * Date: 20/11/2018
 */
public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //----------WEB ELEMENT -------------
    @FindBy(id = "user_login")
    @CacheLookup
    private WebElement txt_username;
    @FindBy(id = "user_pass")
    @CacheLookup
    private
    WebElement txt_password;
    @FindBy(id = "wp-submit")
    @CacheLookup
    private
    WebElement btnLogin;
    @FindBy(xpath = "//li[@id='wp-admin-bar-my-account']/a[@class='ab-item']/span[@class='display-name']")
    private
    WebElement lblLoggedUsername;

    // ------------PAGE METHODS ---------------------

    public void loginWithUsername(String username, String encodedPassword) {
        byte[] decodedPassword = Base64.getDecoder().decode(encodedPassword);
        String password = new String(decodedPassword);
        waitForPageLoad();
        setText(txt_username, username);
        setText(txt_password, password);
        click(btnLogin);
    }

    public void loginToPage(String username, String encodedPassword, String url) {
        byte[] decodedPassword = Base64.getDecoder().decode(encodedPassword);
        String password = new String(decodedPassword);
        waitForPageLoad();
        setText(txt_username, username);
        setText(txt_password, password);
        click(btnLogin);
        navigateToPage(url);
    }
    public void verifyLoginIsSuccessfully(String username){
        assertText(lblLoggedUsername, username);
    }
}
