package com.example.moneytracker50;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyticsFragment extends Fragment {

    View view;
    BarChart barChart;
    BarDataSet barDataSet;
    PieDataSet pieDataSet_income, pieDataSet_expense;
    PieChart pieChart_income, pieChart_expense;

    Calendar calendar = Calendar.getInstance();
    String[] months = new DateFormatSymbols().getShortMonths();
    int[] colors;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.fragment_analytics, container, false);

        setupColors();

        barChart = view.findViewById(R.id.mp_BarChart);
        styleBarChart(barChart);
        barChart.invalidate();

        pieChart_income = view.findViewById(R.id.mp_PieChart_income);
        stylePieChart(pieChart_income);
        pieChart_income.invalidate();

        pieChart_expense = view.findViewById(R.id.mp_PieChart_expense);
        stylePieChart(pieChart_expense);
        pieChart_expense.invalidate();

        reload();

        return view;
    }

    private ArrayList<PieEntry> getPieEntries(List<RecordsByCategory> pieList){
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < pieList.size(); i++) {
            pieEntries.add(
                    new PieEntry(
                            pieList.get(i).getMoney().abs().floatValue(),
                            pieList.get(i).getCategory()
                    ));
        }
        return pieEntries;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private ArrayList<BarEntry> getBarEntries(List<RecordsByMonth> list){
        Map<String, Float> dataValues = new HashMap<>();
        for (RecordsByMonth montlyRecord : list)
            dataValues.put(montlyRecord.getMonth(), montlyRecord.getMoney().floatValue());

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for(int i = 0; i < months.length; i++){
            barEntries.add(new BarEntry(i, dataValues.getOrDefault(months[i], 0f)));
        }

        return barEntries;
    }

    private void stylePieDataSet(PieDataSet pieDataSet){
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueLinePart1Length(0.5f);
        pieDataSet.setSliceSpace(5f);
        pieDataSet.setColors(colors);
    }

    private void stylePieChart(PieChart pieChart){
        pieChart.setRotationEnabled(false);
        pieChart.setExtraOffsets(0f, 40f, 0f, 0f);
        pieChart.getLegend().setWordWrapEnabled(true);
        pieChart.getDescription().setEnabled(false);
    }

    private void styleBarChart(BarChart barChart){
        barChart.setNoDataText("No Data Available");
        barChart.getDescription().setEnabled(false);
        barChart.animateY(500);
        barChart.getLegend().setEnabled(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.setScaleEnabled(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return months[(int)value];
            }
        });
    }

    private void setupColors(){
        int[] first = ColorTemplate.MATERIAL_COLORS;
        int[] second = ColorTemplate.VORDIPLOM_COLORS;
        colors = new int[first.length + second.length];

        int pos = 0;
        for (int element : first) {
            colors[pos] = element;
            pos++;
        }

        for (int element : second) {
            colors[pos] = element;
            pos++;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void reload(){

        pieDataSet_income = new PieDataSet(getPieEntries(RecordDatabase.getInstance(this.getContext()).recordsByCategoryDAO().getIncomeByMonth(calendar.getTime())), null);
        pieDataSet_expense = new PieDataSet(getPieEntries(RecordDatabase.getInstance(this.getContext()).recordsByCategoryDAO().getExpenseByMonth(calendar.getTime())), null);
        barDataSet = new BarDataSet(getBarEntries(RecordDatabase.getInstance(this.getContext()).recordsByMonthDAO().getTotalByMonth(calendar.getTime())), null);

        stylePieDataSet(pieDataSet_income);
        stylePieDataSet(pieDataSet_expense);
        styleBarChart(barChart);

        pieChart_income.setData(new PieData(pieDataSet_income));
        pieChart_income.notifyDataSetChanged();
        pieChart_expense.setData(new PieData(pieDataSet_expense));
        pieChart_expense.notifyDataSetChanged();
        barChart.setData(new BarData(barDataSet));
        barChart.notifyDataSetChanged();
    }
}

