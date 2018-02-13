package my.com.mvptestapp.icarasia.loginSignUp.signup.view;

import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by fadzlirazali on 04/12/2016.
 */

public interface ISignUpView {

    void onClearData();

    void onRegisterResult(boolean status, String msg, @Nullable View view);

    void setProgressBar(boolean isVisible);
}
