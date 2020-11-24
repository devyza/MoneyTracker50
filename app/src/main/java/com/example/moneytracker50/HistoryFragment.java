package com.example.moneytracker50;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryFragment extends Fragment {

    private RecordDatabase recordDatabase;
    private ViewGroup view;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecordAdapter recordAdapter;

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
        recordAdapter.reload(getContext());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recordAdapter.reload(getContext());
    }
}
