package my.com.mvptestapp.icarasia;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

import my.com.mvptestapp.icarasia.loginSignUp.LoginSignUpActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;

/**
 * Created by fadzlirazali on 28/12/2016.
 */

@RunWith(AndroidJUnit4.class)
public class LoginUITest {

    @Rule
    public ActivityTestRule<LoginSignUpActivity> menuActivityTestRule =
            new ActivityTestRule<>(LoginSignUpActivity.class, true, true);


    @Test
    public void clickLoginWithoutInput() {
        //click on the login button
        onView(withId(R.id.loginBtn)).perform(click());

        //check the msg matches
        onView(withId(android.support.design.R.id.snackbar_text)).check(matches(allOf(withText(R.string.field_empty_msg), isDisplayed())));
    }

    @Test
    public void clickLoginWithInvalidEmail() {
        String emailAddress = "testergmail.com";
        String password = "password1!";

        //find the email and type the invalid email
        onView(withId(R.id.emailEt)).perform(typeText(emailAddress), closeSoftKeyboard());

        //find the password and type in the valid password
        onView(withId(R.id.passwordEt)).perform(typeText(password), closeSoftKeyboard());

        //click on the login button
        onView(withId(R.id.loginBtn)).perform(click());

        //check the msg matches
        onView(withId(android.support.design.R.id.snackbar_text)).check(matches(allOf(withText(R.string.invalid_email_msg), isDisplayed())));
    }

    @Test
    public void clickLoginWithInvalidPassword() {
        String emailAddress = "tester@gmail.com";
        String password = "password";

        //find the email and type the valid email
        onView(withId(R.id.emailEt)).perform(typeText(emailAddress), closeSoftKeyboard());

        //find the password and type in the invalid password
        onView(withId(R.id.passwordEt)).perform(typeText(password), closeSoftKeyboard());

        //click on the login button
        onView(withId(R.id.loginBtn)).perform(click());

        //check the msg matches
        onView(withId(android.support.design.R.id.snackbar_text)).check(matches(allOf(withText(R.string.invalid_password_msg), isDisplayed())));
    }

    @Test
    public void clickLoginWithInvalidUser() {
        String emailAddress = "tester223@gmail.com";
        String password = "password1!";

        //find the email and type the valid email with not existed user
        onView(withId(R.id.emailEt)).perform(typeText(emailAddress), closeSoftKeyboard());

        //find the password and type the valid password
        onView(withId(R.id.passwordEt)).perform(typeText(password), closeSoftKeyboard());

        //click on the login button
        onView(withId(R.id.loginBtn)).perform(click());

        //check the msg matches
        onView(withId(android.support.design.R.id.snackbar_text)).check(matches(allOf(withText(R.string.user_not_existed), isDisplayed())));
    }

    @Test
    public void clickSignUpAndSignInWithValidUser() {
        String emailAddress = UUID.randomUUID().toString()+"@gmail.com";
        String password = "password1!";
        String fname = "tester";
        String lname = "qa";
        String phone = "60124420989";
        String userType = "Broker";

        //perform an action by swiping  (Sign up fragment)
        onView(withId(R.id.view_pager)).perform(swipeLeft());

        //Register with valid user
        onView(withId(R.id.email)).perform(typeText(emailAddress), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.firstName)).perform(typeText(fname), closeSoftKeyboard());
        onView(withId(R.id.lastName)).perform(typeText(lname), closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText(phone), closeSoftKeyboard());

        onView(withId(R.id.userTypeSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(userType))).perform(click());

        //click on the sign up button
        onView(withId(R.id.signUpBtn)).perform(click());

        //check the msg matches
        onView(withId(android.support.design.R.id.snackbar_text)).check(matches(allOf(withText(R.string.saved_msg), isDisplayed())));

        //find the email and type the valid email that has been signed up before
        onView(withId(R.id.emailEt)).perform(typeText(emailAddress), closeSoftKeyboard());

        //find the password and type the valid password
        onView(withId(R.id.passwordEt)).perform(typeText(password), closeSoftKeyboard());

        //click on the login button
        onView(withId(R.id.loginBtn)).perform(click());
    }
}
