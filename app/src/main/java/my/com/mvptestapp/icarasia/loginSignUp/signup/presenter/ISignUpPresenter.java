package my.com.mvptestapp.icarasia.loginSignUp.signup.presenter;

import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by fadzlirazali on 04/12/2016.
 */

public interface ISignUpPresenter {
    void clearData();

    void isRegister(@NonNull EditText email, @NonNull EditText password, EditText firstName, EditText lastName, EditText phoneNo, Spinner userType);

    void setProgressBar(boolean isVisible);

}
