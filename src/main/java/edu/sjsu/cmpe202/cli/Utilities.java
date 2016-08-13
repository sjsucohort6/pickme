package edu.sjsu.cmpe202.cli;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * @author rwatsh on 8/6/16.
 */
public class Utilities {

    public final static String DATE_FORMAT = "yyyy-MM-dd";
    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static boolean isValidDate(String date)
    {
        return isValidDateInternal(date, DATE_FORMAT);
    }

    private static boolean isValidDateInternal(String date, String dateFormat) {
        try {
            DateFormat df = new SimpleDateFormat(dateFormat);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    


    public static boolean isValidDateTime(String date)
    {

        return isValidDateInternal(date, DATE_TIME_FORMAT);
    }

    private static boolean isValidInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }



    public static String getIntStr(String displayMsg) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(displayMsg);
        String retStr = scanner.nextLine();
        while (!Utilities.isValidInt(retStr)) {
            System.out.println(displayMsg);
            retStr = scanner.nextLine();
        }
        return retStr;
    }

    public static String getDateStr(String displayMsg) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(displayMsg);
        String retStr = scanner.nextLine();
        while (!Utilities.isValidDate(retStr)) {
            System.out.println(displayMsg);
            retStr = scanner.nextLine();
        }
        return retStr;
    }
    
    public static String getDateTimeString(String displayMsg) {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println(displayMsg);
    	String retStr = scanner.nextLine();
    	while (!Utilities.isValidDateTime(retStr)) {
            System.out.println(displayMsg);
            retStr = scanner.nextLine();
        }
        return retStr;
    }
}
