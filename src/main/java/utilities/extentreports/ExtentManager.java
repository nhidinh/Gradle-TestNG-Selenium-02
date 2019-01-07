package utilities.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.FileHelper;
import utilities.data.InitData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.apache.commons.io.FilenameUtils.separatorsToSystem;

/**
 * User: Nhi Dinh
 * Date: 21/11/2018
 */
public class ExtentManager {
    private static ExtentReports extent;
    private static String reportDir_path;

    public static ExtentReports getInstance() {
        if (extent == null)
            extent = createInstance();

        return extent;
    }

    private static String setReportFileName() {
        reportDir_path = separatorsToSystem(InitData.REPORT_DIR_PATH);
        String reportFile_path = separatorsToSystem(InitData.REPORT_FILE_PATH);
        return reportFile_path;
    }

    //Create an extent report instance
    public static ExtentReports createInstance() {
        String fileName = setReportFileName();
        FileHelper.createDirectory(reportDir_path);

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

    public static String getBase64Screenshot(WebDriver driver, String screenshotName) throws IOException {
        String encodedBase64 = null;
        FileInputStream fileInputStream = null;
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String destination = reportDir_path + "\\FailedTestsScreenshots\\" + screenshotName + InitData.TIMESTAMP + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);

        try {
            fileInputStream = new FileInputStream(finalDestination);
            byte[] bytes = new byte[(int) finalDestination.length()];
            fileInputStream.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return encodedBase64;
    }
}
