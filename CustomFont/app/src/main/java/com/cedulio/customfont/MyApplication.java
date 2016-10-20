package com.cedulio.customfont;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by ceduliocezar on 01/09/16.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("debug","Oswald-Stencbab");
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Oswald-Stencbab.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}
