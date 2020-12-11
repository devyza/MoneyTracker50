package com.example.moneytracker50;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface RecordsByDateDao {
    @Query("SELECT * FROM records " +
            "WHERE strftime('%m %Y', date/1000, 'unixepoch') = strftime('%m %Y', :date/1000, 'unixepoch') " +
            "ORDER BY date DESC, id DESC")
    List<Record> getRecordsByMonth(Date date);

    @Query("SELECT date AS date, SUM(amount) AS money from records " +
            "WHERE strftime('%Y', date/1000, 'unixepoch') = strftime('%Y', :year/1000, 'unixepoch')" +
            "GROUP BY STRFTIME('%m %Y', date/1000, 'unixepoch')")
    List<RecordsByDate> getTotalByMonth(Date year);
}
