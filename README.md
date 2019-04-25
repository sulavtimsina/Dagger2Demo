# Dagger2Demo
This is a most basic demo of Dagger2. In future commits, I will add comments to make it more clear. This repo will be as simple as possible so anyone can understand it.

Dagger 2


Add the following dependencies in app level build.gradle file:

implementation 'com.google.dagger:dagger:2.22.1'
annotationProcessor 'com.google.dagger:dagger-compiler:2.22.1'


The project structure should look like this:

com.sulav.dagger2drycodedemo/
├── dagger/
│   ├── AppComponent.java
│   └── AppModule.java
├── DaggerApplication.java
├── MainActivity.java

AppComponent.java
```java
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
```

**AppModule.java**
```java
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
```

**DaggerApplication.java**
```java
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
```

**MainActivity.java**
```java
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
```

**AndroidManifest.xml**

Add the following line inside the <application> tag
```xml
android:name=".DaggerApplication"
```
