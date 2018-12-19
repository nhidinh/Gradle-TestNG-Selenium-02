package utilities.driver;


/**
 * User: Nhi Dinh
 * Date: 5/12/2018
 */
public class DriverManagerFactory {
    public static DriverManager getManager(DriverType type){
        DriverManager driverManager;
        switch (type){
            default:
                driverManager = new ChromeDriverManager();
                break;
            case firefox:
                driverManager = new FirefoxDriverManager();
                break;
            case ie:
                driverManager = new IEDriverManager();
                break;
            case edge:
                driverManager = new EdgeDriverManager();
                break;

        }
        return driverManager;
    }
}
