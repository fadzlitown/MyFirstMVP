package my.com.mvptestapp.icarasia.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import my.com.mvptestapp.icarasia.R;
import my.com.mvptestapp.icarasia.main.dialog.EditPhoneDialogFragment;
import my.com.mvptestapp.icarasia.main.dialog.listener.SavePhoneListener;
import my.com.mvptestapp.icarasia.model.User;
import my.com.mvptestapp.icarasia.util.SnackBarUtil;

public class MainActivity extends AppCompatActivity implements SavePhoneListener {

    private static final String USER_OBJ = "user_obj";
    @BindView(R.id.fullname)
    TextView fullName;
    @BindView(R.id.phoneNo)
    TextView phoneNo;
    @BindView(R.id.editPhoneBtn)
    Button editPhoneBtn;
    @BindView(R.id.userTypeBtn)
    Button userTypeBtn;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private String userMail;
    private String userPhone;
    private String userType;

    public static void start(Context context, Parcelable obj) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_OBJ, obj);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        String[] arrays = getResources().getStringArray(R.array.user_types);
        if (getIntent().hasExtra(USER_OBJ)) {
            User user = Parcels.unwrap(getIntent().getParcelableExtra(USER_OBJ));
            userMail = user.getEmail();
            userPhone = user.getPhoneNo();
            userType = arrays[user.getUserType()];

            if (!TextUtils.isEmpty(user.getFirstName()) || !TextUtils.isEmpty(user.getLastName())) {
                fullName.setText(getString(R.string.hello)+ " " + user.getFirstName() + " " + user.getLastName());
            }
            if (!TextUtils.isEmpty(userPhone)) {
                phoneNo.setText(userPhone);
            }
        }
    }

    @OnClick(R.id.userTypeBtn)
    public void onUserType(View v) {
        Toast.makeText(this, getString(R.string.your_acc_type_is) + userType, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.editPhoneBtn)
    public void onEditPhone(View v) {
        EditPhoneDialogFragment dialog = EditPhoneDialogFragment.newInstance(userMail, userPhone);
        dialog.setSavePhoneListener(this);
        dialog.show(getSupportFragmentManager(), "");
    }

    @OnClick(R.id.logOutBtn)
    public void onLogOut(View v) {
        final ProgressDialog progress = new ProgressDialog(MainActivity.this);
        progress.setTitle(getString(R.string.logging_out_msg));
        progress.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                progress.dismiss();
                finish();
                SplashScreenActivity.start(MainActivity.this);
            }
        }, 5000);
    }

    @Override
    public void onSavePhoneName(String phone, String msg, boolean isError) {
        if (!isError) {
            this.userPhone = phone;
            phoneNo.setText(phone);
        }
        SnackBarUtil.displaySnackbar(activityMain, this, msg);
    }
}
