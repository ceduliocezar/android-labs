package com.cedulio.horizontalbarchat;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by cedulio on 01/11/16.
 */

public class PeriodAxisFormatter implements IAxisValueFormatter {

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM / yyyy");

    public PeriodAxisFormatter() {

    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        Log.d("PeriodAxisFormatter", "entries:" + Arrays.toString(axis.mEntries));
        Log.d("PeriodAxisFormatter", "index:" + getIndex(value, axis));

        Log.d("PeriodAxisFormatter", "Axis value: " + value);
        return dateFormatter.format(Calendar.getInstance().getTime()).toUpperCase();
    }

    private int getIndex(float value, AxisBase axis) {

        for (int i = 0; i < axis.mEntries.length; i++) {
            if (axis.mEntries[i] == value) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getDecimalDigits() {
        return 0;
    }


}
