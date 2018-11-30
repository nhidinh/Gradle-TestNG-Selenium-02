package utilities.CustomKeywords;

import org.openqa.selenium.*;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import static utilities.CustomKeywords.JavaScriptKeyWords.getJsDndHelper;

/**
 * User: Nhi Dinh
 * Date: 29/11/2018
 */
public class UploadFiles {
    public void uploadByBrowseButton(String filePath, WebElement browseButton) throws AWTException {
        setClipboardData(filePath);
//        Actions builder = new Actions(driver);
//        Action myAction = builder.click(btnSelectFiles).release().build();
//        myAction.perform();
        browseButton.click();

        Robot robot = new Robot();
        robot.setAutoDelay(250);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void UploadFile(String filePath, WebElement inputFile, WebDriver driver) {
        inputFile.sendKeys(filePath);
    }

    public void uploadBySikuli(String filePath) {
    }

    public void uploadByDragDrop(String filePath, WebElement target, WebDriver driver) {
        File uploadFile = new File(filePath);
        if(!uploadFile.exists()){
            throw new WebDriverException("File not found" + filePath);
        }
        int offsetX = 0;
        int offsetY = 0;
        JavascriptExecutor executor = (JavascriptExecutor)driver;
//        WebElement input = ((WebElement) executor.executeScript(getJsDndHelper(), target, offsetX, offsetY));
        String inputID = ((String) executor.executeScript(getJsDndHelper(), target, offsetX, offsetY));
        WebElement input = driver.findElement(By.id(inputID));
        input.sendKeys(filePath);
        System.out.println(input);
    }

    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

}
