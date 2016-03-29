package com.google.android.apps.iosched;

import android.app.Application;
import com.instabug.library.IBGInvocationEvent;
import com.instabug.library.Instabug;


/**
 * Created by nicolas on 05/09/2014.
 */
public class IOApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new Instabug.Builder(this, "e8b1f7d408208b69c3a4d5ddc70d1ebe")
                .setInvocationEvent(IBGInvocationEvent.IBGInvocationEventShake)
                .build();

    }
}
