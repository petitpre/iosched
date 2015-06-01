package com.google.android.apps.iosched;

import android.app.Application;
import android.widget.Toast;


/**
 * Created by nicolas on 05/09/2014.
 */
public class IOApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        fr.spoonware.SpoonwareAndroid.init(this,
                "http://192.168.56.1:8080/rest",
                "iosched-f3949e81-72ae-4314-b558-eb8fdd42ccc2",  // Application ID
                "6ntj53qj7bseo1ajp503mfqs76");  // Application TOKEN
    }
}
