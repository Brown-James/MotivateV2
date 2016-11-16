package com.brownjames.motivatev2;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by james on 23/09/16.
 */

public class Util {

    private static final String TAG = "Util";

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Parses a date-time String as stored in the database to a Date object
     * @param dateTime The date-time String from the database.
     * @return The parsed date.
     */
    public static Date parseDateTimeString(String dateTime) {

        DateFormat iso8601Format = new SimpleDateFormat(DATE_FORMAT);

        try {
            return iso8601Format.parse(dateTime);
        } catch (ParseException e) {
            Log.e(TAG, "Parsing ISO8601 datetime failed when given string " + dateTime, e);
            return null;
        }
    }
}
