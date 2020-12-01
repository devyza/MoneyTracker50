package com.example.moneytracker50;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    private List<Record> recordList = new ArrayList<>();

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_record, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        Record current = recordList.get(position);
        holder.bind(current);
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public void reload(Context context, Date month){
        recordList = RecordDatabase.getInstance(context).recordDao().getRecordsByMonth(month);
        notifyDataSetChanged();
    }

    public class RecordViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout containerView;
        public TextView descriptionTextView, dateTextView, amountTextView, categoryTextView;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            containerView = itemView.findViewById(R.id.record_row);
            descriptionTextView = itemView.findViewById(R.id.txtDescription);
            dateTextView = itemView.findViewById(R.id.txtDate);
            amountTextView = itemView.findViewById(R.id.txtAmount);
            categoryTextView = itemView.findViewById(R.id.txtCategory);
        }

        public void bind(Record record){
            descriptionTextView.setText(record.getDescription());
            dateTextView.setText(Converters.dateFormat.format(record.getDate()));
            amountTextView.setText(Converters.moneyFormat.format(record.getAmount()));
            categoryTextView.setText(RecordDatabase
                    .getInstance(containerView.getContext())
                    .categoryDao().getCategory(record.getCategory_id()).getName()
            );
        }

    }
}
