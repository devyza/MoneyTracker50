package com.example.moneytracker50;

import androidx.room.TypeConverter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value){
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date){
        return date == null ? null : date.getTime();
    }


    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static DecimalFormat moneyFormat = new DecimalFormat("#,###.00");

}