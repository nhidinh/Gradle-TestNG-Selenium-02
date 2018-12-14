package utilities;

import java.util.Properties;

/**
 * User: Nhi Dinh
 * Date: 12/12/2018
 */
public class CustomSystemProperties {
    public CustomSystemProperties(){
    }
    public void customProperty(String property, String value){
        Properties properties = System.getProperties();
        properties.setProperty(property, value);
    }
}
