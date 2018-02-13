package my.com.mvptestapp.icarasia.main;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLooper;

import my.com.mvptestapp.icarasia.BuildConfig;
import my.com.mvptestapp.icarasia.loginSignUp.LoginSignUpActivity;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by fadzlirazali on 24/12/2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class SplashScreenActivityTest {

    private SplashScreenActivity mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = new SplashScreenActivity();
    }

    @Test
    public void testNextActivityWasLaunchedWithIntent() {
        /** Check SplashScreen is started */
        assertFalse(mActivity.isFinishing());
        mActivity.delay();
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks();

        /** Check SplashScreen will be finish after delay */
        assertTrue(mActivity.isFinishing());

        Intent expectedIntent = new Intent(mActivity, LoginSignUpActivity.class);
        /** Check expectedIntent is not null */
        assertNotNull(expectedIntent);

        /** After SplashScreen, it will go to Login Activity */
        assertEquals(expectedIntent, shadowOf(mActivity).getNextStartedActivity());
    }
}