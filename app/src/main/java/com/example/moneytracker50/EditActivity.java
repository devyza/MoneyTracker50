package com.example.moneytracker50;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    Record savedRecord;

    EditText edtDescription, edtDate, edtAmount;
    Button btnCancel, btnSave;
    DatePickerDialog datePickerDialog;

    Spinner spnrCategory;
    List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecordDatabase recordDatabase = RecordDatabase.getInstance(getApplicationContext());
        categories = recordDatabase.categoryDao().getAll();
        savedRecord = recordDatabase.recordDao().getRecordByID(getIntent().getIntExtra("id", 0));

        edtDescription = findViewById(R.id.edtDescription);
        edtDate = findViewById(R.id.edtDate);
        edtAmount = findViewById(R.id.edtAmount);
        btnCancel = findViewById(R.id.btnCancle);
        btnSave = findViewById(R.id.btnSave);
        spnrCategory = findViewById(R.id.spnr_category);

        ArrayAdapter<Category> adapterCategory = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        spnrCategory.setAdapter(adapterCategory);
        loadRecord(savedRecord);

        edtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    final Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    datePickerDialog = new DatePickerDialog(EditActivity.this,
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
                            savedRecord.getId(),
                            edtDescription.getText().toString(),
                            Formatter.parseDate(edtDate.getText().toString()),
                            new BigDecimal(edtAmount.getText().toString()),
                            ((Category)spnrCategory.getSelectedItem()).getId()
                    );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                recordDatabase.recordDao().updateRecord(record);
                setResult(RESULT_OK);
                Toast.makeText(EditActivity.this, "Record has ben Deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.edit_menu, menu);
        MenuItem deleteItem = menu.findItem(R.id.menu_delete);
        deleteItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                RecordDatabase.getInstance(getApplicationContext()).recordDao().deleteRecord(savedRecord);
                setResult(RESULT_OK);
                Toast.makeText(EditActivity.this, "Record has been Deleted", Toast.LENGTH_SHORT).show();
                finish();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void loadRecord(Record record){
        edtDescription.setText(savedRecord.getDescription());
        spnrCategory.setSelection(savedRecord.getCategory_id() - 1);
        edtDate.setText(Formatter.formatDate(savedRecord.getDate()));
        edtAmount.setText(savedRecord.getAmount().toString());
    }
}