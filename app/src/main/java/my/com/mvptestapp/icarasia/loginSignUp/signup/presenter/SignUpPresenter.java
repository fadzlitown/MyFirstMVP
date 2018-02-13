package my.com.mvptestapp.icarasia.loginSignUp.signup.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Spinner;

import io.realm.Realm;
import my.com.mvptestapp.icarasia.R;
import my.com.mvptestapp.icarasia.loginSignUp.signup.view.ISignUpView;
import my.com.mvptestapp.icarasia.model.User;
import my.com.mvptestapp.icarasia.model.Validation;

/**
 * Created by fadzlirazali on 04/12/2016.
 */
public class SignUpPresenter implements ISignUpPresenter {

    private final ISignUpView mView;
    private final Context mContext;

    public SignUpPresenter(ISignUpView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    @Override
    public void clearData() {
        mView.onClearData();
    }

    @Override
    public void isRegister(@NonNull final EditText email, @NonNull final EditText password, final EditText firstName, final EditText lastName, final EditText phoneNo, final Spinner userType) {

        /** Validation */
        if (TextUtils.isEmpty(email.getText().toString())) {
            mView.onRegisterResult(false, mContext.getString(R.string.field_empty_msg), email);
            return;
        } else if (!Validation.isEmailValid(email.getText().toString())) {
            mView.onRegisterResult(false, mContext.getString(R.string.invalid_email_msg), email);
            return;
        }
        if (TextUtils.isEmpty(password.getText().toString())) {
            mView.onRegisterResult(false, mContext.getString(R.string.field_empty_msg), password);
            return;
        } else if (!Validation.isPasswordValid(password.getText().toString())) {
            mView.onRegisterResult(false, mContext.getString(R.string.invalid_password_msg), password);
            return;
        }
        if (TextUtils.isEmpty(phoneNo.getText().toString())) {
            mView.onRegisterResult(false, mContext.getString(R.string.field_empty_msg), phoneNo);
            return;
        } else if (!Validation.isPhoneValid(phoneNo.getText().toString())) {
            mView.onRegisterResult(false, mContext.getString(R.string.invalid_phone_msg), phoneNo);
            return;
        }
        if (userType.getSelectedItemPosition() == 0) {
            mView.onRegisterResult(false, mContext.getString(R.string.invalid_user_type_msg), userType);
            return;
        }

        /** Check User Email*/
        Realm realm = Realm.getDefaultInstance();
        final long count = realm.where(User.class).equalTo("email", email.getText().toString()).count();
        if (count != 0) {
            mView.onRegisterResult(false, mContext.getString(R.string.email_existed_msg), userType);
            return;
        }

        /** Add User */
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class, email.getText().toString());
                user.setPassword(password.getText().toString());
                user.setFirstName(firstName.getText().toString());
                user.setLastName(lastName.getText().toString());
                user.setPhoneNo(phoneNo.getText().toString());
                user.setUserType(userType.getSelectedItemPosition());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                mView.onRegisterResult(true, mContext.getString(R.string.saved_msg), null);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                mView.onRegisterResult(false, error.getMessage(), null);
            }
        });
    }

    @Override
    public void setProgressBar(boolean isVisible) {
        mView.setProgressBar(isVisible);
    }
}
