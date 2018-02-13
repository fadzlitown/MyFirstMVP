package my.com.mvptestapp.icarasia.loginSignUp.login.view;

import android.os.Parcelable;
import android.support.annotation.Nullable;

/**
 * Created by fadzlirazali on 02/12/2016.
 */

public interface ILoginView {
     void onClearData();

     void onLoginResult(boolean status, String msg , @Nullable Parcelable user);

     void setProgressBar(boolean isVisible);

}
