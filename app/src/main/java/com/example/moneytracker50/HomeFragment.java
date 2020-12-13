package com.example.moneytracker50;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.math.BigDecimal;

public class HomeFragment extends Fragment {

    ViewGroup view;
    TextView txtBalance;
    Button btnIncome, btnExpense;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        txtBalance = (TextView) view.findViewById(R.id.balance);
        reloadBalance();

        btnIncome = (Button) view.findViewById(R.id.btnIncome);
        btnIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SaveActivity.class);
                intent.putExtra("isIncome", true);
                startActivityForResult(intent, 1001);
            }
        });

        btnExpense = (Button) view.findViewById(R.id.btnExpense);
        btnExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SaveActivity.class);
                intent.putExtra("isIncome", false);
                startActivityForResult(intent, 1001);
            }
        });

        return view;
    }

    public void reloadBalance(){
        BigDecimal mainBalance = RecordDatabase.getInstance(view.getContext()).recordDao().getAmount();
        if (mainBalance != null){
            txtBalance.setText(Formatter.formatMoney(mainBalance));
            txtBalance.setTextColor(mainBalance.compareTo(new BigDecimal("0")) == 1 ? Color.GREEN : Color.RED);
        }


    }

}
