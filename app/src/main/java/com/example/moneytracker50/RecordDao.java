package com.example.moneytracker50;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.math.BigDecimal;
import java.util.List;

@Dao
public interface RecordDao {
    @Insert
    void addRecord(Record record);

    @Query ("SELECT * FROM records")
    List<Record> getAll();

    @Query ("SELECT SUM(amount) FROM records")
    BigDecimal getAmount();
}
