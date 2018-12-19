package pages;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import pages.base_pages.BasePage;
import utilities.Links;

/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */
public class DashboardPage extends BasePage {
    //Constructor
    public DashboardPage(WebDriver driver){
        super(driver);
    }

    //------------VARIABLE------//
    Screen screen = new Screen();
    //------------ELEMENT-------//

    Pattern btnCustomizeSite =new Pattern(System.getProperty("user.dir")+"/src/object_images/btnCustomizeSite.png");


    //------------METHODS--------//
    public DashboardPage Goto(){
        navigateToPage(Links.URL_DASHBOARD);
        return new DashboardPage(driver);
    }

    public void clickButtonCustomizeSite() throws FindFailed {
        screen.click(btnCustomizeSite);
    }

}
