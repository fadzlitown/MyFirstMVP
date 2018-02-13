package my.com.mvptestapp.icarasia;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by fadzlirazali on 02/12/2016.
 */

public class MyApplication extends Application {

   public MyApplication getInstance(){
       return this;
   }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getInstance());
    }
}
