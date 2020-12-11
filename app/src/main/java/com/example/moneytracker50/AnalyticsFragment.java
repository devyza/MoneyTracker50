package com.example.moneytracker50;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class AnalyticsFragment extends Fragment {

    View view, layout_month, layout_year;
    RecordBarChart barChart;
    RecordPieChart pieChart_income, pieChart_expense;
    ImageButton btnNxtMth, btnPvsMth, btnNxtYr, btnPvsYr;
    TextView txtMonth, txtYear;

    Calendar calendar = Calendar.getInstance();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = (View) inflater.inflate(R.layout.fragment_analytics, container, false);
        layout_month = view.findViewById(R.id.layout_month);
        layout_year = view.findViewById(R.id.layout_year);

        btnPvsMth = layout_month.findViewById(R.id.btnPrevious);
        btnPvsMth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                txtMonth.setText(Formatter.formatMonthYear(calendar.getTime()));
                reloadPieCharts();
            }
        });

        txtMonth = layout_month.findViewById(R.id.txtDate);
        txtMonth.setText(Formatter.formatMonthYear(calendar.getTime()));
        txtMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                txtMonth.setText(Formatter.formatMonthYear(calendar.getTime()));
                reloadPieCharts();
            }
        });

        btnNxtMth = layout_month.findViewById(R.id.btnNext);
        btnNxtMth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                txtMonth.setText(Formatter.formatMonthYear(calendar.getTime()));
                reloadPieCharts();
            }
        });

        btnPvsYr = layout_year.findViewById(R.id.btnPrevious);
        btnPvsYr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.YEAR, -1);
                txtYear.setText(Formatter.formatYear(calendar.getTime()));
                reloadBarChart();
            }
        });

        txtYear = layout_year.findViewById(R.id.txtDate);
        txtYear.setText(Formatter.formatYear(calendar.getTime()));
        txtYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                txtYear.setText(Formatter.formatYear(calendar.getTime()));
                reloadBarChart();
            }
        });

        btnNxtYr = layout_year.findViewById(R.id.btnNext);
        btnNxtYr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.YEAR, 1);
                txtYear.setText(Formatter.formatYear(calendar.getTime()));
                reloadBarChart();
            }
        });

        barChart = view.findViewById(R.id.barChart);
        barChart.invalidate();

        pieChart_income = view.findViewById(R.id.mp_PieChart_income);
        pieChart_income.invalidate();

        pieChart_expense = view.findViewById(R.id.mp_PieChart_expense);
        pieChart_expense.invalidate();

        reloadAll();

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void reloadAll(){
        reloadBarChart();
        reloadPieCharts();
    }

    public void reloadPieCharts(){
        pieChart_income.setRecordData(RecordDatabase.getInstance(this.getContext()).recordsByCategoryDao().getIncomeByMonth(calendar.getTime()));
        pieChart_income.invalidate();

        pieChart_expense.setRecordData(RecordDatabase.getInstance(this.getContext()).recordsByCategoryDao().getExpenseByMonth(calendar.getTime()));
        pieChart_expense.invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void reloadBarChart(){
        barChart.setRecordData(RecordDatabase.getInstance(this.getContext()).recordsByDateDao().getTotalByMonth(calendar.getTime()));
        barChart.invalidate();
    }

}

