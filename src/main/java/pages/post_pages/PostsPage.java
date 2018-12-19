package pages.post_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.base_pages.BasePage;

/**
 * User: Nhi Dinh
 * Date: 22/11/2018
 */
public class PostsPage extends BasePage {
    public PostsPage(WebDriver driver){
        super(driver);
    }

    //-------------ELEMENT-------------//
    @FindBy(xpath = "//div[@class='wrap']//a[text()='Add New']")
    private WebElement btnAddNewBy;
    @FindBy(xpath = "//div[@id='message']//p")
    private WebElement msgDeletedPost;

    private WebElement getTrashButton(String postTitle){
        String xpathBtnTrash = "//span[contains(@class,'trash')]//a[@class='submitdelete' and contains(@aria-label, '"+postTitle+"')]";
        return driver.findElement(By.xpath(xpathBtnTrash));
    }

    private WebElement getRowPostElement(String postTitle){
        String xpathPost = "//td[@data-colname='Title']//strong/a[text()='" + postTitle + "']";
        return driver.findElement(By.xpath(xpathPost));
    }

    //-------------METHODS------------//
    public AddNewPostPage clickAddNewPost(){
        click(btnAddNewBy);
        return new AddNewPostPage(driver);
    }
    public void deleteAPostByTile(String title){
        findPostByTitle(title);
        WebElement btnTrash = getTrashButton(title);
        click(btnTrash);
    }
    public void findPostByTitle(String postTitle){
        WebElement tdPost = getRowPostElement(postTitle);
        hoverMouseToElement(tdPost);
    }
    public void verifyPostIsMovedToTrash(){
        String message = getText(msgDeletedPost);
        Assert.assertTrue(message.contains("1 post moved to the Trash"));
    }
}
