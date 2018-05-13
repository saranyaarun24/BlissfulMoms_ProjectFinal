package com.fullerton.project.blissfulmoms.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class TextBoxValidation {
    private Context context;

    /**
     * constructor
     *
     * @param context
     */
    public TextBoxValidation(Context context) {
        this.context = context;
    }


    /**
     * method to Hide keyboard
     *
     * @param view
     */
    private void hideKeyboardFrom(View view) {
        InputMethodManager methodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        methodManager.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /**
     * method to check the textbox is not empty .
     *
     * @param editBoxText
     * @param editBoxLayout
     * @param message
     * @return
     */
    public boolean isTextBoxEmpty(TextInputEditText editBoxText, TextInputLayout editBoxLayout, String message) {
        String inputText = editBoxText.getText().toString().trim();
        if (inputText.isEmpty()) {
            editBoxLayout.setError(message);
            hideKeyboardFrom(editBoxText);
            return false;
        } else {
            editBoxLayout.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * Check for matching text from two different text box whether they match
     *
     * @param firstTextContent
     * @param secondTextContent
     * @param editBoxLayout
     * @param message
     * @return
     */
    public boolean isMatchingText(TextInputEditText firstTextContent, TextInputEditText secondTextContent, TextInputLayout editBoxLayout, String message) {
        String value1 = firstTextContent.getText().toString().trim();
        String value2 = secondTextContent.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            editBoxLayout.setError(message);
            hideKeyboardFrom(secondTextContent);
            return false;
        } else {
            editBoxLayout.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * method to check email field contains valid text in valid email format.
     *
     * @param textInputEditText
     * @param textInputLayout
     * @param message
     * @return
     */
    public boolean isValidEmail(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String emailText = textInputEditText.getText().toString().trim();
        if (emailText.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
}