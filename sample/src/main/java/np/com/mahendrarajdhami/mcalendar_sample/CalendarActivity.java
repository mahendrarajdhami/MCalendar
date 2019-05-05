package np.com.mahendrarajdhami.mcalendar_sample;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import np.com.mahendrarajdhami.mcalendar.CalendarView;
import np.com.mahendrarajdhami.mcalendar.EventDay;
import np.com.mahendrarajdhami.mcalendar.LeaveDay;
import np.com.mahendrarajdhami.mcalendar.PresentDay;
import np.com.mahendrarajdhami.mcalendar.utils.DateUtils;
import np.com.mahendrarajdhami.mcalendar_sample.utils.DrawableUtils;
import np.com.mahendrarajdhami.mcalendar_sample.utils.MFunction;

public class CalendarActivity extends AppCompatActivity {
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        mContext = this;
        setTitle("Attendance");
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        Calendar min = Calendar.getInstance();
        DateUtils.setMidnight(min);
        min.set(min.get(Calendar.YEAR)-1,0, 1);

        Calendar max = Calendar.getInstance();
        max.set(max.get(Calendar.YEAR),max.get(Calendar.MONTH),max.getActualMaximum(Calendar.DAY_OF_MONTH));


        calendarView.setMinimumDate(min);
        calendarView.setMaximumDate(max);

        calendarView.setLeaves(getLeaveDays());
        calendarView.setPresents(getPresentDays());

        //calendarView.setEvents(createEvents());
        //calendarView.setDisabledDays(getDisabledDays());

        calendarView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        calendarView.setOnDayClickListener(
                eventDay -> showDayDialog()
        );
        calendarView.setHeaderColor(R.color.colorPrimaryDark);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }

            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    private List<EventDay> createEvents(){

        Calendar temp = Calendar.getInstance();

        List<EventDay> events = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, np.com.mahendrarajdhami.mcalendar_sample.utils.DrawableUtils.getCircleDrawableWithText(this, "P")));

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_MONTH, 4);
        events.add(new EventDay(calendar1, DrawableUtils.getCircleDrawableWithText(this,"S",R.drawable.shape_background_saturday)));

        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, 11);
        events.add(new EventDay(calendar2,DrawableUtils.getCircleDrawableWithText(this,"S",R.drawable.shape_background_saturday)));

        return events;

    }

    private List<LeaveDay> getLeaveDays(){


        //return LeaveDay.getAllLeaves();
        List<LeaveDay> leaves = new ArrayList<>();

        leaves.add(new LeaveDay(MFunction.getCalendarFromStrdate("2019-05-09")));
        leaves.add(new LeaveDay(MFunction.getCalendarFromStrdate("2019-05-16")));
        return leaves;
    }

    private List<PresentDay> getPresentDays(){

        List<PresentDay> presentDays = new ArrayList<>();
        presentDays.add(new PresentDay(MFunction.getCalendarFromStrdate("2019-05-01")));
        presentDays.add(new PresentDay(MFunction.getCalendarFromStrdate("2019-05-02")));
        presentDays.add(new PresentDay(MFunction.getCalendarFromStrdate("2019-05-03")));
        presentDays.add(new PresentDay(MFunction.getCalendarFromStrdate("2019-05-05")));
        return presentDays;
    }

    private List<Calendar> getDisabledDays() {

        Calendar firstDisabled = DateUtils.getCalendar();
        firstDisabled.add(Calendar.DAY_OF_MONTH, 2);

        Calendar secondDisabled = DateUtils.getCalendar();
        secondDisabled.add(Calendar.DAY_OF_MONTH, 1);

        Calendar thirdDisabled = DateUtils.getCalendar();
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 18);

        List<Calendar> calendars = new ArrayList<>();
        calendars.add(firstDisabled);
        calendars.add(secondDisabled);
        calendars.add(thirdDisabled);
        return calendars;
    }

    private Calendar getRandomCalendar() {
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, random.nextInt(99));
        return calendar;
    }

    public void showDayDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_day_detail, null);
        dialogBuilder.setView(dialogView);

        TextView tvPendingBill = dialogView.findViewById(R.id.tvPendingBillValue);
        TextView tvLessThan30 = dialogView.findViewById(R.id.tvLT30DaysValue);
        TextView tvBetween30to60 = dialogView.findViewById(R.id.tvB30To60DaysValue);
        TextView tvBetween60to90 = dialogView.findViewById(R.id.tvB60To90DaysValue);
        TextView tvGreaterThan90 = dialogView.findViewById(R.id.tvGT90DaysValue);
        TextView tvOnAccount = dialogView.findViewById(R.id.tvOnAccountValue);

        dialogBuilder.setTitle("Day Detail");

        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        AlertDialog b = dialogBuilder.create();
        //b.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        b.getWindow().setBackgroundDrawableResource(R.drawable.rounded_white_background);
        b.show();
    }

}
