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

    @Test(description = "Upload Image by Browse Button")
    public void uploadImageByBrowseButton() throws AWTException, InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        MediaLibraryPage mediaLibraryPage = dashboardPage.navigateToMediaPage();

        mediaLibraryPage.clickAddNewButton();
        String imagePath = System.getProperty("user.dir") + "\\media\\dog.jpg";
        Log.info(imagePath);
        mediaLibraryPage.uploadFileByBrowseButton(imagePath);
    }
    @Test (description = "Upload Image")
    public void uploadImage(){
        DashboardPage dashboardPage = new DashboardPage(driver);
        MediaLibraryPage mediaLibraryPage = dashboardPage.navigateToMediaPage();

        mediaLibraryPage.clickAddNewButton();
        String imagePath = System.getProperty("user.dir") + "\\media\\dog.jpg";
        mediaLibraryPage.uploadFile(imagePath);
    }
    @Test(description = "Upload Image By Drag Drop")
    public void uploadImageByDragDrop() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        MediaLibraryPage mediaLibraryPage = dashboardPage.navigateToMediaPage();

        mediaLibraryPage.clickAddNewButton();
        String imagePath = System.getProperty("user.dir") + "\\media\\cat.jpg";
        mediaLibraryPage.uploadFileByDragDrop(imagePath);
    }
}
