package np.com.mahendrarajdhami.mcalendar;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.RestrictTo;

import java.util.Calendar;

import np.com.mahendrarajdhami.mcalendar.utils.DateUtils;


public class PresentDay {
    private Calendar mDay;
    private Object mDrawable;
    private boolean mIsDisabled;

    /**
     * @param day Calendar object which represents a date of the present
     */
    public PresentDay(Calendar day) {
        DateUtils.setMidnight(day);
        mDay = day;
    }

    /**
     * @param day      Calendar object which represents a date of the present
     * @param drawable Drawable resource which will be displayed in a day cell
     */
    public PresentDay(Calendar day, @DrawableRes int drawable) {
        DateUtils.setMidnight(day);
        mDay = day;
        mDrawable = drawable;
    }

    /**
     * @param day      Calendar object which represents a date of the present
     * @param drawable Drawable which will be displayed in a day cell
     */
    public PresentDay(Calendar day, Drawable drawable) {
        DateUtils.setMidnight(day);
        mDay = day;
        mDrawable = drawable;
    }


    /**
     * @return An image resource which will be displayed in the day row
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public Object getImageDrawable() {
        return mDrawable;
    }


    /**
     * @return Calendar object which represents a date of current present
     */
    public Calendar getCalendar() {
        return mDay;
    }


    /**
     * @return Boolean value if day is not disabled
     */
    public boolean isEnabled() {
        return !mIsDisabled;
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public void setEnabled(boolean enabled) {
        mIsDisabled = enabled;
    }
}
