package utilities.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import utilities.data.InitData;

/**
 * User: Nhi Dinh
 * Date: 4/12/2018
 */
public class Log {
    private static Logger log = Logger.getLogger(Log.class.getName());
    private static String timeStamp = InitData.TIMESTAMP;

    public static void innitLog() {
        String logFileName = timeStamp + ".log";
        System.setProperty("logFileName", logFileName);
        DOMConfigurator.configure("log4j.xml");
    }

    public static void startLog() {
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

    public static void warn(String message) {
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
