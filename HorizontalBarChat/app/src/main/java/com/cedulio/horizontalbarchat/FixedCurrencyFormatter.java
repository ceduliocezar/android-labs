package com.cedulio.horizontalbarchat;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by cedulio on 01/11/16.
 */

public class FixedCurrencyFormatter implements IValueFormatter {

    private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

        return currencyFormatter.format(value);

    }
}
