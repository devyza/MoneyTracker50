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

    @Query ("SELECT * FROM records " +
            "WHERE strftime('%m', date/1000, 'unixepoch') = strftime('%m', 'now')" +
            "ORDER BY date DESC, id DESC")
    List<Record> getAll();

    @Query ("SELECT SUM(amount) FROM records")
    BigDecimal getAmount();
}
