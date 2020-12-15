package com.example.moneytracker50;

import android.content.Context;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class RecordPieChart extends PieChart {

    public RecordPieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        setRotationEnabled(false);
        setExtraOffsets(0f, 5f, 0f, 5f);
        getLegend().setWordWrapEnabled(true);
        getDescription().setEnabled(false);
    }

    public void setRecordData(List<RecordsByCategory> pieList){
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < pieList.size(); i++) {
            pieEntries.add(
                    new PieEntry(
                            pieList.get(i).getMoney().abs().floatValue(),
                            pieList.get(i).getCategory()
                    ));
        }
        RecordPieDataSet pieDataSet = new RecordPieDataSet(pieEntries, null);
        setData(new PieData(pieDataSet));
        animateY(1200);
        notifyDataSetChanged();
    }

    static int[] colors;

    public class RecordPieDataSet extends PieDataSet{
        public RecordPieDataSet(List<PieEntry> yVals, String label) {
            super(yVals, label);
            setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
            setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
            setValueLinePart1Length(0.5f);
            setSliceSpace(5f);
            if(colors == null) setupColors();
            setColors(colors);
        }
    }

    private static void setupColors(){
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


}
