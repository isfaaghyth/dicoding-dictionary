package app.isfaaghyth.dictionary.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.isfaaghyth.dictionary.R;
import app.isfaaghyth.dictionary.util.BulkEntriesHelper;
import io.isfaaghyth.rak.Rak;

/**
 * Created by isfaaghyth on 7/28/18.
 * github: @isfaaghyth
 */

public class SplashActivity extends AppCompatActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Rak.isExist("init")) {
            if (!(Boolean) Rak.grab("init")) {
                new BulkEntriesHelper(this).execute();
            }
        }
    }

}
