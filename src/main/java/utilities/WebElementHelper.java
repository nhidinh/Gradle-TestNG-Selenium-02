package utilities;

import org.openqa.selenium.WebElement;

/**
 * User: Nhi Dinh
 * Date: 11/12/2018
 */
public class WebElementHelper {
    public WebElementHelper(){}

    public String getLocatorOfElement(WebElement element){
        String elementName = element.toString();
        int index = elementName.indexOf("> ") + 2;
        return "["+ elementName.substring(index);
    }
}
