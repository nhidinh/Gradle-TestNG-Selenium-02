package pages.post_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.base_pages.BasePage;

/**
 * User: Nhi Dinh
 * Date: 22/11/2018
 */
public class AddNewPostPage extends BasePage {
    public AddNewPostPage(WebDriver driver) {
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

    private AddMediaForm clickAddMediaButton() {
        click(btnAddMedia);
        return new AddMediaForm(driver);
    }
    public void addMediaToPost(String filePath){
        AddMediaForm addMediaForm = clickAddMediaButton();
        addMediaForm.selectUploadFilesTab();
        addMediaForm.addMediaToPostByJS(filePath);
        addMediaForm.clickInsertMediaButton();
    }

    public void addGalleryToPost(String filePath)  {
        AddMediaForm addMediaForm = clickAddMediaButton();
        addMediaForm.clickCreateGalleryLink();
        addMediaForm.selectUploadFilesTab();
        addMediaForm.addMediaToPostByJS(filePath);
        addMediaForm.clickCreateNewGallery();
        addMediaForm.clickInserGalleryButton();
    }

    public EditPostPage addNewPost(String title, String body) {
        setPostTitle(title);
        setPostBody(body);
        waitElementVisibility(lnkPermalink);
        click(btnPublish);
        return new EditPostPage(driver);
    }

    public void verifyNewPostIdAdded(String title) {
        String message = getText(lblMessage);
        Assert.assertTrue(message.contains("Post published"));
        click(lnkViewPost);
        PostDetailPage detailPage = new PostDetailPage(driver);
        String postedTitle = detailPage.getPostTitle();
        Assert.assertEquals(postedTitle, title);
        backToPreviousPage();
    }

}
