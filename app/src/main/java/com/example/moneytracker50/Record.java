package com.example.moneytracker50;

import java.util.Date;

public class Record {
    private int id;
    private String description;
    private Date date;
    private int amount;

    public Record(int id, String description, Date date, int amount) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.amount = amount;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
