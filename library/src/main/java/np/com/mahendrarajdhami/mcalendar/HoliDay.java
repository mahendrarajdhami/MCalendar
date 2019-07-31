package np.com.mahendrarajdhami.mcalendar;

import android.support.annotation.DrawableRes;
import android.support.annotation.RestrictTo;

import java.util.Calendar;

import np.com.mahendrarajdhami.mcalendar.utils.DateUtils;

public class HoliDay {
    private Calendar mDay;
    private Object mDrawable;
    private boolean mIsDisabled;

    public HoliDay(Calendar mDay){
        DateUtils.setMidnight(mDay);
        this.mDay = mDay;
    }

    public HoliDay(Calendar mDay, Object mDrawable) {
        DateUtils.setMidnight(mDay);
        this.mDay = mDay;
        this.mDrawable = mDrawable;
    }

    public HoliDay(Calendar mDay,@DrawableRes int mDrawable) {
        DateUtils.setMidnight(mDay);
        this.mDay = mDay;
        this.mDrawable = mDrawable;
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
