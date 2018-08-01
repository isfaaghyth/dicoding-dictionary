package app.isfaaghyth.dictionary.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
        if (!Rak.isExist("init") || !(Boolean) Rak.grab("init")) {
            new BulkEntriesHelper(this).execute();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }, 2000);
        }
    }

}
