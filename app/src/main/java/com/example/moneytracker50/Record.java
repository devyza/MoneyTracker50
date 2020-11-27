package com.example.moneytracker50;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "records")
public class Record {

    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "amount")
    private int amount;

    @ColumnInfo(name = "category_id")
    private int category_id;

    public Record(int id, String description, Date date, int amount, int category_id){
        this.id = id;
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.category_id = category_id;
    }

    @Ignore
    public Record(String description, Date date, int amount, Category category){
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.category_id = category.getId();
    }

    @Ignore
    public Record(String description, Date date, int amount, int category_id) {
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public int getCategory_id(){return category_id;}

    public void setId(int id){ this.id = id;}

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCategory_id(int category_id){this.category_id = category_id;}
}
