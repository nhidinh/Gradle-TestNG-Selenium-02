package pages.media_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_pages.BasePage;
import utilities.CustomKeywords.UploadFiles;

import java.awt.*;

/**
 * User: Nhi Dinh
 * Date: 29/11/2018
 */
public class MediaLibraryPage extends BasePage {
    public MediaLibraryPage(WebDriver driver) {
        super(driver);
    }
    //=============VARIABLES====================//
    UploadFiles uploadFiles = new UploadFiles();

    //=============ELEMENTS=====================//
    @FindBy(xpath = "//div[@id='wp-media-grid']//a[@role='button' and text()='Add New']")
    WebElement btnAddNew;

    @FindBy(xpath = "//button[contains(@class,'browser') and text()='Select Files']")
    WebElement btnSelectFiles;

    @FindBy(id = "wpcontent")
    WebElement ctn_DropFile;

    @FindBy(xpath = "//input[@type='file']")
    WebElement txt_UploadFile;

    public void clickAddNewButton() {
        click(btnAddNew);
    }

    public void uploadFileByBrowseButton(String filePath) throws InterruptedException, AWTException {
        uploadFiles.uploadByBrowseButton(filePath, btnSelectFiles);
    }

    public void uploadFileByDragDrop(String filePath) {
        uploadFiles.uploadByDragDrop(filePath,ctn_DropFile,driver);
    }

    public void uploadFile(String filePath) {
        uploadFiles.UploadFile(filePath, txt_UploadFile, driver);
    }

}
