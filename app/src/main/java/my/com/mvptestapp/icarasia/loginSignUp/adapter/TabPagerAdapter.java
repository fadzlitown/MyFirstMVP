package my.com.mvptestapp.icarasia.loginSignUp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import my.com.mvptestapp.icarasia.loginSignUp.fragment.LoginFragment;
import my.com.mvptestapp.icarasia.loginSignUp.fragment.SignUpFragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private String [] name = new String[]{"LOGIN","SIGN UP"};
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public TabPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        fragments.add(new LoginFragment());
        fragments.add(new SignUpFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return name[position];
    }
}