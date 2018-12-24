package utilities.data;

import org.openqa.selenium.Platform;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User: Nhi Dinh
 * Date: 4/12/2018
 */
public class InitData {
    public static Platform PLATFORM = getCurrentPlatform();
    public static String PLATFORM_NAME;
    public static Date DATE = new Date();
    public static String TIMESTAMP = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    public static String MAC_PATH = System.getProperty("user.dir")+ "/TestReport";
    public static String WINDOW_PATH = System.getProperty("user.dir")+ "\\extentreports" + "\\Report-"+TIMESTAMP;

    //Get current platform
    private static Platform getCurrentPlatform () {
        if (PLATFORM == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                PLATFORM_NAME = "WINDOWS";
                PLATFORM = Platform.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                PLATFORM_NAME = "LINUX";
                PLATFORM = Platform.LINUX;
            } else if (operSys.contains("mac")) {
                PLATFORM_NAME = "MAC";
                PLATFORM = Platform.MAC;
            }
        }
        return PLATFORM;
    }
}
