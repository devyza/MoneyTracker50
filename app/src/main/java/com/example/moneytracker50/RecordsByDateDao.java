package com.example.moneytracker50;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface RecordsByDateDao {
    @Query("SELECT * FROM records " +
            "WHERE strftime('%m %Y', date/1000, 'unixepoch', 'localtime') = strftime('%m %Y', :date/1000, 'unixepoch', 'localtime') " +
            "ORDER BY date DESC, id DESC")
    List<Record> getRecordsByMonth(Date date);

    @Query("SELECT date AS date, SUM(amount) AS money from records " +
            "WHERE strftime('%Y', date/1000, 'unixepoch', 'localtime') = strftime('%Y', :year/1000, 'unixepoch', 'localtime')" +
            "GROUP BY STRFTIME('%m %Y', date/1000, 'unixepoch', 'localtime')")
    List<RecordsByDate> getTotalByMonth(Date year);
}
