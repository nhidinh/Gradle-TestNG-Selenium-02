package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * User: Nhi Dinh
 * Date: 22/11/2018
 */
public class Generator {
    String[] wordNames = {"new","post","collection","Pen","tutorial","vacation"};
    public int rndNo(){
        Random ran = new Random();
        return ran.nextInt(wordNames.length);
    }
    public String rndString(){
        return wordNames[rndNo()]+" "+wordNames[rndNo()]+" ";
    }
    public String getDateTime(){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return timeStamp;
    }
    public String title(){
        return "Title: "+rndString()+getDateTime();
    }
    public String body(){
        return "======= Body: "+ rndString()+ getDateTime();
    }
}
