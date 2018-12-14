package pages.post_pages;

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
public class AddMediaForm extends BasePage {
    public AddMediaForm(WebDriver driver) {
        super(driver);
    }

    //========ELEMENTS=======//
    @FindBy(xpath = "//input[@type='file']")
    WebElement txt_UploadFile;
    @FindBy(xpath = "//button[text()='Insert into post']")
    WebElement btnInsertMedia;
    @FindBy(xpath = "//div[@class='media-router']//a[text()='Upload Files']")
    WebElement tabUploadFiles;
    @FindBy(xpath = "//button[text()='Select Files']")
    WebElement btnSelectFiles;

    @FindBy(xpath = "//a[text()='Create Gallery']")
    WebElement lnkCreateGallery;

    @FindBy(xpath = "//button[text()='Create a new gallery']")
    WebElement btnCreateNewGallery;

    @FindBy(xpath = "//div[contains(@class,'media-selection')]")
    WebElement ctn_Selection;

    //--------- VARIABLE ---------------//
    UploadFiles uploadFiles = new UploadFiles();


    //=========METHODS==================//
    public void clickInsertMediaButton() {
        click(btnInsertMedia);
    }

    public void clickCreateGalleryLink() {
        click(lnkCreateGallery);
    }

    public void addMediaToPostByJS(String filePath) {
        uploadFiles.uploadFile(filePath, txt_UploadFile, driver);
    }

    public void addMediaToPostByBrowseButton(String filePath) throws AWTException {
        uploadFiles.uploadByBrowseButton(filePath, btnSelectFiles);
    }

    public void selectUploadFilesTab() {
        click(tabUploadFiles);
    }

    public void clickCreateNewGallery() throws InterruptedException {
        waitForElementIsClickable(btnCreateNewGallery);
        click(btnCreateNewGallery);
    }
}
