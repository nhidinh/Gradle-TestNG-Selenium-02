package pages.media_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_pages.BasePage;
import utilities.customkeywords.UploadFiles;

import java.awt.*;

/**
 * User: Nhi Dinh
 * Date: 29/11/2018
 */
public class MediaLibrary_Page extends BasePage {
    public MediaLibrary_Page(WebDriver driver) {
        super(driver);
    }
    //=============VARIABLES====================//
    private UploadFiles uploadFiles = new UploadFiles();

    //=============ELEMENTS=====================//
    @FindBy(xpath = "//div[@id='wp-media-grid']//a[@role='button' and text()='Add New']")
    private WebElement btnAddNew;

    @FindBy(xpath = "//button[contains(@class,'browser') and text()='Select Files']")
    private WebElement btnSelectFiles;

    @FindBy(id = "wpcontent")
    private WebElement ctn_DropFile;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement txt_UploadFile;

    public void clickAddNewButton() {
        click(btnAddNew);
    }

    public void uploadFileByBrowseButton(String filePath){
        try {
            uploadFiles.uploadByBrowseButton(filePath, btnSelectFiles);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void uploadFileByDragDrop(String filePath) {
        uploadFiles.uploadByDragDrop(filePath,ctn_DropFile,driver);
    }

    public void uploadFile(String filePath) {
        uploadFiles.uploadFile(filePath, txt_UploadFile, driver);
    }
}
