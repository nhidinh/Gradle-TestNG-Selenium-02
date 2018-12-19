package tests;

import org.testng.annotations.Test;
import pages.media_pages.MediaLibraryPage;
import utilities.Links;

/**
 * User: Nhi Dinh
 * Date: 29/11/2018
 */
public class MediaTests extends BaseTest {

    @Test(description = "Upload Image by Browse Button")
    public void uploadImageByBrowseButton(){
        String imagePath = Links.IMAGE_PATH;

        MediaLibraryPage mediaLibraryPage = Page.LeftNavigation().NavigateToMediaPage();
        mediaLibraryPage.clickAddNewButton();
        mediaLibraryPage.uploadFileByBrowseButton(imagePath);
    }
    @Test (description = "Upload Image")
    public void uploadImage(){
        String imagePath = Links.IMAGE_PATH;

        Page.LeftNavigation().NavigateToMediaPage();
        Page.MediaLibrary().clickAddNewButton();
        Page.MediaLibrary().uploadFile(imagePath);
    }
    @Test(description = "Upload Image By Drag Drop")
    public void uploadImageByDragDrop() {
        String imagePath = Links.IMAGE_PATH;
        Page.LeftNavigation().NavigateToMediaPage();
        Page.MediaLibrary().clickAddNewButton();
        Page.MediaLibrary().uploadFileByDragDrop(imagePath);
    }
}
