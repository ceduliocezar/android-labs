package com.cedulio.horizontalbarchat;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
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
        return dateFormatter.format(Calendar.getInstance().getTime()).toUpperCase();
    }

    @Override
    public int getDecimalDigits() {
        return 0;
    }


}
