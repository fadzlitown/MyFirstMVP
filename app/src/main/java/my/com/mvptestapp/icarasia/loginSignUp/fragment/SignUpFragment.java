package my.com.mvptestapp.icarasia.loginSignUp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import my.com.mvptestapp.icarasia.R;
import my.com.mvptestapp.icarasia.loginSignUp.LoginSignUpActivity;
import my.com.mvptestapp.icarasia.loginSignUp.signup.presenter.SignUpPresenter;
import my.com.mvptestapp.icarasia.loginSignUp.signup.view.ISignUpView;
import my.com.mvptestapp.icarasia.util.SnackBarUtil;
import my.com.mvptestapp.icarasia.widget.PhoneNumberTextWatcher;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements ISignUpView {

    private static final String EMAIL_STATE = "email";
    private static final String PASSWORD_STATE = "password";
    private static final String FN_STATE = "fn";
    private static final String LN_STATE = "ln";
    private static final String PHONE_STATE = "phone";
    private static final String USERTYPE_STATE = "userType";

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.firstName)
    EditText firstName;
    @BindView(R.id.lastName)
    EditText lastName;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.userTypeSpinner)
    Spinner userTypeSpinner;
    @BindView(R.id.signUpBtn)
    Button signUpBtn;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.mainLayout)
    FrameLayout mainLayout;
    private SignUpPresenter signUpPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        signUpPresenter = new SignUpPresenter(this, getContext());
        phone.addTextChangedListener(new PhoneNumberTextWatcher(phone));
    }

    @OnClick(R.id.signUpBtn)
    public void onSignUp(View v) {
        signUpPresenter.isRegister(email, password, firstName, lastName, phone, userTypeSpinner);
    }

    @Override
    public void onClearData() {
        email.setText("");
        password.setText("");
        firstName.setText("");
        lastName.setText("");
        phone.setText("");
    }

    @Override
    public void onRegisterResult(boolean status, String msg, View view) {
        signUpPresenter.setProgressBar(false);
        if (status) {
            ((LoginSignUpActivity) getActivity()).setViewPager(0);
            signUpPresenter.clearData();
        } else {
            if (view instanceof EditText) {
                ((EditText) view).setCompoundDrawablePadding(10);
                ((EditText) view).setError(msg);
            } else if (view instanceof Spinner) {
                ((TextView) ((Spinner) view).getSelectedView()).setError(msg);
            }
        }
        SnackBarUtil.displaySnackbar(mainLayout, getActivity(), msg);
    }

    @Override
    public void setProgressBar(boolean isVisible) {
        if (isVisible) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EMAIL_STATE, email.getText().toString());
        outState.putString(PASSWORD_STATE, password.getText().toString());
        outState.putString(FN_STATE, firstName.getText().toString());
        outState.putString(LN_STATE, lastName.getText().toString());
        outState.putString(PHONE_STATE, phone.getText().toString());
        outState.putInt(USERTYPE_STATE, userTypeSpinner.getSelectedItemPosition());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
            email.setText(savedInstanceState.getString(EMAIL_STATE));
            password.setText(savedInstanceState.getString(PASSWORD_STATE));
            firstName.setText(savedInstanceState.getString(FN_STATE));
            lastName.setText(savedInstanceState.getString(LN_STATE));
            phone.setText(savedInstanceState.getString(PHONE_STATE));
            userTypeSpinner.setSelection(savedInstanceState.getInt(USERTYPE_STATE));
        }
    }
}
