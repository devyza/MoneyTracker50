package com.example.moneytracker50;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
public class HistoryFragment extends Fragment {

    private View view, btnsDate;
    private ImageButton btnNext, btnPrevious;
    private TextView txtMonth;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecordAdapter recordAdapter;

    Calendar calendar = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = (ViewGroup) inflater.inflate(R.layout.fragment_history, container, false);
        btnsDate = view.findViewById(R.id.dateButtons);

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recordAdapter = new RecordAdapter(getActivity());

        recyclerView = view.findViewById(R.id.rcylHistory);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recordAdapter);
        recordAdapter.reload(getContext(), calendar.getTime());

        txtMonth = btnsDate.findViewById(R.id.txtDate);
        txtMonth.setText(Formatter.formatMonthYear(calendar.getTime()));
        txtMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                txtMonth.setText(Formatter.formatMonthYear(calendar.getTime()));
                reload();
            }
        });

        btnNext = btnsDate.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                txtMonth.setText(Formatter.formatMonthYear(calendar.getTime()));
                reload();
            }
        });

        btnPrevious = btnsDate.findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                txtMonth.setText(Formatter.formatMonthYear(calendar.getTime()));
                reload();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void reload(){
        recordAdapter.reload(getContext(), calendar.getTime());
    }

}
