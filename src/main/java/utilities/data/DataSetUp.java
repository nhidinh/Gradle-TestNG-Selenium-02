package utilities.data;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import utilities.generator.DataGenerator;

/**
 * User: Nhi Dinh
 * Date: 23/11/2018
 */
public class DataSetUp{
    public void setUpPostData(ITestContext context, WebDriver driver){
        DataGenerator dataGenerator = new DataGenerator();
        String body = dataGenerator.body();
        String title = dataGenerator.title();

        context.setAttribute("title", title);
        context.setAttribute("body", body);
    }
}
