package com.example.moneytracker50;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    private List<Record> recordList = new ArrayList<>();

    RecordAdapter(){
        this.recordList.add(new Record(0, "Go Shopping", new Date(), 100));
        this.recordList.add(new Record(1, "Go to Cinema", new Date(), 2000));
        this.recordList.add(new Record(2, "Dinner", new Date(), 50000));
        this.recordList.add(new Record(3, "Breakfast", new Date(), 600000));

        notifyDataSetChanged();
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


    DecimalFormat decimalFormat = new DecimalFormat("#,###");
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public class RecordViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout containerView;
        public TextView descriptionTextView, dateTextView, amountTextView;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            containerView = itemView.findViewById(R.id.record_row);
            descriptionTextView = itemView.findViewById(R.id.txtDescription);
            dateTextView = itemView.findViewById(R.id.txtDate);
            amountTextView = itemView.findViewById(R.id.txtAmount);
        }

        public void bind(Record record){
            descriptionTextView.setText(record.getDescription());
            dateTextView.setText(formatter.format(record.getDate()));
            amountTextView.setText(decimalFormat.format(record.getAmount()));
        }

    }
}
