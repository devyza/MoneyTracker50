package com.example.moneytracker50;

import androidx.room.ColumnInfo;

import java.math.BigDecimal;
import java.util.Date;

public class RecordsByMonth {
    @ColumnInfo(name = "yearMonth")
    Date yearMonth;

    @ColumnInfo(name = "money")
    BigDecimal money;

    public Date getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(Date yearMonth) {
        this.yearMonth = yearMonth;
    }

    public BigDecimal getMoney() { return money; }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getMonth(){
        return Converters.monthFormat.format(getYearMonth());
    }
}
