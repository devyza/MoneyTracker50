package com.example.moneytracker50;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void addCategory(Category category);

    @Query("SELECT COUNT(id) from category")
    int getTotal();

    @Query("SELECT * FROM category WHERE id=:id")
    Category getCategory(int id);

    @Query("SELECT * FROM category")
    List<Category> getAll();
}
