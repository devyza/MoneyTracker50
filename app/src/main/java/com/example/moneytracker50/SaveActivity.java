package com.example.moneytracker50;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;

public class SaveActivity extends AppCompatActivity {

    RecordDatabase database;
    EditText edtDescription, edtDate, edtAmount;
    Button btnCancel, btnSave;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = RecordDatabase.getInstance(SaveActivity.this);

        edtDescription = (EditText)findViewById(R.id.edtDescription);
        edtDate = (EditText)findViewById(R.id.edtDate);
        edtAmount = (EditText)findViewById(R.id.edtAmount);
        btnCancel = (Button)findViewById(R.id.btnCancle);
        btnSave = (Button)findViewById(R.id.btnSave);

        edtDate.setShowSoftInputOnFocus(false);
        edtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
                                    edtDate.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                                }
                            }, year, month, day);

                    datePickerDialog.show();
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Record record = null;
                try {
                        record = new Record(
                                edtDescription.getText().toString(),
                                Converters.dateFormat.parse(edtDate.getText().toString()),
                                Integer.parseInt(edtAmount.getText().toString())
                    );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                database.recordDao().addRecord(record);
                Toast.makeText(SaveActivity.this, "Saving Completed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}