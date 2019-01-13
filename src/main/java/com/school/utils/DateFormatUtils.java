package com.school.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
    public static Date dateConverter(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }
    public static Date dateConverterFormat(Date date) throws ParseException {
        return dateConverter(new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

}
