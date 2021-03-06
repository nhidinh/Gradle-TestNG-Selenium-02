package pages.base_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.logger.Log;

/**
 * User: Nhi Dinh
 * Date: 17/12/2018
 */
public class TopNavigation extends BasePage {
    public TopNavigation(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//li[@id='wp-admin-bar-my-account']/a[@class='ab-item']/span[@class='display-name']")
    private WebElement lblLoggedUsername;
    @FindBy(id = "wp-admin-bar-user-info")
    private WebElement lnkUserInfo;
    @FindBy(id = "wp-admin-bar-logout")
    private WebElement lnkLogOut;


    public void LogOut(){
        boolean isLoggedIn = false;
        try{
            isLoggedIn = isElementPresent(lblLoggedUsername);
        }catch (Exception e){
            if(e.getMessage().contains("Unable to locate element")){
                Log.error("Not Log out as User is not logged in!");
            }
        }
        if(isLoggedIn){
            hoverMouseToElement(lblLoggedUsername);
            click(lnkLogOut);
        }
    }

}
