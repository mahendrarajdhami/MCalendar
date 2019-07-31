package np.com.mahendrarajdhami.mcalendar.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.annimon.stream.Stream;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import np.com.mahendrarajdhami.mcalendar.CalendarUtils;
import np.com.mahendrarajdhami.mcalendar.CalendarView;
import np.com.mahendrarajdhami.mcalendar.EventDay;
import np.com.mahendrarajdhami.mcalendar.HoliDay;
import np.com.mahendrarajdhami.mcalendar.LeaveDay;
import np.com.mahendrarajdhami.mcalendar.PresentDay;
import np.com.mahendrarajdhami.mcalendar.R;
import np.com.mahendrarajdhami.mcalendar.adapters.CalendarPageAdapter;
import np.com.mahendrarajdhami.mcalendar.utils.CalendarProperties;
import np.com.mahendrarajdhami.mcalendar.utils.DateUtils;
import np.com.mahendrarajdhami.mcalendar.utils.DayColorsUtils;
import np.com.mahendrarajdhami.mcalendar.utils.SelectedDay;


/**
 * This class is responsible for handle click events
 */

public class DayRowClickListener implements AdapterView.OnItemClickListener {

    private CalendarPageAdapter mCalendarPageAdapter;

    private CalendarProperties mCalendarProperties;
    private int mPageMonth;

    public DayRowClickListener(CalendarPageAdapter calendarPageAdapter, CalendarProperties calendarProperties, int pageMonth) {
        mCalendarPageAdapter = calendarPageAdapter;
        mCalendarProperties = calendarProperties;
        mPageMonth = pageMonth < 0 ? 11 : pageMonth;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Calendar day = new GregorianCalendar();
        day.setTime((Date) adapterView.getItemAtPosition(position));

        if (mCalendarProperties.getOnDayClickListener() != null) {
            onClick(day);
        }

        switch (mCalendarProperties.getCalendarType()) {
            case CalendarView.ONE_DAY_PICKER:
                selectOneDay(view, day);
                break;

            case CalendarView.MANY_DAYS_PICKER:
                selectManyDays(view, day);
                break;

            case CalendarView.RANGE_PICKER:
                selectRange(view, day);
                break;

            case CalendarView.CLASSIC:
                mCalendarPageAdapter.setSelectedDay(new SelectedDay(view, day));
        }

    }

    private void selectOneDay(View view, Calendar day) {
        SelectedDay previousSelectedDay = mCalendarPageAdapter.getSelectedDay();

        TextView dayLabel = (TextView) view.findViewById(R.id.dayLabel);

        if (isAnotherDaySelected(previousSelectedDay, day)) {
            selectDay(dayLabel, day);
            if (dayIsHoliday(previousSelectedDay.getCalendar())) {
                if (previousSelectedDay.getCalendar().get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                    reverseHolidayColor(previousSelectedDay);
                } else {
                    reverseUnselectedColor(previousSelectedDay);
                }
            } else if (dayIsLeaveDay(previousSelectedDay.getCalendar())) {
                reverseLeaveColor(previousSelectedDay);
            } else if (dayIsPresentDay(previousSelectedDay.getCalendar())) {
                reversePresentColor(previousSelectedDay);
            } else {
                reverseUnselectedColor(previousSelectedDay);
            }
        }


    }


    private void reversePresentColor(SelectedDay selectedDay) {
        DayColorsUtils.setPresentDayColor((TextView) selectedDay.getView(), mCalendarProperties);

    }

    private void reverseLeaveColor(SelectedDay selectedDay) {
        DayColorsUtils.setLeaveDayColor((TextView) selectedDay.getView(), mCalendarProperties);

    }

    private void reverseHolidayColor(SelectedDay selectedDay) {
        DayColorsUtils.setSaturdayColor((TextView) selectedDay.getView(), mCalendarProperties);

    }

    private boolean dayIsLeaveDay(Calendar day) {
        List<LeaveDay> leaveDays = mCalendarProperties.getLeaveDays();
        for (LeaveDay leaveDay : leaveDays) {
            if (leaveDay.getCalendar().get(Calendar.DAY_OF_YEAR) == day.get(Calendar.DAY_OF_YEAR) && leaveDay.getCalendar().get(Calendar.YEAR) == day.get(Calendar.YEAR)) {
                return true;
            }
        }
        return false;
    }

    private boolean dayIsHoliday(Calendar day) {
        List<HoliDay> holiDays = mCalendarProperties.getHoliDays();
        for (HoliDay holiDay : holiDays) {
            if (holiDay.getCalendar().get(Calendar.DAY_OF_YEAR) == day.get(Calendar.DAY_OF_YEAR) && holiDay.getCalendar().get(Calendar.YEAR) == day.get(Calendar.YEAR)) {
                return true;
            }
        }
        return false;
    }

    private boolean dayIsPresentDay(Calendar day) {
        List<PresentDay> presentDays = mCalendarProperties.getPresentDays();
        for (PresentDay presentDay : presentDays) {
            if (presentDay.getCalendar().get(Calendar.DAY_OF_YEAR) == day.get(Calendar.DAY_OF_YEAR) && presentDay.getCalendar().get(Calendar.YEAR) == day.get(Calendar.YEAR)) {
                return true;
            }
        }
        return false;
    }

    private void selectManyDays(View view, Calendar day) {
        TextView dayLabel = (TextView) view.findViewById(R.id.dayLabel);

        if (isCurrentMonthDay(day) && isActiveDay(day)) {
            SelectedDay selectedDay = new SelectedDay(dayLabel, day);

            if (!mCalendarPageAdapter.getSelectedDays().contains(selectedDay)) {
                DayColorsUtils.setSelectedDayColors(dayLabel, mCalendarProperties);
            } else {
                reverseUnselectedColor(selectedDay);
            }

            mCalendarPageAdapter.addSelectedDay(selectedDay);
        }
    }

    private void selectRange(View view, Calendar day) {
        TextView dayLabel = (TextView) view.findViewById(R.id.dayLabel);

        if (!isCurrentMonthDay(day) || !isActiveDay(day)) {
            return;
        }

        List<SelectedDay> selectedDays = mCalendarPageAdapter.getSelectedDays();

        if (selectedDays.size() > 1) {
            clearAndSelectOne(dayLabel, day);
        }

        if (selectedDays.size() == 1) {
            selectOneAndRange(dayLabel, day);
        }

        if (selectedDays.isEmpty()) {
            selectDay(dayLabel, day);
        }
    }

    private void clearAndSelectOne(TextView dayLabel, Calendar day) {
        Stream.of(mCalendarPageAdapter.getSelectedDays()).forEach(this::reverseUnselectedColor);
        selectDay(dayLabel, day);
    }

    private void selectOneAndRange(TextView dayLabel, Calendar day) {
        SelectedDay previousSelectedDay = mCalendarPageAdapter.getSelectedDay();

        Stream.of(CalendarUtils.getDatesRange(previousSelectedDay.getCalendar(), day))
                .filter(calendar -> !mCalendarProperties.getDisabledDays().contains(calendar))
                .forEach(calendar -> mCalendarPageAdapter.addSelectedDay(new SelectedDay(calendar)));

        DayColorsUtils.setSelectedDayColors(dayLabel, mCalendarProperties);

        mCalendarPageAdapter.addSelectedDay(new SelectedDay(dayLabel, day));
        mCalendarPageAdapter.notifyDataSetChanged();
    }

    private void selectDay(TextView dayLabel, Calendar day) {
        DayColorsUtils.setSelectedDayColors(dayLabel, mCalendarProperties);
        mCalendarPageAdapter.setSelectedDay(new SelectedDay(dayLabel, day));
    }

    private void reverseUnselectedColor(SelectedDay selectedDay) {
        DayColorsUtils.setCurrentMonthDayColors(selectedDay.getCalendar(),
                DateUtils.getCalendar(), (TextView) selectedDay.getView(), mCalendarProperties);
    }

    private boolean isCurrentMonthDay(Calendar day) {
        return day.get(Calendar.MONTH) == mPageMonth && isBetweenMinAndMax(day);
    }

    private boolean isActiveDay(Calendar day) {
        return !mCalendarProperties.getDisabledDays().contains(day);
    }

    private boolean isBetweenMinAndMax(Calendar day) {
        return !((mCalendarProperties.getMinimumDate() != null && day.before(mCalendarProperties.getMinimumDate()))
                || (mCalendarProperties.getMaximumDate() != null && day.after(mCalendarProperties.getMaximumDate())));
    }

    private boolean isAnotherDaySelected(SelectedDay selectedDay, Calendar day) {
        return selectedDay != null && !day.equals(selectedDay.getCalendar())
                && isCurrentMonthDay(day) && isActiveDay(day);
    }

    private void onClick(Calendar day) {
        if (mCalendarProperties.getEventDays() == null) {
            createEmptyEventDay(day);
            return;
        }

        Stream.of(mCalendarProperties.getEventDays())
                .filter(eventDate -> eventDate.getCalendar().equals(day))
                .findFirst()
                .ifPresentOrElse(this::callOnClickListener, () -> createEmptyEventDay(day));
    }

    private void createEmptyEventDay(Calendar day) {
        EventDay eventDay = new EventDay(day);
        callOnClickListener(eventDay);
    }

    private void callOnClickListener(EventDay eventDay) {
        boolean enabledDay = mCalendarProperties.getDisabledDays().contains(eventDay.getCalendar())
                || !isBetweenMinAndMax(eventDay.getCalendar());

        eventDay.setEnabled(enabledDay);
        mCalendarProperties.getOnDayClickListener().onDayClick(eventDay);

    }
}
