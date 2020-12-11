package com.example.moneytracker50;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordBarChart extends BarChart {

    String[] months = new DateFormatSymbols().getShortMonths();

    public RecordBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        setNoDataText("No Data Available");
        getDescription().setEnabled(false);
        animateY(500);
        getLegend().setEnabled(false);
        setScaleEnabled(false);
        getXAxis().setDrawGridLines(false);
        getXAxis().setGranularityEnabled(true);
        getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return months[(int)value];
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setRecordData(List<RecordsByDate> list){
        Map<String, Float> dataValues = new HashMap<>();
        for (RecordsByDate monthlyRecord : list)
            dataValues.put(Formatter.formatMonthName(monthlyRecord.getYearMonth()) , monthlyRecord.getMoney().floatValue());

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for(int i = 0; i < months.length; i++)
            barEntries.add(new BarEntry(i, dataValues.getOrDefault(months[i], 0f)));

        setData(new BarData(new BarDataSet(barEntries, null)));
        notifyDataSetChanged();
    }
}