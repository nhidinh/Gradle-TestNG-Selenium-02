package tests;

import commontests.CommonLoginTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.media_pages.MediaLibraryPage;

import java.awt.*;

/**
 * User: Nhi Dinh
 * Date: 29/11/2018
 */
public class MediaTests extends BaseTest {
    @BeforeClass
    @Parameters({"username", "encodedPassword"})
    public void login(String username, String encodedPassword) throws InterruptedException {
        CommonLoginTest commonLoginTest = new CommonLoginTest();
        commonLoginTest.LoginSuccessfully(username, encodedPassword, driver);
    }

    @Test(enabled = false)
    public void uploadImageByBrowseButton() throws AWTException, InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        MediaLibraryPage mediaLibraryPage = dashboardPage.navigateToMediaPage();

        mediaLibraryPage.clickAddNewButton();
        String imagePath = System.getProperty("user.dir") + "\\media\\dog.jpg";
        System.out.println(imagePath);
        mediaLibraryPage.uploadFileByBrowseButton(imagePath);
    }
    @Test (enabled = false)
    public void uploadImage() throws AWTException, InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        MediaLibraryPage mediaLibraryPage = dashboardPage.navigateToMediaPage();

        mediaLibraryPage.clickAddNewButton();
        String imagePath = System.getProperty("user.dir") + "\\media\\dog.jpg";
        mediaLibraryPage.uploadFile(imagePath);
    }
    @Test(enabled = true)
    public void uploadImageByDragDrop() throws AWTException, InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        MediaLibraryPage mediaLibraryPage = dashboardPage.navigateToMediaPage();

        mediaLibraryPage.clickAddNewButton();
        String imagePath = System.getProperty("user.dir") + "\\media\\dog.jpg";
        mediaLibraryPage.uploadFileByDragDrop(imagePath);
    }
}
