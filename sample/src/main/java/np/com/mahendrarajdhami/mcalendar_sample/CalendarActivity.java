package np.com.mahendrarajdhami.mcalendar_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import np.com.mahendrarajdhami.mcalendar.CalendarView;
import np.com.mahendrarajdhami.mcalendar.EventDay;
import np.com.mahendrarajdhami.mcalendar.utils.DateUtils;
import np.com.mahendrarajdhami.mcalendar_sample.utils.DrawableUtils;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        setTitle("Attendance");
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        Calendar min = Calendar.getInstance();
        min.add(Calendar.YEAR, -1);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.MONTH, 2);

        calendarView.setMinimumDate(min);
        calendarView.setMaximumDate(max);

        //calendarView.setEvents(createEvents());

        //calendarView.setDisabledDays(getDisabledDays());

        calendarView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        calendarView.setOnDayClickListener(eventDay -> Toast.makeText(getApplicationContext(), eventDay.getCalendar().getTime().toString() + " " + eventDay.isEnabled(), Toast.LENGTH_SHORT).show());
        calendarView.setHeaderColor(R.color.colorPrimaryDark);
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



        /*Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, 5);
        events.add(new EventDay(calendar2, R.drawable.sample_icon_3));*/

        /*Calendar calendar3 = Calendar.getInstance();
        calendar3.add(Calendar.DAY_OF_MONTH, 7);
        events.add(new EventDay(calendar3, R.drawable.sample_four_icons));*/

        /*Calendar calendar4 = Calendar.getInstance();
        calendar4.add(Calendar.DAY_OF_MONTH, 13);
        events.add(new EventDay(calendar4, DrawableUtils.getThreeDots(this)));*/

        return events;

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
}