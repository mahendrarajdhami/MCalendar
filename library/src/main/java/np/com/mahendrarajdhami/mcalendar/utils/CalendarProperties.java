package np.com.mahendrarajdhami.mcalendar.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import np.com.mahendrarajdhami.mcalendar.CalendarView;
import np.com.mahendrarajdhami.mcalendar.EventDay;
import np.com.mahendrarajdhami.mcalendar.HoliDay;
import np.com.mahendrarajdhami.mcalendar.LeaveDay;
import np.com.mahendrarajdhami.mcalendar.PresentDay;
import np.com.mahendrarajdhami.mcalendar.R;
import np.com.mahendrarajdhami.mcalendar.exceptions.ErrorsMessages;
import np.com.mahendrarajdhami.mcalendar.exceptions.UnsupportedMethodsException;
import np.com.mahendrarajdhami.mcalendar.listeners.OnCalendarPageChangeListener;
import np.com.mahendrarajdhami.mcalendar.listeners.OnDayClickListener;
import np.com.mahendrarajdhami.mcalendar.listeners.OnSelectDateListener;
import np.com.mahendrarajdhami.mcalendar.listeners.OnSelectionAbilityListener;

/**
 * This class contains all properties of the calendar
 * <p>
 */

public class CalendarProperties {

    /**
     * A number of months (pages) in the calendar
     * 2401 months means 1200 months (100 years) before and 1200 months after the current month
     */
    public static final int CALENDAR_SIZE = 2401;
    public static final int FIRST_VISIBLE_PAGE = CALENDAR_SIZE / 2;

    private int mCalendarType, mHeaderColor, mHeaderLabelColor, mSelectionColor, mTodayLabelColor,
            mDialogButtonsColor, mItemLayoutResource, mDisabledDaysLabelsColor, mPagesColor,
            mAbbreviationsBarColor, mAbbreviationsLabelsColor, mDaysLabelsColor, mSelectionLabelColor,
            mAnotherMonthsDaysLabelsColor, mSaturdaydaysLabelsColor, mLeavedaysLabelsColor, mPresentdaysLabelsColor, mHeaderVisibility;

    private boolean mEventsEnabled;
    private boolean mLeavesEnabled;
    private boolean mPresentEnabled;
    private boolean mHolidayEnabled;


    private boolean mSwipeEnabled;

    private Drawable mPreviousButtonSrc, mForwardButtonSrc;

    private Calendar mFirstPageCalendarDate = DateUtils.getCalendar();
    private Calendar mCalendar, mMinimumDate, mMaximumDate;

    private OnDayClickListener mOnDayClickListener;
    private OnSelectDateListener mOnSelectDateListener;
    private OnSelectionAbilityListener mOnSelectionAbilityListener;
    private OnCalendarPageChangeListener mOnPreviousPageChangeListener;
    private OnCalendarPageChangeListener mOnForwardPageChangeListener;

    private List<EventDay> mEventDays = new ArrayList<>();
    private List<LeaveDay> mLeaveDays = new ArrayList<>();
    private List<PresentDay> mPresentDays = new ArrayList<>();
    private List<Calendar> mDisabledDays = new ArrayList<>();
    private List<SelectedDay> mSelectedDays = new ArrayList<>();
    private List<HoliDay> mHoliDays = new ArrayList<>();

    public List<HoliDay> getHoliDays() {
        return mHoliDays;
    }

    public void setHoliDays(List<HoliDay> mHoliDays) {
        this.mHoliDays = mHoliDays;
    }

    private Context mContext;

    public CalendarProperties(Context context) {
        mContext = context;
    }

    public int getCalendarType() {
        return mCalendarType;
    }

    public void setCalendarType(int calendarType) {
        mCalendarType = calendarType;
    }

    public boolean getLeavesEnabled() {
        return mLeavesEnabled;
    }

    public boolean getPresentEnabled() {
        return mPresentEnabled;
    }

    public boolean getEventsEnabled() {
        return mEventsEnabled;
    }

    public void setEventsEnabled(boolean eventsEnabled) {
        mEventsEnabled = eventsEnabled;
    }

    public void setLeavesEnabled(boolean leavesEnabled) {
        mLeavesEnabled = leavesEnabled;
    }

    public void setPresentEnabled(boolean presentEnabled) {
        mPresentEnabled = presentEnabled;
    }

    public boolean getSwipeEnabled() {
        return mSwipeEnabled;
    }

    public void setSwipeEnabled(boolean swipeEnabled) {
        mSwipeEnabled = swipeEnabled;
    }

    public Calendar getCalendar() {
        return mCalendar;
    }

    public void setCalendar(Calendar calendar) {
        mCalendar = calendar;
    }

    public OnSelectDateListener getOnSelectDateListener() {
        return mOnSelectDateListener;
    }

    public void setOnSelectDateListener(OnSelectDateListener onSelectDateListener) {
        mOnSelectDateListener = onSelectDateListener;
    }

    public int getHeaderColor() {
        if (mHeaderColor <= 0) {
            return mHeaderColor;
        }

        return ContextCompat.getColor(mContext, mHeaderColor);
    }

    public void setHeaderColor(int headerColor) {
        mHeaderColor = headerColor;
    }

    public int getHeaderLabelColor() {
        if (mHeaderLabelColor <= 0) {
            return mHeaderLabelColor;
        }

        return ContextCompat.getColor(mContext, mHeaderLabelColor);
    }

    public void setHeaderLabelColor(int headerLabelColor) {
        mHeaderLabelColor = headerLabelColor;
    }

    public Drawable getPreviousButtonSrc() {
        return mPreviousButtonSrc;
    }

    public void setPreviousButtonSrc(Drawable previousButtonSrc) {
        mPreviousButtonSrc = previousButtonSrc;
    }

    public Drawable getForwardButtonSrc() {
        return mForwardButtonSrc;
    }

    public void setForwardButtonSrc(Drawable forwardButtonSrc) {
        mForwardButtonSrc = forwardButtonSrc;
    }

    public int getSelectionColor() {
        if (mSelectionColor == 0) {
            return ContextCompat.getColor(mContext, R.color.defaultColor);
        }

        return mSelectionColor;
    }

    public void setSelectionColor(int selectionColor) {
        mSelectionColor = selectionColor;
    }

    public int getTodayLabelColor() {
        if (mTodayLabelColor == 0) {
            return ContextCompat.getColor(mContext, R.color.defaultColor);
        }

        return mTodayLabelColor;
    }

    public void setTodayLabelColor(int todayLabelColor) {
        mTodayLabelColor = todayLabelColor;
    }

    public int getDialogButtonsColor() {
        return mDialogButtonsColor;
    }

    public void setDialogButtonsColor(int dialogButtonsColor) {
        mDialogButtonsColor = dialogButtonsColor;
    }

    public Calendar getMinimumDate() {
        return mMinimumDate;
    }

    public void setMinimumDate(Calendar minimumDate) {
        mMinimumDate = minimumDate;
    }

    public Calendar getMaximumDate() {
        return mMaximumDate;
    }

    public void setMaximumDate(Calendar maximumDate) {
        mMaximumDate = maximumDate;
    }

    public OnSelectionAbilityListener getOnSelectionAbilityListener() {
        return mOnSelectionAbilityListener;
    }

    public void setOnSelectionAbilityListener(OnSelectionAbilityListener onSelectionAbilityListener) {
        mOnSelectionAbilityListener = onSelectionAbilityListener;
    }

    public int getItemLayoutResource() {
        return mItemLayoutResource;
    }

    public void setItemLayoutResource(int itemLayoutResource) {
        mItemLayoutResource = itemLayoutResource;
    }

    public OnCalendarPageChangeListener getOnPreviousPageChangeListener() {
        return mOnPreviousPageChangeListener;
    }

    public void setOnPreviousPageChangeListener(OnCalendarPageChangeListener onPreviousButtonClickListener) {
        mOnPreviousPageChangeListener = onPreviousButtonClickListener;
    }

    public OnCalendarPageChangeListener getOnForwardPageChangeListener() {
        return mOnForwardPageChangeListener;
    }

    public void setOnForwardPageChangeListener(OnCalendarPageChangeListener onForwardButtonClickListener) {
        mOnForwardPageChangeListener = onForwardButtonClickListener;
    }

    public Calendar getFirstPageCalendarDate() {
        return mFirstPageCalendarDate;
    }

    public OnDayClickListener getOnDayClickListener() {
        return mOnDayClickListener;
    }

    public void setOnDayClickListener(OnDayClickListener onDayClickListener) {
        mOnDayClickListener = onDayClickListener;
    }

    public List<EventDay> getEventDays() {
        return mEventDays;
    }

    public List<LeaveDay> getLeaveDays() {
        return mLeaveDays;
    }

    public List<PresentDay> getPresentDays() {
        return mPresentDays;
    }

    public void setEventDays(List<EventDay> eventDays) {
        mEventDays = eventDays;
    }

    public void setLeaveDays(List<LeaveDay> leaveDays) {
        mLeaveDays = leaveDays;
    }

    public void setPresentDays(List<PresentDay> presentDays) {
        mPresentDays = presentDays;
    }

    public List<Calendar> getDisabledDays() {
        return mDisabledDays;
    }

    public void setDisabledDays(List<Calendar> disabledDays) {
        mSelectedDays.removeAll(disabledDays);

        mDisabledDays = Stream.of(disabledDays)
                .map(calendar -> {
                    DateUtils.setMidnight(calendar);
                    return calendar;
                }).toList();
    }

    public List<SelectedDay> getSelectedDays() {
        return mSelectedDays;
    }

    public void setSelectedDay(Calendar calendar) {
        setSelectedDay(new SelectedDay(calendar));
    }

    public void setSelectedDay(SelectedDay selectedDay) {
        mSelectedDays.clear();
        mSelectedDays.add(selectedDay);
    }

    public void setSelectedDays(List<Calendar> selectedDays) {
        if (mCalendarType == CalendarView.ONE_DAY_PICKER) {
            throw new UnsupportedMethodsException(ErrorsMessages.ONE_DAY_PICKER_MULTIPLE_SELECTION);
        }

        if (mCalendarType == CalendarView.RANGE_PICKER && !DateUtils.isFullDatesRange(selectedDays)) {
            throw new UnsupportedMethodsException(ErrorsMessages.RANGE_PICKER_NOT_RANGE);
        }

        mSelectedDays = Stream.of(selectedDays)
                .map(calendar -> {
                    DateUtils.setMidnight(calendar);
                    return new SelectedDay(calendar);
                }).filterNot(value -> mDisabledDays.contains(value.getCalendar()))
                .toList();
    }

    public int getDisabledDaysLabelsColor() {
        if (mDisabledDaysLabelsColor == 0) {
            return ContextCompat.getColor(mContext, R.color.nextMonthDayColor);
        }

        return mDisabledDaysLabelsColor;
    }

    public void setDisabledDaysLabelsColor(int disabledDaysLabelsColor) {
        mDisabledDaysLabelsColor = disabledDaysLabelsColor;
    }

    public int getPagesColor() {
        return mPagesColor;
    }

    public void setPagesColor(int pagesColor) {
        mPagesColor = pagesColor;
    }

    public int getAbbreviationsBarColor() {
        return mAbbreviationsBarColor;
    }

    public void setAbbreviationsBarColor(int abbreviationsBarColor) {
        mAbbreviationsBarColor = abbreviationsBarColor;
    }

    public int getAbbreviationsLabelsColor() {
        return mAbbreviationsLabelsColor;
    }

    public void setAbbreviationsLabelsColor(int abbreviationsLabelsColor) {
        mAbbreviationsLabelsColor = abbreviationsLabelsColor;
    }

    public int getDaysLabelsColor() {
        if (mDaysLabelsColor == 0) {
            return ContextCompat.getColor(mContext, R.color.currentMonthDayColor);
        }

        return mDaysLabelsColor;
    }

    public void setDaysLabelsColor(int daysLabelsColor) {
        mDaysLabelsColor = daysLabelsColor;
    }

    public int getSelectionLabelColor() {
        if (mSelectionLabelColor == 0) {
            return ContextCompat.getColor(mContext, android.R.color.white);
        }

        return mSelectionLabelColor;
    }

    public void setSelectionLabelColor(int selectionLabelColor) {
        mSelectionLabelColor = selectionLabelColor;
    }

    public int getAnotherMonthsDaysLabelsColor() {
        if (mAnotherMonthsDaysLabelsColor == 0) {
            return ContextCompat.getColor(mContext, R.color.nextMonthDayColor);
        }

        return mAnotherMonthsDaysLabelsColor;
    }

    public int getSaturdaysLabelsColor() {

        if (mSaturdaydaysLabelsColor == 0) {
            return ContextCompat.getColor(mContext, R.color.colorSaturday);
        }
        return mSaturdaydaysLabelsColor;
    }

    public int getLeavesDaysLabelsColor() {

        if (mLeavedaysLabelsColor == 0) {
            return ContextCompat.getColor(mContext, R.color.colorLeave);
        }
        return mLeavedaysLabelsColor;
    }

    public int getPresentDaysLabelsColor() {

        if (mPresentdaysLabelsColor == 0) {
            return ContextCompat.getColor(mContext, R.color.colorPresent);
        }
        return mPresentdaysLabelsColor;
    }

    public boolean getHolidayEnabled() {
        return mHolidayEnabled;
    }

    public void setHolidayEnabled(boolean mHolidayEnabled) {
        this.mHolidayEnabled = mHolidayEnabled;
    }

    public void setAnotherMonthsDaysLabelsColor(int anotherMonthsDaysLabelsColor) {
        mAnotherMonthsDaysLabelsColor = anotherMonthsDaysLabelsColor;
    }

    public void setSaturdaysLabelsColor(int tempColor) {
        mSaturdaydaysLabelsColor = tempColor;
    }

    public void setLeavesDaysLabelsColor(int tempColor) {
        mLeavedaysLabelsColor = tempColor;
    }

    public int getHeaderVisibility() {
        return mHeaderVisibility;
    }

    public void setHeaderVisibility(int headerVisibility) {
        mHeaderVisibility = headerVisibility;
    }
}
