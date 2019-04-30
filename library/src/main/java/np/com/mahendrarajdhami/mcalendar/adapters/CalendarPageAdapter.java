package np.com.mahendrarajdhami.mcalendar.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import np.com.mahendrarajdhami.mcalendar.R;
import np.com.mahendrarajdhami.mcalendar.extensions.CalendarGridView;
import np.com.mahendrarajdhami.mcalendar.listeners.DayRowClickListener;
import np.com.mahendrarajdhami.mcalendar.utils.CalendarProperties;
import np.com.mahendrarajdhami.mcalendar.utils.DateConverter;
import np.com.mahendrarajdhami.mcalendar.utils.Model;
import np.com.mahendrarajdhami.mcalendar.utils.SelectedDay;

import static np.com.mahendrarajdhami.mcalendar.utils.CalendarProperties.CALENDAR_SIZE;


public class CalendarPageAdapter extends PagerAdapter {

    private Context mContext;
    private CalendarGridView mCalendarGridView;

    private CalendarProperties mCalendarProperties;

    private int mPageMonth;

    public CalendarPageAdapter(Context context, CalendarProperties calendarProperties) {
        mContext = context;
        mCalendarProperties = calendarProperties;
        informDatePicker();
    }

    @Override
    public int getCount() {
        int calendarSize = CALENDAR_SIZE;
        return calendarSize;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCalendarGridView = (CalendarGridView) inflater.inflate(R.layout.calendar_view_grid, null);
        loadMonth(position);
        mCalendarGridView.setOnItemClickListener(new DayRowClickListener(this, mCalendarProperties, mPageMonth));

        container.addView(mCalendarGridView);
        return mCalendarGridView;
    }

    public void addSelectedDay(SelectedDay selectedDay) {
        if (!mCalendarProperties.getSelectedDays().contains(selectedDay)) {
            mCalendarProperties.getSelectedDays().add(selectedDay);
            informDatePicker();
            return;
        }

        mCalendarProperties.getSelectedDays().remove(selectedDay);
        informDatePicker();
    }

    public List<SelectedDay> getSelectedDays() {
        return mCalendarProperties.getSelectedDays();
    }

    public SelectedDay getSelectedDay() {
        return mCalendarProperties.getSelectedDays().get(0);
    }

    public void setSelectedDay(SelectedDay selectedDay) {
        mCalendarProperties.setSelectedDay(selectedDay);
        informDatePicker();
    }

    /**
     * This method inform DatePicker about ability to return selected days
     */
    private void informDatePicker() {
        if (mCalendarProperties.getOnSelectionAbilityListener() != null) {
            mCalendarProperties.getOnSelectionAbilityListener().onChange(mCalendarProperties.getSelectedDays().size() > 0);
        }
    }

    /**
     * This method fill calendar GridView with days
     *
     * @param position Position of current page in ViewPager
     */
    private void loadMonth(int position) {

        ArrayList<Date> days = new ArrayList<>();

        // Get Calendar object instance
        Calendar calendar = (Calendar) mCalendarProperties.getFirstPageCalendarDate().clone();

        DateConverter dc = new DateConverter();
        Model tempModel = dc.getNepaliDate(calendar);
        int tempYear = tempModel.getYear();
        int tempMonth = tempModel.getMonth();
        int tempDay = tempModel.getDay();
        int tempFirstDayOfWeek = tempModel.getDayOfWeek();
        int tempDayOfWeek = dc.getFirstWeekDayMonth(tempYear,tempMonth);

        // Add months to Calendar (a number of months depends on ViewPager position)
        calendar.add(Calendar.MONTH, position);

        // Set day of month as 1 ()
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // Get a number of the first day of the week
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        Log.i("dayOfWeek",String.valueOf(dayOfWeek));

        // Count when month is beginning
        int firstDayOfWeek = calendar.getFirstDayOfWeek();
        int monthBeginningCell = (dayOfWeek < firstDayOfWeek ? 7 : 0) + dayOfWeek - firstDayOfWeek;

        // Subtract a number of beginning days, it will let to load a part of a previous month
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        /*
        Get all days of one page (42 is a number of all possible cells in one page
        (a part of previous month, current month and a part of next month))
         */

        while (days.size() < 35) {
            days.add(calendar.getTime());
            Log.i("time",String.valueOf(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            /*int tempYear = calendar.get(Calendar.YEAR);
            int tempMonth = calendar.get(Calendar.MONTH);
            int tempDay = calendar.get(Calendar.DAY_OF_MONTH);*/
            //int tempFirstDayOfWeek = calendar.getFirstDayOfWeek();
            Log.i("CalendarPageAdapter","Load Month");
        }


        mPageMonth = calendar.get(Calendar.MONTH) - 1;//0=>January,1=>February and so on
        CalendarDayAdapter calendarDayAdapter = new CalendarDayAdapter(this, mContext, mCalendarProperties, days, mPageMonth);

        mCalendarGridView.setAdapter(calendarDayAdapter);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
