package com.example.ceduliosilva.roboeletricmock;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Created by cedulio.silva <cedulio.silva@pixida.com.br> on 22/05/2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {

    @Test
    public void test_button_clicked() throws Exception {


        MainActivity activity = Robolectric.setupActivity(MainActivity.class);

        activity.findViewById(R.id.bt).performClick();

        assertEquals("text", ((TextView) activity.findViewById(R.id.tv)).getText().toString());

    }
}