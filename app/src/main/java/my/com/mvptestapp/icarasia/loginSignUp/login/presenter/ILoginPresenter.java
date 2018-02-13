package my.com.mvptestapp.icarasia.loginSignUp.login.presenter;

/**
 * Created by fadzlirazali on 02/12/2016.
 */

public interface ILoginPresenter {
    void clearData();

    void isLogin(String name, String password);

    void setProgressBar(boolean isVisible);
}
