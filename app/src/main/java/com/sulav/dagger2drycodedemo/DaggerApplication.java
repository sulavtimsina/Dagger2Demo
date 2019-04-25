package com.sulav.dagger2drycodedemo;

import android.app.Application;

import com.sulav.dagger2drycodedemo.dagger.AppComponent;
import com.sulav.dagger2drycodedemo.dagger.AppModule;
import com.sulav.dagger2drycodedemo.dagger.DaggerAppComponent;

public class DaggerApplication extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent(){return appComponent;}
}
