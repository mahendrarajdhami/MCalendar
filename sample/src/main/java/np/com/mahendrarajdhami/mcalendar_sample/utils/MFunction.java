package np.com.mahendrarajdhami.mcalendar_sample.utils;

import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by DeltaTech on 1/18/2018.
 */

public class MFunction {

    public static Calendar getCalendarFromStrdate(String strDate){
        Date tempDate = null;
        try {
            tempDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(tempDate);
        return tempCalendar;
    }
}
