package my.com.mvptestapp.icarasia.loginSignUp.login.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.text.TextUtils;

import org.parceler.Parcels;

import io.realm.Realm;
import my.com.mvptestapp.icarasia.R;
import my.com.mvptestapp.icarasia.loginSignUp.login.view.ILoginView;
import my.com.mvptestapp.icarasia.model.User;
import my.com.mvptestapp.icarasia.model.Validation;

/**
 * Created by fadzlirazali on 02/12/2016.
 */

public class LoginPresenter implements ILoginPresenter {

    private final Context mContext;
    private Handler handler;
    private ILoginView mView;

    public LoginPresenter(ILoginView view, Context context) {
        this.mView= view;
        this.mContext=context;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void clearData() {
        mView.onClearData();
    }

    @Override
    public void isLogin(final String name, final String password) {
        boolean isSuccess = false;

        String msg = Validation.checkUserIsValid(mContext,name,password);
        if(TextUtils.isEmpty(msg)) {
            msg = mContext.getString(R.string.login_success_msg);
            isSuccess=true;
        }

        final boolean finalIsSuccess = isSuccess;
        final String finalMsg = msg;
        Realm realm = Realm.getDefaultInstance();
        if(!finalIsSuccess){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mView.onLoginResult(finalIsSuccess, finalMsg, null);
                }
            },2000);

        } else {
             final User user = realm.where(User.class).equalTo("email", name).equalTo("password", password).findFirst();
            final Parcelable wUser = Parcels.wrap(User.class, user);
            if(user!=null){
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mView.onLoginResult(finalIsSuccess, finalMsg, wUser);
                    }
                },2000);
            } else {
                mView.onLoginResult(false, mContext.getString(R.string.user_not_existed), null);
            }
        }
    }

    @Override
    public void setProgressBar(boolean isVisible) {
        mView.setProgressBar(isVisible);
    }
}
