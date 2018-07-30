package app.isfaaghyth.dictionary;

import android.app.Application;

import io.isfaaghyth.rak.Rak;

/**
 * Created by isfaaghyth on 7/30/18.
 * github: @isfaaghyth
 */

public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();
        Rak.initialize(this);
    }

}
