package com.example.moneytracker50;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AnalyticsFragment extends Fragment {

    View view;
    BarChart barChart;

    Calendar calendar = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.fragment_analytics, container, false);

        List<MontlyRecord> list = RecordDatabase.getInstance(this.getContext()).recordDao().getAmountByMonth(calendar.getTime());
        ArrayList<BarEntry> dataValues = new ArrayList<>();
        ArrayList<String> xAxisLabel = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            dataValues.add(new BarEntry(i, list.get(i).getMoney().longValue()));
            xAxisLabel.add(new SimpleDateFormat("MMM").format(list.get(i).getYearMonth()));
        }
        BarDataSet barDataSet = new BarDataSet(dataValues, "Monthly Total");

        barChart = view.findViewById(R.id.mp_BarChart);
        barDataSet.setColor(Color.BLUE, 70);
        barChart.setNoDataText("No Data Available");
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getLegend().setEnabled(true);
        barChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xAxisLabel.get((int)value);
            }
        });

        barChart.setData(new BarData(barDataSet));
        barChart.invalidate();

         return view;
    }


}

