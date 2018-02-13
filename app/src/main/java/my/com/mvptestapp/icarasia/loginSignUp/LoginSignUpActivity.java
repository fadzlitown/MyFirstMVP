package my.com.mvptestapp.icarasia.loginSignUp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.com.mvptestapp.icarasia.R;
import my.com.mvptestapp.icarasia.loginSignUp.adapter.TabPagerAdapter;

public class LoginSignUpActivity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginSignUpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        TabPagerAdapter mAdapter = new TabPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        tablayout.setupWithViewPager(viewPager);
    }

    public void setViewPager(int no){
        viewPager.setCurrentItem(no);
    }
}
