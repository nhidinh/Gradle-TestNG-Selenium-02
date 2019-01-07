package utilities.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import utilities.FileHelper;
import utilities.data.InitData;

import static org.apache.commons.io.FilenameUtils.separatorsToSystem;

/**
 * User: Nhi Dinh
 * Date: 4/12/2018
 */
public class Log {
    private static Logger log;
    private static String configFile_path;

    public static void initLogger() {
        setLogFileLocation();
        getLog4jConfigurationFile();
        log = Logger.getLogger(Log.class.getName());
    }

    private static void setLogFileLocation() {
        String logDir_path = separatorsToSystem(InitData.LOG_DIR_PATH);
        String logFile_path = separatorsToSystem(InitData.LOG_FILE_PATH);
        configFile_path = separatorsToSystem(InitData.LOG_CONFIG_FILE_PATH);

        FileHelper.createDirectory(logDir_path);
        System.setProperty("logLocation", logFile_path);
        System.out.println("Log file is created at: " + logFile_path);
    }

    private static void getLog4jConfigurationFile() {
        DOMConfigurator.configure(configFile_path);
    }

    public static void startLog() {
        initLogger();
        log.info("Start Log...");
    }

    public static void endLog() {
        log.info("End Log...");
    }

    public static void startTestCase(String sTestCaseName) {
        log.info("********************* START TESTCASE: " + sTestCaseName + "*********************");
    }

    public static void endTestCase(String sTestCaseName) {
        log.info("********************* END OF TEST CASE: " + sTestCaseName + "*********************");
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void warning(String message) {
        log.warn(message);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void fatal(String message) {
        log.fatal(message);
    }

    public static void debug(String message) {
        log.debug(message);
    }

    public static void trace(String message) {
        log.trace(message);
    }
}
