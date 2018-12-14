package utilities.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * User: Nhi Dinh
 * Date: 5/12/2018
 */
public class EdgeDriverManager extends DriverManager {
    @Override
    public WebDriver createDriver(){
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
}
