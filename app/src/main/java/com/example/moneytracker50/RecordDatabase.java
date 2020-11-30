package com.example.moneytracker50;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Record.class, Category.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class RecordDatabase extends RoomDatabase {

    static RecordDatabase instance;

    public abstract RecordDao recordDao();
    public abstract CategoryDao categoryDao();

    public static RecordDatabase getInstance(Context context){

        if(instance == null){
            instance = Room
                    .databaseBuilder(context, RecordDatabase.class, "recordDatabase")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}
