package my.com.mvptestapp.icarasia.main.dialog.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import io.realm.Realm;
import my.com.mvptestapp.icarasia.R;
import my.com.mvptestapp.icarasia.main.dialog.view.IEditPhoneView;
import my.com.mvptestapp.icarasia.model.User;
import my.com.mvptestapp.icarasia.model.Validation;

/**
 * Created by fadzlirazali on 05/12/2016.
 */

public class EditPhonePresenter implements IEditPhonePresenter {

    private final IEditPhoneView mView;
    private final String email;
    private final Context mContext;
    private boolean isError=true;

    public EditPhonePresenter(Context context, IEditPhoneView view, String email) {
        this.mContext=context;
        this.mView = view;
        this.email=email;
    }

    @Override
    public void onEditPhone(@NonNull final String phoneNo) {
        if(TextUtils.isEmpty(phoneNo)){
            mView.checkField(isError, mContext.getString(R.string.field_empty_msg));
            return;

        } else if(!Validation.isPhoneValid(phoneNo)){
            mView.checkField(isError, mContext.getString(R.string.invalid_phone_msg));
            return;
        }

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                User user = bgRealm.where(User.class).equalTo("email", email).findFirst();
                user.setPhoneNo(phoneNo);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                isError =false;
                mView.checkField(isError, mContext.getString(R.string.edit_successfully_msg));
            }
        });
    }
}
