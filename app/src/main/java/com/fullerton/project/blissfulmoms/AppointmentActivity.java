package com.fullerton.project.blissfulmoms;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.util.TimeZone;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.v13.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.fullerton.project.blissfulmoms.models.Appointment;
import com.fullerton.project.blissfulmoms.utils.SQLiteHelper;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/*This is the code used to create the appointments page where the user can provide details like
date, time and appointment description  */
public class AppointmentActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = AppointmentActivity.this;
    SimpleDateFormat month = new SimpleDateFormat("MMMM");
    SimpleDateFormat month_day = new SimpleDateFormat("dd");
    SimpleDateFormat month_year = new SimpleDateFormat("yyyy");
    private TextView textViewDate;
    private TextView textViewTime;
    private EditText textAppointmentDesc;
    private AppCompatButton appSaveAppointmentBtn;
    private LinearLayout appointmentText;
    private LinearLayout textAppointmentTime;
    private Appointment userAppointment;
    private SQLiteHelper storageHelper;
    private int mYear, mMonth, mDay, mHour, mMinute;
    long startMillis, endMillis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR}, 1);

        setContentView(R.layout.activity_appointment);
        initViews();
        initObjects();
    }

    private void initObjects() {
        userAppointment = new Appointment();
        storageHelper = new SQLiteHelper(activity);
        appointmentText.setOnClickListener(this);
        textAppointmentTime.setOnClickListener(this);
        appSaveAppointmentBtn.setOnClickListener(this);
    }

    private void initViews() {
        textViewDate = (TextView) findViewById(R.id.textDateView);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        textAppointmentDesc = (EditText) findViewById(R.id.appointmentDetails);
        appSaveAppointmentBtn = (AppCompatButton) findViewById(R.id.appCompatAppointmentSave);

        final Calendar c = Calendar.getInstance();

        // set current date into textview
        textViewDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month.format(c.getTime())).append(" ").append(month_day.format(c.getTime())).append(",").append(" ")
                .append(month_year.format(c.getTime())));
        appointmentText = (LinearLayout) findViewById(R.id.textAppointmentDate);
        textAppointmentTime = (LinearLayout) findViewById(R.id.textAppointmentTime);
    }

    private void createAppointment() {

        userAppointment.setEmail(getIntent().getExtras().getString("EMAIL").trim());
        userAppointment.setAppointmentDesc(textAppointmentDesc.getText().toString().trim());
        userAppointment.setAppointmentDate(textViewDate.getText().toString().trim());
        userAppointment.setAppointmentTime(textViewTime.getText().toString().trim());
        storageHelper.addAppointment(userAppointment);
        Intent homeIntent = new Intent(activity, HomeActivity.class);
        homeIntent.putExtra("EMAIL", getIntent().getExtras().getString("EMAIL"));
        homeIntent.putExtra("Page", 2);
        startActivity(homeIntent);
    }

    public void addReminder(int statrYear, int startMonth, int startDay, int startHour, int startMinut, int endYear, int endMonth, int endDay, int endHour, int endMinuts) {
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(statrYear, startMonth, startDay, startHour, startMinut);
        long startMillis = beginTime.getTimeInMillis();

        Calendar endTime = Calendar.getInstance();
        endTime.set(endYear, endMonth, endDay, endHour, endMinuts);
        long endMillis = endTime.getTimeInMillis();

        String eventUriString = "content://com.android.calendar/events";
        ContentValues eventValues = new ContentValues();

        eventValues.put(CalendarContract.Events.CALENDAR_ID, 1);
        eventValues.put(CalendarContract.Events.TITLE, "OCS");
        eventValues.put(CalendarContract.Events.DESCRIPTION, "Clinic App");
        eventValues.put(CalendarContract.Events.EVENT_TIMEZONE, "Nasik");
        eventValues.put(CalendarContract.Events.DTSTART, startMillis);
        eventValues.put(CalendarContract.Events.DTEND, endMillis);

        //eventValues.put(Events.RRULE, "FREQ=DAILY;COUNT=2;UNTIL="+endMillis);
        eventValues.put("eventStatus", 1);
        eventValues.put("visibility", 3);
        eventValues.put("transparency", 0);
        eventValues.put(CalendarContract.Events.HAS_ALARM, 1);

        Uri eventUri = getContentResolver().insert(Uri.parse(eventUriString), eventValues);
        long eventID = Long.parseLong(eventUri.getLastPathSegment());

        /***************** Event: Reminder(with alert) Adding reminder to event *******************/

        String reminderUriString = "content://com.android.calendar/reminders";

        ContentValues reminderValues = new ContentValues();

        reminderValues.put("event_id", eventID);
        reminderValues.put("minutes", 1);
        reminderValues.put("method", 1);

        Uri reminderUri = getContentResolver().insert(Uri.parse(reminderUriString), reminderValues);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        final Calendar beginTime = Calendar.getInstance();

        final Calendar endTime = Calendar.getInstance();
        switch (v.getId()) {
            case R.id.textAppointmentDate:
                final Calendar c = Calendar.getInstance();
                final SimpleDateFormat month = new SimpleDateFormat("MMMM dd, yyyy");

                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String monthString = new DateFormatSymbols().getMonths()[monthOfYear];
                                mYear = year;
                                mMonth = monthOfYear;
                                mDay = dayOfMonth;

                                textViewDate.setText(String.format(
                                        "%s %02d, %04d", monthString, dayOfMonth,
                                        year));

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.textAppointmentTime:
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                boolean isPM = (hourOfDay >= 12);
                                mHour = hourOfDay;
                                mMinute = minute;
                                textViewTime.setText(String.format("%02d : %02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
                            }
                        }, hour, minute, true);
                timePickerDialog.show();
                break;
            case R.id.appCompatAppointmentSave:
                String eventUriString = "content://com.android.calendar/events";
                beginTime.set(mYear, mMonth, mDay, mHour, mMinute);
                startMillis = beginTime.getTimeInMillis();
                endTime.set(mYear, mMonth, mDay, mHour, mMinute);
                endMillis = endTime.getTimeInMillis();

                TimeZone timeZone = TimeZone.getDefault();

                ContentValues eventValues = new ContentValues();
                eventValues.put(CalendarContract.Events.CALENDAR_ID, 1);
                eventValues.put(CalendarContract.Events.TITLE, textAppointmentDesc.getText().toString().trim());
                eventValues.put(CalendarContract.Events.DESCRIPTION, textAppointmentDesc.getText().toString().trim());
                eventValues.put(CalendarContract.Events.EVENT_TIMEZONE, "UTC/GMT -7:00");
                eventValues.put(CalendarContract.Events.DTSTART, startMillis);
                eventValues.put(CalendarContract.Events.DTEND, endMillis);

                //eventValues.put(Events.RRULE, "FREQ=DAILY;COUNT=2;UNTIL="+endMillis);
                //eventValues.put(CalendarContract.Events.VISIBLE, 1);
                //m@eventValues.put(CalendarContract.Events.ACCESS_LEVEL, 3);
                eventValues.put(CalendarContract.Events.ALL_DAY, 0);
                eventValues.put(CalendarContract.Events.CALENDAR_ID, 3);
                eventValues.put(CalendarContract.Events.STATUS, 1);

                //eventValues.put("visibility", 3);
                //eventValues.put("transparency", 0);
                eventValues.put(CalendarContract.Events.HAS_ALARM, 1);

                if (android.support.v4.app.ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR}, 1);

                    return;
                }
                Uri eventUri = getContentResolver().insert(CalendarContract.Events.CONTENT_URI, eventValues);
                long eventID = Long.parseLong(eventUri.getLastPathSegment());

                /***************** Event: Reminder(with alert) Adding reminder to event *******************/

                String reminderUriString = "content://com.android.calendar/reminders";

                ContentValues reminderValues = new ContentValues();

                reminderValues.put("event_id", eventID);
                reminderValues.put("minutes", 60);
                reminderValues.put("method", 1);
                Uri reminderUri = getContentResolver().insert(Uri.parse(reminderUriString), reminderValues);
                createAppointment();
                //finish();
                break;
        }
    }
}

