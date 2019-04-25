package com.sulav.dagger2drycodedemo.dagger;

import android.content.Context;
import android.content.SharedPreferences;

import com.sulav.dagger2drycodedemo.DaggerApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module public class AppModule {
    private final DaggerApplication application;

    public AppModule(DaggerApplication app) {
        this.application = app;
    }

    @Provides @Singleton
    Context providesApplicationContext(){
        return application;
    }

    @Provides @Singleton
    SharedPreferences providesSharedPreferences(Context app){
        return app.getSharedPreferences("My_Prefs_Title", Context.MODE_PRIVATE);
    }
}
