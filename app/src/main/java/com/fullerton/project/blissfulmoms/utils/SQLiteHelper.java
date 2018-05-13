package com.fullerton.project.blissfulmoms.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fullerton.project.blissfulmoms.models.Appointment;
import com.fullerton.project.blissfulmoms.models.User;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    // Database Name/Version
    private static final String DATABASE_NAME = "BlissfulMoms.db";
    private static final int DATABASE_VERSION = 2;

    // Table Names
    private static final String TABLE_USER = "user";
    private static final String TABLE_APPOINTMENT = "appointments";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_AGE = "user_age";
    private static final String COLUMN_USER_DUE_DATE = "user_due";

    // Appointment Table Column names

    private static final String COLUMN_USER_ID_APP = "user_id";
    private static final String COLUMN_USER_EMAIL_APP = "user_email";
    private static final String COLUMN_USER_DESC_APP = "user_app_desc";
    private static final String COLUMN_USER_DATE_APP = "user_app_date";
    private static final String COLUMN_USER_TIME_APP = "user_app_time";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT," + COLUMN_USER_AGE + " INTEGER," + COLUMN_USER_DUE_DATE + " TEXT" + ")";

    private String CREATE_USER_APP_TABLE = "CREATE TABLE " + TABLE_APPOINTMENT + "("
            + COLUMN_USER_ID_APP + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_EMAIL_APP + " TEXT,"
            + COLUMN_USER_DESC_APP + " TEXT," + COLUMN_USER_DATE_APP + " TEXT," + COLUMN_USER_TIME_APP + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_APP_TABLE = "DROP TABLE IF EXISTS " + TABLE_APPOINTMENT;


    /**
     * Constructor
     *
     * @param context
     */
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_USER_APP_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_APP_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues userValues = new ContentValues();
        userValues.put(COLUMN_USER_NAME, user.getName());
        userValues.put(COLUMN_USER_EMAIL, user.getEmail());
        userValues.put(COLUMN_USER_PASSWORD, user.getPassword());
        userValues.put(COLUMN_USER_AGE, user.getAge());
        userValues.put(COLUMN_USER_DUE_DATE, user.getDueDate());

        // Inserting Row
        db.insert(TABLE_USER, null, userValues);
        db.close();
    }

    /**
     * This method is to create user record
     *
     * @param userAppointment
     */
    public void addAppointment(Appointment userAppointment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues appointmentValues = new ContentValues();
        appointmentValues.put(COLUMN_USER_EMAIL_APP, userAppointment.getEmail());
        appointmentValues.put(COLUMN_USER_DESC_APP, userAppointment.getAppointmentDesc());
        appointmentValues.put(COLUMN_USER_DATE_APP, userAppointment.getAppointmentDate());
        appointmentValues.put(COLUMN_USER_TIME_APP, userAppointment.getAppointmentTime());

        // Inserting Row
        db.insert(TABLE_APPOINTMENT, null, appointmentValues);
        db.close();
    }


    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public User getUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_NAME,
                COLUMN_USER_EMAIL,
                COLUMN_USER_DUE_DATE
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order

        User user = new User();

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setDueDate(cursor.getString(cursor.getColumnIndex(COLUMN_USER_DUE_DATE)));
                // Adding user record to list
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return user;
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<Appointment> getAllUserAppointments(String email) {
        // array of columns to fetch

        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL_APP,
                COLUMN_USER_DESC_APP,
                COLUMN_USER_DATE_APP,
                COLUMN_USER_TIME_APP
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_DATE_APP + " ASC";
        List<Appointment> userAppointmentList = new ArrayList<Appointment>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_APPOINTMENT, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Appointment userAppointment = new Appointment();
                userAppointment.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                userAppointment.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL_APP)));
                userAppointment.setAppointmentDesc(cursor.getString(cursor.getColumnIndex(COLUMN_USER_DESC_APP)));
                userAppointment.setAppointmentDate(cursor.getString(cursor.getColumnIndex(COLUMN_USER_DATE_APP)));
                userAppointment.setAppointmentTime(cursor.getString(cursor.getColumnIndex(COLUMN_USER_TIME_APP)));

                // Adding user record to list
                userAppointmentList.add(userAppointment);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userAppointmentList;
    }
}