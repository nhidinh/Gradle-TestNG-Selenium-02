package utilities.logger;

import utilities.data.InitData;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * User: Nhi Dinh
 * Date: 5/12/2018
 */
public class SystemLog {
    private static String timeStamp = InitData.TIMESTAMP;

    public static void setOutLog() throws FileNotFoundException {
        String logFileName = timeStamp+"_Sys.log";
        System.setOut(new PrintStream(new FileOutputStream("logs/" + logFileName)));
    }
}
