package my.com.mvptestapp.icarasia.main.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import my.com.mvptestapp.icarasia.R;
import my.com.mvptestapp.icarasia.main.dialog.listener.SavePhoneListener;
import my.com.mvptestapp.icarasia.main.dialog.presenter.EditPhonePresenter;
import my.com.mvptestapp.icarasia.main.dialog.view.IEditPhoneView;
import my.com.mvptestapp.icarasia.widget.PhoneNumberTextWatcher;

public class EditPhoneDialogFragment extends DialogFragment implements IEditPhoneView {

    private static final String ARG_EMAIL = "arg_email";
    private static final String ARG_PHONE = "arg_phone";
    private EditText phoneNoEt;
    private boolean isError = false;
    private EditPhonePresenter editPhonePresenter;
    private String phone;
    private SavePhoneListener savePhoneListener;
    private DialogInterface mDialog;

    public static EditPhoneDialogFragment newInstance(String email, String userPhone) {
        EditPhoneDialogFragment fragment = new EditPhoneDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EMAIL, email);
        args.putString(ARG_PHONE, userPhone);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        String email = "";
        if (getArguments() != null) {
            email = getArguments().getString(ARG_EMAIL);
            phone = getArguments().getString(ARG_PHONE);
        }
        editPhonePresenter = new EditPhonePresenter(getContext(), this, email);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogStyle)
                .setTitle(getString(R.string.phone_no_title))
                .setMessage(getString(R.string.edit_your_number_msg))
                .setPositiveButton(getString(R.string.update_btn), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editPhonePresenter.onEditPhone(phoneNoEt.getText().toString());
                    }
                })
                .setNegativeButton(getString(R.string.cancel_btn), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isError = false;
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_edit_dialog, null, false);
        phoneNoEt = (EditText) view.findViewById(R.id.phoneNo);
        phoneNoEt.addTextChangedListener(new PhoneNumberTextWatcher(phoneNoEt));
        if (!TextUtils.isEmpty(phone)) phoneNoEt.setText(phone);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void checkField(boolean status, String msg) {
        this.isError = status;
        if (!isError) {
            mDialog.dismiss();
        }
        if (savePhoneListener != null) {
            savePhoneListener.onSavePhoneName(phoneNoEt.getText().toString(), msg, isError);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        this.mDialog = dialog;
    }

    public void setSavePhoneListener(SavePhoneListener savePhoneListener) {
        this.savePhoneListener = savePhoneListener;
    }
}