package np.com.mahendrarajdhami.mcalendar.utils;

import android.support.annotation.IntRange;
import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 */
public class Model {

    private int day;
    private int year;
    private int month;
    private int dayOfWeek;

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(@IntRange(from = 1, to = 7) int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Model() {
        GregorianCalendar date = new GregorianCalendar();
        day = date.get(GregorianCalendar.DAY_OF_MONTH);
        month = date.get(GregorianCalendar.MONTH) + 1;
        year = date.get(GregorianCalendar.YEAR);
        dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
    }

    public Model(@IntRange(from=1970,to=2090) int year,
                 @IntRange(from = 0, to = 12) int month,
                 @IntRange(from = 1, to = 32) int day) {
        this.year = year;
        this.month = month;
        this.day = day;

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        Log.i("Model","setYear");
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        Log.i("Model","setDay");
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        Log.i("Model","setMonth");
        this.month = month;
    }

}
