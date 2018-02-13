package my.com.mvptestapp.icarasia.model;

import android.content.Context;
import android.text.TextUtils;

import java.util.regex.Pattern;

import my.com.mvptestapp.icarasia.R;


/**
 * Created by fadzlirazali on 04/12/2016.
 */

public class Validation {
    public static String checkUserIsValid(Context mContext, String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            return mContext.getString(R.string.field_empty_msg);
        }
        if (!isEmailValid(email)) {
            return mContext.getString(R.string.invalid_email_msg);
        }

        if (!isPasswordValid(password)) {
            return mContext.getString(R.string.invalid_password_msg);
        }
        return "";
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).matches();
    }

    /** regex at least 1 special char and at least 8 charter*/
    public static boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile("^(?=.*?[#?!@$%^&*-]).{8,}$");
        return pattern.matcher(password).matches();
    }

    public static boolean isPhoneValid(String phone) {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{7,8}");
        return  pattern.matcher(phone).matches();

    }
}
