package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//Default
	private static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");//Keep year only

    // read a date string and parse/convert to a date!
    public static Date parseDate(String dateStr) throws ParseException {
            Date theDate = formatter.parse(dateStr);
            return theDate;
    }
    
    //read a date string, specify format and parse it!
    public static Date parseDate(String dateStr, String formatStr) throws ParseException {
    	SimpleDateFormat newFormatter = new SimpleDateFormat(formatStr);
    	Date theDate = newFormatter.parse(dateStr);
    	return theDate;
    }
    
    // read a year string and parse/convert to a year!
    public static Date parseYear(String year) throws ParseException {
            Date theDate = yearFormat.parse(year);
            return theDate;
    }
    
    // read a year integer and parse/convert to a year!
    public static Date parseYear(Integer year) throws ParseException {
             return parseYear(year.toString());
    }
    
    //read year and get Age
    public static int getAge(int year) {
    	Date today = new Date();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(today);
    	int yearNow = cal.get(Calendar.YEAR);
    	return yearNow - year;
    }
    
    // read a date and format/convert to a string!
    public static String formatDate(Date theDate) {
            String result = null;
            if (theDate != null) {
                    result = formatter.format(theDate);
            }
            return result;
    }
    
    // read a date and desired format and format/convert to a string!
    public static String formatDate(Date theDate, String formatStr) {
            String result = null;
            if (theDate != null) {
            	SimpleDateFormat newFormatter = new SimpleDateFormat(formatStr);
                result = newFormatter.format(theDate);
            }
            return result;
    }
	
 // read a date and desired format and format/convert to a string!
    public static String formatYear(Date theDate) {
            String result = null;
            if (theDate != null) {
                result = yearFormat.format(theDate);
            }
            return result;
    }
}
