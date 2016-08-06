package edu.sjsu.cmpe202.cli;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author rwatsh on 8/6/16.
 */
public class CommonValidations {
    private final static String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidContact(String phone) {
        try {
            Integer.parseInt(phone);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
