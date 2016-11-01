package com.cedulio.horizontalbarchat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<BarEntry> entries;

    protected HorizontalBarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        float maxValue = 50;

        generateBarEntries(12, maxValue);

        mChart = (HorizontalBarChart) findViewById(R.id.chart1);

        customizeChart();

        cutomizeAxis(maxValue);

        setData();
    }

    private void cutomizeAxis(float maxValue) {
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setEnabled(false);


        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawGridLines(true);
        yAxis.setLabelCount(6, true);
        yAxis.setAxisMinimum(0f);
        yAxis.setGridColor(ColorTemplate.rgb("#aeafaf"));
        yAxis.setValueFormatter(new EmptyAxisFormatter());
        yAxis.setAxisMaximum((float) (maxValue +  maxValue * 0.40));

        YAxis yAxisRight = mChart.getAxisRight();
        yAxisRight.setEnabled(false);
    }

    private void customizeChart() {
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false);
        mChart.setHighlightFullBarEnabled(false);
        mChart.setHighlightPerDragEnabled(false);
        mChart.setHighlightPerDragEnabled(false);
        mChart.setFitBars(true);
        mChart.getLegend().setEnabled(false);
    }

    private void setData() {

        float barWidth = 9f;

        BarDataSet set1;

        set1 = new BarDataSet(entries, "DataSet 1");
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        set1.setColor(ColorTemplate.rgb("#004060"));
        BarData data = new BarData(dataSets);
        data.setValueTextSize(15f);
        data.setValueTypeface(Typeface.createFromAsset(getAssets(), "SourceSansPro-Bold.ttf"));
        data.setValueTextColor(ColorTemplate.rgb("#004060"));
        data.setValueFormatter(new CurrencyMonthFormatter());
        data.setBarWidth(barWidth);
        mChart.setData(data);

    }

    private void generateBarEntries(int count, float range) {
        float spaceForBar = 10f;

        entries = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range);
            BarEntry entry = new BarEntry(i * spaceForBar, val);
            entries.add(entry);
        }
    }
}
