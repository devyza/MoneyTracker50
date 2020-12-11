package com.example.moneytracker50;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface RecordsByCategoryDao {
    @Query("SELECT CASE WHEN C.name IS 'None' THEN 'Other' ELSE C.name END AS category, " +
            "SUM(R.amount) AS money " +
            "FROM records R " +
            "LEFT OUTER JOIN category C ON C.id = R.category_id " +
            "WHERE R.amount > 0 AND  strftime('%m %Y', date/1000, 'unixepoch') = strftime('%m %Y', :month/1000, 'unixepoch') " +
            "GROUP BY C.id")
    List<RecordsByCategory> getIncomeByMonth(Date month);

    @Query ("SELECT CASE WHEN C.name IS 'None' THEN 'Other' ELSE C.name END AS category, " +
            "SUM(R.amount) AS money " +
            "FROM records R " +
            "LEFT OUTER JOIN category C ON C.id = R.category_id " +
            "WHERE R.amount < 0 AND strftime('%m %Y', date/1000, 'unixepoch') = strftime('%m %Y', :month/1000, 'unixepoch') " +
            "GROUP BY C.id")
    List<RecordsByCategory> getExpenseByMonth(Date month);

}
