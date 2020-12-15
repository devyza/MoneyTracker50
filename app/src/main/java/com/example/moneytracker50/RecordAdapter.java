package com.example.moneytracker50;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    Context contextMain;
    private List<Record> recordList = new ArrayList<>();
    int green, red;

    public RecordAdapter(Context context){
        this.contextMain = context;
        green = context.getResources().getColor(R.color.green);
        red = context.getResources().getColor(R.color.red);
    }

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

    public void reload(Context context, Date month) {
        recordList = RecordDatabase.getInstance(context).recordsByDateDao().getRecordsByMonth(month);
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

            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Record record = (Record) containerView.getTag();
                    Intent intent = new Intent(v.getContext(), EditActivity.class);
                    intent.putExtra("id", record.getId());
                    ((Activity)contextMain).startActivityForResult(intent, 1002);
                }
            });
        }

        public void bind(Record record) {
            containerView.setTag(record);
            descriptionTextView.setText(record.getDescription());
            dateTextView.setText(Formatter.formatDate(record.getDate()));
            amountTextView.setText(Formatter.formatMoney(record.getAmount()));
            amountTextView.setTextColor(record.getAmount().compareTo(new BigDecimal("0")) == 1 ? green : red);
            categoryTextView.setText(RecordDatabase
                    .getInstance(containerView.getContext())
                    .categoryDao().getCategory(record.getCategory_id()).getName()
            );
        }

    }
}
