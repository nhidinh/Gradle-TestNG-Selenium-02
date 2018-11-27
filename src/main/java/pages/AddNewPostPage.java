package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utilities.Generator;

/**
 * User: Nhi Dinh
 * Date: 22/11/2018
 */
public class AddNewPostPage extends BasePage {
    public AddNewPostPage(WebDriver driver){
        super(driver);
    }

    //----------WEB ELEMENT --------------//
    @FindBy(id = "title")
    WebElement txtTitle;
    @FindBy(id = "content_ifr")
    WebElement frmBody;
    @FindBy(xpath = "//body[@id='tinymce']")
    WebElement txtBody;
    @FindBy(id = "publish")
    WebElement btnPublish;
    @FindBy(xpath = "//div[@id='message']//p")
    WebElement lblMessage;
    @FindBy(xpath = "//div[@id='message']//a[text()='View post']")
    WebElement lnkViewPost;
    @FindBy(xpath = "//div[@id='edit-slug-box']//span[@id='sample-permalink']")
    WebElement lnkPermalink;

    //--------- VARIABLE ---------------//
    Generator generator = new Generator();

    //----------METHODS------------------//

    public void setPostTitle(String title){
        setText(txtTitle, title);
    }
    public void setPostBody(String body) {
        driver.switchTo().frame(frmBody);
        setText(txtBody, body);
        driver.switchTo().defaultContent();
    }
    public void addNewPost(String title, String body){
        setPostTitle(title);
        setPostBody(body);
        waitElementVisibility(lnkPermalink);
        click(btnPublish);
    }
    public void verifyNewPostIdAdded(String title){
        String message = getText(lblMessage);
        Assert.assertTrue(message.contains("Post published"));
        click(lnkViewPost);
        PostDetailPage detailPage = new PostDetailPage(driver);
        String postedTitle = detailPage.getPostTitle();
        Assert.assertEquals(postedTitle, title);
    }

}
