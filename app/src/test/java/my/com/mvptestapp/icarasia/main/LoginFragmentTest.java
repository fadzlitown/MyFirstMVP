package my.com.mvptestapp.icarasia.main;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import my.com.mvptestapp.icarasia.BuildConfig;
import my.com.mvptestapp.icarasia.loginSignUp.fragment.LoginFragment;

import static junit.framework.Assert.assertNotNull;


/**
 * Created by fadzlirazali on 28/12/2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginFragmentTest {
    private LoginFragment mLoginFragment;

    @Before
    public void setUp() throws Exception {
        mLoginFragment = new LoginFragment();
    }

    @Test
    public void testLogin() {
        assertNotNull(mLoginFragment);
    }
}
