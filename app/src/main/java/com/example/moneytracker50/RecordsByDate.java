package com.example.moneytracker50;

import androidx.room.ColumnInfo;

import java.math.BigDecimal;
import java.util.Date;

public class RecordsByDate {
    @ColumnInfo(name = "date")
    Date date;

    @ColumnInfo(name = "money")
    BigDecimal money;

    public Date getYearMonth() {
        return date;
    }

    public void setYearMonth(Date date) {
        this.date = date;
    }

    public BigDecimal getMoney() { return money; }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
