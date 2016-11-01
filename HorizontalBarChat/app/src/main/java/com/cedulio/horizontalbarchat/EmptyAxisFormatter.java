package com.cedulio.horizontalbarchat;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by cedulio on 01/11/16.
 */

public class EmptyAxisFormatter implements IAxisValueFormatter {

    public EmptyAxisFormatter() {

    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return "";
    }

    @Override
    public int getDecimalDigits() {
        return 0;
    }


}
