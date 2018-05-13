package com.fullerton.project.blissfulmoms;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;

import com.fullerton.project.blissfulmoms.models.User;
import com.fullerton.project.blissfulmoms.utils.SQLiteHelper;
import com.fullerton.project.blissfulmoms.utils.TextBoxValidation;

import java.util.Calendar;

/**
 * Created by Saranya A.
 */

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
/*This is the registration page of the application which allows the user to register with valid credentials'*/
    private final AppCompatActivity activity = RegistrationActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputLayout textInputLayoutAge;
    private TextInputLayout textInputLayoutWeight;
    private TextInputLayout textInputLayoutDueDate;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private TextInputEditText textInputEditTextAge;
    private TextInputEditText textInputEditWeight;
    private TextInputEditText textInputEditDueDate;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private User user;

    private InputMethodManager im;
    private TextBoxValidation textValidator;
    private SQLiteHelper storageHelper;

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        textInputLayoutAge = (TextInputLayout) findViewById(R.id.textInputLayoutAge);
        textInputLayoutDueDate = (TextInputLayout) findViewById(R.id.textInputLayoutDueDate);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
        textInputEditTextAge = (TextInputEditText) findViewById(R.id.textInputEditTextAge);
        textInputEditDueDate = (TextInputEditText) findViewById(R.id.textInputEditDueDate);
        textInputEditDueDate.setInputType(InputType.TYPE_NULL);
        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
        textInputEditDueDate.setOnFocusChangeListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        user = new User();
        textValidator = new TextBoxValidation(activity);
        storageHelper = new SQLiteHelper(activity);
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonRegister:
                createUser();
                break;
            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void createUser() {
        user.setName(textInputEditTextName.getText().toString().trim());
        user.setEmail(textInputEditTextEmail.getText().toString().trim());
        user.setPassword(textInputEditTextPassword.getText().toString().trim());
        user.setAge(Integer.parseInt(textInputEditTextAge.getText().toString().trim()));
        user.setDueDate(textInputEditDueDate.getText().toString().trim());
        storageHelper.addUser(user);
        Intent homeIntent = new Intent(activity, LoginActivity.class);
        startActivity(homeIntent);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.textInputEditDueDate:
                if (hasFocus) {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    textInputEditDueDate.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
                break;
        }
    }
}
