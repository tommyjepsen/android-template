package com.beanielab.template;

import android.app.Application;


/**
 * Created by tommyjepsen on 21/11/15.
 */
public class App extends Application {

    private static App instance;


    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

    public static App getInstance() {
        return instance;
    }


}
