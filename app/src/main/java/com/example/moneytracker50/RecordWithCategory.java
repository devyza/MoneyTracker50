package com.example.moneytracker50;

import androidx.room.Embedded;
import androidx.room.Relation;

public class RecordWithCategory {
    @Embedded private Record record;
    @Relation(
            parentColumn = "id",
            entityColumn = "category_id"
    )
    private Category category;
}
