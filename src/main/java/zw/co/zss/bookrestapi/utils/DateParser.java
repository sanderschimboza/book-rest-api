package zw.co.zss.bookrestapi.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateParser {

    public static void main(String[] args) throws ParseException {

        String str = getParsedDate("2022-03-07T14:55:14.069+02:00");
        System.out.println(str);
    }

    public static String getParsedDate(String date) {

        System.out.println("IN:::"+date);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        String s2 = null;
        Date d;
        try {
            d = sdf.parse(date);
            s2 = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).format(d);

        } catch (ParseException e) {

            e.printStackTrace();
        }

        System.out.println("OUT::::"+s2);

        return s2;
    }

}
