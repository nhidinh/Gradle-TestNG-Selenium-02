package utilities;

import org.openqa.selenium.Platform;
import utilities.data.InitData;

import java.io.File;

/**
 * User: Nhi Dinh
 * Date: 7/01/2019
 */
public class FileHelper {
    private static Platform platform = InitData.PLATFORM;

    private static String fileLocation;
    public static String MAC_fileLocation;
    public static String WIN_fileLocation;
    public static String MAC_DirectoryPath;
    public static String WIN_DirectoryPath;


    //Select file location based on platform
    public static String getFileLocation() {
        switch (platform) {
            case MAC:
                fileLocation = MAC_fileLocation;
                createDirectory(MAC_DirectoryPath);
                break;
            case WINDOWS:
                fileLocation = WIN_fileLocation;
                createDirectory(WIN_DirectoryPath);
                break;
            default:
                System.out.println("File path has not been set! There is a problem!\n");
                break;
        }
        return fileLocation;
    }

    //Create the directory path if it does not exist
    public static void createDirectory(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdirs()) {
                System.out.println("Directory: " + path + " is created!");
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        }
    }

}
