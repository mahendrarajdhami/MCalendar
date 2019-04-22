package np.com.mahendrarajdhami.mcalendar.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import np.com.mahendrarajdhami.mcalendar.CalendarView;
import np.com.mahendrarajdhami.mcalendar.R;
import np.com.mahendrarajdhami.mcalendar.utils.CalendarProperties;
import np.com.mahendrarajdhami.mcalendar.utils.DateConverter;
import np.com.mahendrarajdhami.mcalendar.utils.DateUtils;
import np.com.mahendrarajdhami.mcalendar.utils.DayColorsUtils;
import np.com.mahendrarajdhami.mcalendar.utils.ImageUtils;
import np.com.mahendrarajdhami.mcalendar.utils.SelectedDay;


class CalendarDayAdapter extends ArrayAdapter<Date> {
    private CalendarPageAdapter mCalendarPageAdapter;
    private LayoutInflater mLayoutInflater;
    private int mPageMonth;
    private Calendar mToday = DateUtils.getCalendar();

    private CalendarProperties mCalendarProperties;
    DateConverter dc;

    CalendarDayAdapter(CalendarPageAdapter calendarPageAdapter, Context context, CalendarProperties calendarProperties, ArrayList<Date> dates, int pageMonth) {
        super(context, calendarProperties.getItemLayoutResource(), dates);
        mCalendarPageAdapter = calendarPageAdapter;
        mCalendarProperties = calendarProperties;
        mPageMonth = pageMonth < 0 ? 11 : pageMonth;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        if (view == null) {
            view = mLayoutInflater.inflate(mCalendarProperties.getItemLayoutResource(), parent, false);
        }

        TextView dayLabel = (TextView) view.findViewById(R.id.dayLabel);
        ImageView dayIcon = (ImageView) view.findViewById(R.id.dayIcon);

        Calendar day = new GregorianCalendar();
        day.setTime(getItem(position));

        // Loading an image of the event
        if (dayIcon != null) {
            loadIcon(dayIcon, day);
        }

        setLabelColors(dayLabel, day);

        int yr = day.get(Calendar.YEAR);
        int mn = day.get(Calendar.MONTH);
        int d = day.get(Calendar.DAY_OF_MONTH);
        dc = new DateConverter();

        //Model tempModel = dc.getNepaliDate(day);
        //dayLabel.setText(String.valueOf(day.get(Calendar.DAY_OF_MONTH))+"/"+MDateConversion.getNepaliDate(yr,mn,d,"1",3));
        //dayLabel.setText(String.valueOf(day.get(Calendar.DAY_OF_MONTH))+"/"+tempModel.getDay());
        dayLabel.setText(String.valueOf(day.get(Calendar.DAY_OF_MONTH)));
        return view;
    }

    private void setLabelColors(TextView dayLabel, Calendar day) {
        // Setting not current month day color
        if (!isCurrentMonthDay(day)) {
            DayColorsUtils.setDayColors(dayLabel, mCalendarProperties.getAnotherMonthsDaysLabelsColor(), Typeface.NORMAL, R.drawable.background_transparent);
            return;
        }

        // Set view for all SelectedDays
        if (isSelectedDay(day)) {
            /*Stream.of(mCalendarPageAdapter.getSelectedDays())
                    .filter(selectedDay -> selectedDay.getCalendar().equals(day))
                    .findFirst().ifPresent(selectedDay -> selectedDay.setView(dayLabel));

            DayColorsUtils.setSelectedDayColors(dayLabel, mCalendarProperties);
            return;*/
        }

        // Setting disabled days color
        if (!isActiveDay(day)) {
            DayColorsUtils.setDayColors(dayLabel, mCalendarProperties.getDisabledDaysLabelsColor(),
                    Typeface.NORMAL, R.drawable.background_transparent);
            return;
        }

        // Setting current month day color

        //DayColorsUtils.setCurrentMonthDayColors(day, mToday, dayLabel, mCalendarProperties);

    }

    private boolean isSelectedDay(Calendar day) {
        return mCalendarProperties.getCalendarType() != CalendarView.CLASSIC && day.get(Calendar.MONTH) == mPageMonth
                && mCalendarPageAdapter.getSelectedDays().contains(new SelectedDay(day));
    }

    private boolean isCurrentMonthDay(Calendar day) {
        return day.get(Calendar.MONTH) == mPageMonth &&
                !((mCalendarProperties.getMinimumDate() != null && day.before(mCalendarProperties.getMinimumDate()))
                        || (mCalendarProperties.getMaximumDate() != null && day.after(mCalendarProperties.getMaximumDate())));
    }

    private boolean isActiveDay(Calendar day) {
        return !mCalendarProperties.getDisabledDays().contains(day);
    }

    private void loadIcon(ImageView dayIcon, Calendar day) {
        if (mCalendarProperties.getEventDays() == null || !mCalendarProperties.getEventsEnabled()) {
            dayIcon.setVisibility(View.GONE);
            return;
        }

        Stream.of(mCalendarProperties.getEventDays()).filter(eventDate ->
                eventDate.getCalendar().equals(day)).findFirst().executeIfPresent(eventDay -> {

            ImageUtils.loadImage(dayIcon, eventDay.getImageDrawable());

            // If a day doesn't belong to current month then image is transparent
            if (!isCurrentMonthDay(day) || !isActiveDay(day)) {
                dayIcon.setAlpha(0.12f);
            }

        });
    }
}