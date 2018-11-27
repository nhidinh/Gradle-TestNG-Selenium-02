package utils.DataSetup;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import pages.DashboardPage;
import utilities.Generator;

/**
 * User: Nhi Dinh
 * Date: 23/11/2018
 */
public class DataSetUp{
    public void setUpPostData(ITestContext context, WebDriver driver){
        DashboardPage dashboardPage = new DashboardPage(driver);
        Generator generator = new Generator();
        String body = generator.body();
        String title = generator.title();

        context.setAttribute("dashboardPage", dashboardPage);
        context.setAttribute("title", title);
        context.setAttribute("body", body);
    }
}
