package com.example.moneytracker50;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.math.BigDecimal;

@Dao
public interface RecordDao {
    @Insert
    void addRecord(Record record);

    @Update
    void updateRecord(Record record);

    @Delete
    void deleteRecord(Record record);

    @Query("SELECT SUM(amount) FROM records")
    BigDecimal getAmount();

    @Query("SELECT * FROM records WHERE id=:id")
    Record getRecordByID(int id);
}
