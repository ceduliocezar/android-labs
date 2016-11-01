package com.cedulio.horizontalbarchat;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by TECBMCCS on 01/11/16.
 */

public class CurrencyMonthFormatter implements IValueFormatter {

    private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM");

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

        return dateFormatter.format(Calendar.getInstance().getTime()).toUpperCase() + " " + currencyFormatter
                .format
                (value);

    }
}
