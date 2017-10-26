package com.test.santiago.condorlabs;

import android.app.Application;

/**
 * Created by Santiago on 10/26/17.
 */

public class App extends Application {

    public static String token;

    @Override
    public void onCreate() {
        super.onCreate();
        token = "";
    }
}
