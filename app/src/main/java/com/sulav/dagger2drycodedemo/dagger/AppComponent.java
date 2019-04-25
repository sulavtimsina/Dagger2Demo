package com.sulav.dagger2drycodedemo.dagger;

import com.sulav.dagger2drycodedemo.DaggerApplication;
import com.sulav.dagger2drycodedemo.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(DaggerApplication application);

    void inject(MainActivity mainActivity);
}
