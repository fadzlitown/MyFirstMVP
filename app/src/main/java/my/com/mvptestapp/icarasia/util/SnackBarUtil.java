package my.com.mvptestapp.icarasia.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import my.com.mvptestapp.icarasia.R;


public class SnackBarUtil {
    public static void displaySnackbar(View view, Context context, String s) {
        Snackbar snack = Snackbar.make(view, s, Snackbar.LENGTH_LONG);
        View subView = snack.getView();
        subView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        TextView textView = (TextView) subView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(context.getResources().getColor(android.R.color.white));
        snack.show();
    }
}