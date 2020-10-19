package com.example.fikisha.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String dateformatter(Date date){
        SimpleDateFormat ft = new SimpleDateFormat ("MMMM dd, yyyy ");
        return ft.format(date);
    }
}
