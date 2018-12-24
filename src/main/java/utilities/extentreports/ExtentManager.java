package utilities.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.data.InitData;
import utilities.logger.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */
public class ExtentManager {
    private static ExtentReports extent;
    private static Platform platform = InitData.PLATFORM;
    private static Date date = new Date();
    private static String timeStamp = InitData.TIMESTAMP;
    private static String reportFileName = "ExtentReport3-"+timeStamp+".html";
    private static String macPath = System.getProperty("user.dir")+ "/TestReport";
    private static String windowsPath = System.getProperty("user.dir")+ "\\extentreports" + "\\Report-"+timeStamp;
    private static String macReportFileLoc = macPath + "/" + reportFileName;
    private static String winReportFileLoc = windowsPath + "\\" + reportFileName;

    public static ExtentReports getInstance() {
        if (extent == null)
            extent = createInstance();

    return extent;
    }

    //Create an extent report instance
    public static ExtentReports createInstance() {
        String fileName = getReportFileLocation(platform);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }

    //Select the extent report file location based on platform
    private static String getReportFileLocation (Platform platform) {
        String reportFileLocation = null;
        switch (platform) {
            case MAC:
                reportFileLocation = macReportFileLoc;
                createReportPath(macPath);
                Log.info("ExtentReport Path for MAC: " + macPath + "\n");
                break;
            case WINDOWS:
                reportFileLocation = winReportFileLoc;
                createReportPath(windowsPath);
                Log.info("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
                break;
            default:
                Log.info("ExtentReport path has not been set! There is a problem!\n");
                break;
        }
        return reportFileLocation;
    }

    //Create the report path if it does not exist
    private static void createReportPath (String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                Log.info("Directory: " + path + " is created!");
            } else {
                Log.info("Failed to create directory: " + path);
            }
        } else {
            Log.info("Directory already exists: " + path);
        }
    }

    public static void turnOffFreemaker(){
        Properties properties = System.getProperties();
        properties.setProperty("-Dorg.freemarker.loggerLibrary", "none");
    }



    // Get Screenshot
    public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String destination = windowsPath + "\\FailedTestsScreenshots\\"+screenshotName+timeStamp+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        String base = System.getProperty("user.dir")+ "\\extentreports";
        String relative = new File(base).toURI().relativize(new File(destination).toURI()).getPath();
        return relative;
    }

    public static String getBase64Screenshot(WebDriver driver, String screenshotName) throws IOException {
        String encodedBase64 = null;
        FileInputStream fileInputStream = null;
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String destination = windowsPath + "\\FailedTestsScreenshots\\"+screenshotName+timeStamp+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);

        try {
            fileInputStream =new FileInputStream(finalDestination);
            byte[] bytes =new byte[(int)finalDestination.length()];
            fileInputStream.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return encodedBase64;
    }
}
