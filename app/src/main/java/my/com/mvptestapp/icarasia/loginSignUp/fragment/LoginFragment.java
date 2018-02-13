package my.com.mvptestapp.icarasia.loginSignUp.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import my.com.mvptestapp.icarasia.R;
import my.com.mvptestapp.icarasia.loginSignUp.login.presenter.LoginPresenter;
import my.com.mvptestapp.icarasia.loginSignUp.login.view.ILoginView;
import my.com.mvptestapp.icarasia.main.MainActivity;
import my.com.mvptestapp.icarasia.util.SnackBarUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements ILoginView {

    private static final String EMAIL_STATE = "email";
    private static final String PASSWORD_STATE = "password";

    @BindView(R.id.emailEt)
    EditText emailEt;
    @BindView(R.id.passwordEt)
    EditText passwordEt;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.mainLayout)
    FrameLayout mainLayout;
    private LoginPresenter loginPresenter;

    public LoginFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        loginPresenter = new LoginPresenter(this, getContext());
        loginPresenter.setProgressBar(false);
    }

    @OnClick(R.id.loginBtn)
    public void onLogin(View v) {
        loginPresenter.setProgressBar(true);
        loginPresenter.isLogin(emailEt.getText().toString(), passwordEt.getText().toString());
    }

    @Override
    public void onClearData() {
        emailEt.setText("");
        passwordEt.setText("");
    }

    @Override
    public void onLoginResult(boolean status, String msg, Parcelable wUser) {
        loginPresenter.setProgressBar(false);
        SnackBarUtil.displaySnackbar(mainLayout, getActivity(), msg);
        if (status && wUser != null) {
            getActivity().finish();
            MainActivity.start(getActivity(), wUser);
        }
    }

    @Override
    public void setProgressBar(boolean isVisible) {
        if (progressBar == null) return;
        if (isVisible) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EMAIL_STATE, emailEt.getText().toString());
        outState.putString(PASSWORD_STATE, passwordEt.getText().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
            emailEt.setText(savedInstanceState.getString(EMAIL_STATE));
            passwordEt.setText(savedInstanceState.getString(PASSWORD_STATE));
        }
    }
}
