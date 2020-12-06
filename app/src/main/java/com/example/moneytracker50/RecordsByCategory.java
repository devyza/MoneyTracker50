package com.example.moneytracker50;

import androidx.room.ColumnInfo;

import java.math.BigDecimal;

public class RecordsByCategory {
    @ColumnInfo(name = "category")
    String category;

    @ColumnInfo(name = "money")
    BigDecimal money;

    public RecordsByCategory(String category, BigDecimal money) {
        this.category = category;
        this.money = money;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
