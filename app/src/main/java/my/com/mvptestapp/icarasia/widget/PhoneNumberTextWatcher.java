package my.com.mvptestapp.icarasia.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by fadzlirazali on 07/12/2016.
 */
public class PhoneNumberTextWatcher implements TextWatcher {
    private final EditText phone;
    private String lastCharPhone=" ";

    public PhoneNumberTextWatcher(EditText editText) {
        this.phone=editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        int digits = phone.getText().toString().length();
        if (digits > 1)
            lastCharPhone = phone.getText().toString().substring(digits-1);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        int digits = phone.getText().toString().length();

        if (!lastCharPhone.equals("-")) {
            if (digits == 4) {
                phone.append("-");
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
