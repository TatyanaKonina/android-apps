package com.example.roomdb.db.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


import com.example.roomdb.Constans.Constans;

@Entity(tableName = Constans.NAME_TABLE_COURSE,
        foreignKeys = {
                @ForeignKey(entity = Professor.class,
                    parentColumns = "id",
                    childColumns = "professorId",
                        onDelete = CASCADE
                ),
                @ForeignKey(entity = Languages.class,
                        parentColumns = "id",
                        childColumns = "languageId",
                        onDelete = CASCADE)
        })
public class Course {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "duration")
    public String duration;
    @ColumnInfo(name = "professorId")
    public int professorId;
    @ColumnInfo(name = "languageId")
    public int languageId;

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Override
    public String toString() {
        return
                "ID: " + id +
                " NAME:" + name +
                " DURATION " + duration +
                " PROFESSOR_ID " + professorId;
    }
}
