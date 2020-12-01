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

import java.text.SimpleDateFormat;
import java.util.Calendar;
public class HistoryFragment extends Fragment {

    private RecordDatabase recordDatabase;
    private ViewGroup view;
    private ImageButton btnNext, btnPrevious;
    private TextView txtMonth;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecordAdapter recordAdapter;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMM yyyy");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = (ViewGroup) inflater.inflate(R.layout.fragment_history, container, false);
        recordDatabase = RecordDatabase.getInstance(view.getContext());

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recordAdapter = new RecordAdapter();

        recyclerView = view.findViewById(R.id.rcylHistory);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recordAdapter);
        recordAdapter.reload(getContext(), calendar.getTime());

        txtMonth = view.findViewById(R.id.txtMonth);
        txtMonth.setText(monthFormat.format(calendar.getTime()));
        txtMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                txtMonth.setText(monthFormat.format(calendar.getTime()));
                recordAdapter.reload(getContext(), calendar.getTime());
            }
        });

        btnNext = view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                txtMonth.setText(monthFormat.format(calendar.getTime()));
                recordAdapter.reload(getContext(), calendar.getTime());
            }
        });

        btnPrevious = view.findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                txtMonth.setText(monthFormat.format(calendar.getTime()));
                recordAdapter.reload(getContext(), calendar.getTime());
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recordAdapter.reload(getContext(), calendar.getTime());
    }
}
