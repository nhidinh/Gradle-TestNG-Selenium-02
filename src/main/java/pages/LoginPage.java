package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
    WebElement txt_username;
    @FindBy(id = "user_pass")
    @CacheLookup
    WebElement txt_password;
    @FindBy(id = "wp-submit")
    @CacheLookup
    WebElement btnLogin;
    @FindBy(xpath = "//li[@id='wp-admin-bar-my-account']/a[@class='ab-item']/span[@class='display-name']")
    WebElement lblLoggedUsername;

    // ------------PAGE METHODS ---------------------

    public void login(String username, String encodedPassword){
        byte[] decodedPassword = Base64.getDecoder().decode(encodedPassword);
        String password = new String(decodedPassword);

        setText(txt_username, username);
        setText(txt_password, password);
        click(btnLogin);
    }

    public void verifyLoginIsSuccessfully(String username){
        Assert.assertEquals(getText(lblLoggedUsername),username);
    }
}
