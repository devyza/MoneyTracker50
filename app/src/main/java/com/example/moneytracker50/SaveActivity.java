package com.example.moneytracker50;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class SaveActivity extends AppCompatActivity {

    EditText txtDescription, txtDate, txtAmount;
    Button btnCancel, btnSave;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtDescription = (EditText)findViewById(R.id.txtDescription);
        txtDate = (EditText)findViewById(R.id.txtDate);
        txtAmount = (EditText)findViewById(R.id.txtAmount);
        btnCancel = (Button)findViewById(R.id.btnCancle);
        btnSave = (Button)findViewById(R.id.btnSave);

        txtDate.setShowSoftInputOnFocus(false);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    final Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    datePickerDialog = new DatePickerDialog(SaveActivity.this,
                            new DatePickerDialog.OnDateSetListener(){
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    txtDate.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                                }
                            }, year, month, day);

                    datePickerDialog.show();
                }
            }
        });
    }
}