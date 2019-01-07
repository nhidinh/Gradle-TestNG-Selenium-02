package utilities.data;

import org.openqa.selenium.Platform;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.apache.commons.io.FilenameUtils.separatorsToSystem;

/**
 * User: Nhi Dinh
 * Date: 4/12/2018
 */
public class InitData {
    public static Platform PLATFORM = getCurrentPlatform();
    public static String PLATFORM_NAME;
    public static String TIMESTAMP = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    public static String CURRENT_DIR = System.getProperty("user.dir");

    //LOG DIRECTORY AND FILE PATH
    private static String FILE_NAME_LOG = TIMESTAMP + ".log";
    public static String LOG_DIR_PATH = CURRENT_DIR + "/logs";
    public static String LOG_FILE_PATH = LOG_DIR_PATH + "/" + FILE_NAME_LOG;
    public static String LOG_CONFIG_FILE_PATH = CURRENT_DIR + "/framework/src/main/java/com/hansencx/solutions/util/logger/log4j.xml";

    //REPORT DIRECTORY PATH
    public static String FILE_NAME_REPORT = "REPORT-"+TIMESTAMP+".html";
    public static String REPORT_DIR_PATH = CURRENT_DIR+ "\\test-report" + "\\Report-"+TIMESTAMP;
    public static String REPORT_FILE_PATH = REPORT_DIR_PATH +"/"+FILE_NAME_REPORT;

    ///TEST DATA SOURCE PATH
    public static String TEST_DATASOURCE_PATH = CURRENT_DIR + "\\src\\test\\resources\\" ;
    private static String DATA_DIR = "//wordpress//";

    public static String getDataDirectory(){
        return DATA_DIR = separatorsToSystem(DATA_DIR);
    }
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
