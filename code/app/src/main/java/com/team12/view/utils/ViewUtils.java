package com.team12.view.utils;

import android.widget.EditText;

/**
 * This class provides static methods that are useful
 * to the developer.
 */
public class ViewUtils {

    /**
     * This is a static method that makes it
     * easier for the developer to get the user's
     * input from the text boxes.
     */
    public static String getTextFromEditTextElement(EditText element) {
        return element.getText().toString().trim();
    }
}
