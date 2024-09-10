package testdata;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestData {


    public static String generateLogin(){
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        return "FancyLogginn"+ timeStamp;
    }

    public static String generatePassword(){
        return "12345";
    }

    public static String generateFirstName(){
        return "CoolName";
    }

    public static String generateLastName(){
        return "CoolLastName";
    }

    public static String generateAddress(){
        return "Moscow is the capital of GB";
    }
    public static String generateMetroStation(){
        return "Сокольники";
    }
    public static String generatePhone(){
        return "9063166338";
    }
    public static int generateRentTime(){
        return 3;
    }
    public static String generateDeliveryDate(){
        return "2020-06-06";
    }
    public static String generateComment(){
        return "превед медвед";
    }
}
