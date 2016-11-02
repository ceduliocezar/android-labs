package com.cedulio.horizontalbarchat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.ViewGroup;

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
        initViewReferences();
        customizeChart();

        float maxValue = 50;
        int totalEntries = 12;

        generateEntries(totalEntries, maxValue);
        cutomizeAxis(maxValue);

        setData();

        notifyDataChanges();

        defineChartHeight();
    }

    private void notifyDataChanges() {
        mChart.getData().notifyDataChanged();
        mChart.notifyDataSetChanged();
    }

    private void initViewReferences() {
        mChart = (HorizontalBarChart) findViewById(R.id.chart1);
    }

    private void defineChartHeight() {
        ViewGroup.LayoutParams param = mChart.getLayoutParams();

        int heightInPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, entries
                .size() * 40, getResources().getDisplayMetrics());

        param.height = heightInPixel;

        mChart.setLayoutParams(param);
        mChart.invalidate();
    }

    private void cutomizeAxis(float maxValue) {

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setEnabled(true);
        xAxis.setLabelCount(entries.size());
        xAxis.setValueFormatter(new PeriodAxisFormatter());

        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawGridLines(true);
        yAxis.setLabelCount(6, true);
        yAxis.setAxisMinimum(0f);
        yAxis.setGridColor(ColorTemplate.rgb("#aeafaf"));
        yAxis.setValueFormatter(new EmptyAxisFormatter());
        yAxis.setAxisMaximum((float) (maxValue + maxValue * 0.40));
        yAxis.setTypeface(Typeface.createFromAsset(getAssets(), "SourceSansPro-Bold.ttf"));
        yAxis.setTextSize(11f);
        yAxis.setTextColor(ColorTemplate.rgb("#004060"));

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
        data.setValueFormatter(new FixedCurrencyFormatter());
        data.setBarWidth(barWidth);
        mChart.setData(data);

    }

    private void generateEntries(int count, float range) {
        float spaceForBar = 10f;

        entries = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range);
            BarEntry entry = new BarEntry(i * spaceForBar, val);
            entries.add(entry);
        }
    }

}
