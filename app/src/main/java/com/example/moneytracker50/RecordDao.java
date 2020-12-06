package com.example.moneytracker50;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.math.BigDecimal;

@Dao
public interface RecordDao {
    @Insert
    void addRecord(Record record);

    @Query ("SELECT SUM(amount) FROM records")
    BigDecimal getAmount();
}
