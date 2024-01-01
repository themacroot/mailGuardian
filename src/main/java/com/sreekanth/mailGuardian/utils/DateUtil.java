package com.sreekanth.mailGuardian.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil{
	
    public static Date parseDateString(String dateString) throws ParseException {
        // Define the date format based on the provided pattern
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        
        // Parse the string to a Date object
        return dateFormat.parse(dateString);
    }

    public static boolean isWithinXYears(Date date,int x) {
        // Get the current date
        Calendar currentDate = Calendar.getInstance();

        // Set the calendar to the date two years ago
        currentDate.add(Calendar.YEAR, -x);

        // Compare if the given date is after the date two years ago
        return date.after(currentDate.getTime());
    }
    
    
}