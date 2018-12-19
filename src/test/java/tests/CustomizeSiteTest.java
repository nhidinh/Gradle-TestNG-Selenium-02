package tests;

import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;

/**
 * User: Nhi Dinh
 * Date: 30/11/2018
 */
public class CustomizeSiteTest extends BaseTest {
    @Test
    public void OpenCustomizeSite() throws FindFailed {
        Page.Dashboard().clickButtonCustomizeSite();
    }
}
