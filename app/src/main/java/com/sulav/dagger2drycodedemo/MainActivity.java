package com.sulav.dagger2drycodedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject SharedPreferences prefs;
    @Inject Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        Log.d("DEBUG_TAG", "onCreate: Value before saving: " + prefs.getInt("Number", 0));
        prefs.edit().putInt("Number", 6).apply();
        Log.d("DEBUG_TAG", "onCreate: Value after saving: " + prefs.getInt("Number", 0));
    }
}
