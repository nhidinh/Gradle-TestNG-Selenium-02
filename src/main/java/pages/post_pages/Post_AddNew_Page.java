package pages.post_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.IPage;
import pages.base_pages.BasePage;

/**
 * User: Nhi Dinh
 * Date: 22/11/2018
 */
public class Post_AddNew_Page extends BasePage implements IPage {
    public Post_AddNew_Page(WebDriver driver) {
        super(driver);
    }

    //----------WEB ELEMENT --------------//
    @FindBy(id = "title")
    private WebElement txtTitle;
    @FindBy(id = "content_ifr")
    private WebElement frmBody;
    @FindBy(xpath = "//body[@id='tinymce']")
    private WebElement txtBody;
    @FindBy(id = "publish")
    private WebElement btnPublish;
    @FindBy(xpath = "//div[@id='message']//p")
    private WebElement lblMessage;
    @FindBy(xpath = "//div[@id='message']//a[text()='View post']")
    private WebElement lnkViewPost;
    @FindBy(xpath = "//div[@id='edit-slug-box']//span[@id='sample-permalink']")
    private WebElement lnkPermalink;
    @FindBy(id = "insert-media-button")
    private WebElement btnAddMedia;

    //--------- VARIABLE ---------------//

    //----------METHODS------------------//

    private void setPostTitle(String title) {
        setText(txtTitle, title);
    }

    private void setPostBody(String body) {
        driver.switchTo().frame(frmBody);
        setText(txtBody, body);
        driver.switchTo().defaultContent();
    }

    private Media_Add_Form clickAddMediaButton() {
        click(btnAddMedia);
        return new Media_Add_Form(driver);
    }
    public void addMediaToPost(String filePath){
        Media_Add_Form mediaAddForm = clickAddMediaButton();
        mediaAddForm.selectUploadFilesTab();
        mediaAddForm.addMediaToPostByJS(filePath);
        mediaAddForm.clickInsertMediaButton();
    }

    public void addGalleryToPost(String filePath)  {
        Media_Add_Form mediaAddForm = clickAddMediaButton();
        mediaAddForm.clickCreateGalleryLink();
        mediaAddForm.selectUploadFilesTab();
        mediaAddForm.addMediaToPostByJS(filePath);
        mediaAddForm.clickCreateNewGallery();
        mediaAddForm.clickInserGalleryButton();
    }

    public Post_Edit_Page addNewPost(String title, String body) {
        setPostTitle(title);
        setPostBody(body);
        waitElementVisibility(lnkPermalink);
        click(btnPublish);
        return new Post_Edit_Page(driver);
    }

    public void verifyNewPostIdAdded(String title) {
        String message = getText(lblMessage);
        Assert.assertTrue(message.contains("Post published"));
        click(lnkViewPost);
        Post_Detail_Page detailPage = new Post_Detail_Page(driver);
        String postedTitle = detailPage.getPostTitle();
        Assert.assertEquals(postedTitle, title);
        backToPreviousPage();
    }

    @Override
    public void testmethod() {
        System.out.println("this is test method");
    }
}
