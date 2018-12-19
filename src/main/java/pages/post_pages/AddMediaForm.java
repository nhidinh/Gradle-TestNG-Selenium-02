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
    private WebElement txt_UploadFile;
    @FindBy(xpath = "//button[text()='Insert into post']")
    private WebElement btnInsertMedia;
    @FindBy(xpath = "//div[@class='media-router']//a[text()='Upload Files']")
    private WebElement tabUploadFiles;
    @FindBy(xpath = "//button[text()='Select Files']")
    private WebElement btnSelectFiles;

    @FindBy(xpath = "//a[text()='Create Gallery']")
    private WebElement lnkCreateGallery;

    @FindBy(xpath = "//button[text()='Create a new gallery']")
    private WebElement btnCreateNewGallery;

    @FindBy(xpath = "//div[contains(@class,'media-selection')]")
    private WebElement ctn_Selection;

    @FindBy(xpath = "//button[text()='Insert gallery']")
    private WebElement btnInsertGallery;

    //--------- VARIABLE ---------------//
    UploadFiles uploadFiles = new UploadFiles();


    //=========METHODS==================//
    public void clickCreateGalleryLink() {
        click(lnkCreateGallery);
    }


    public void clickCreateNewGallery(){
        waitForElementIsClickable(btnCreateNewGallery);
        click(btnCreateNewGallery);
    }
    public void clickInsertMediaButton() {
        waitForElementIsClickable(btnInsertMedia);
        click(btnInsertMedia);
    }

    public void clickInserGalleryButton(){
        waitForElementIsClickable(btnInsertGallery);
        click(btnInsertGallery);
    }

    public void selectUploadFilesTab() {
        click(tabUploadFiles);
    }

    public void addMediaToPostByJS(String filePath) {
        uploadFiles.uploadFile(filePath, txt_UploadFile, driver);
    }

    public void addMediaToPostByBrowseButton(String filePath) throws AWTException {
        uploadFiles.uploadByBrowseButton(filePath, btnSelectFiles);
    }
}
