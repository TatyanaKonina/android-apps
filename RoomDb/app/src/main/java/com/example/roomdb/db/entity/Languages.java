package com.example.roomdb.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.roomdb.Constans.Constans;

@Entity(tableName = Constans.NAME_TABLE_LANGUAGES)
public class Languages {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return
                " ID: " + id +
                " NAME: " + name ;
    }
}
