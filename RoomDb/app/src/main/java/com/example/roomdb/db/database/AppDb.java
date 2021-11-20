package com.example.roomdb.db.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomdb.Constans.Constans;
import com.example.roomdb.db.dao.CourseDAO;
import com.example.roomdb.db.dao.LanguagesDAO;
import com.example.roomdb.db.dao.ProfessorDAO;
import com.example.roomdb.db.entity.Course;
import com.example.roomdb.db.entity.Languages;
import com.example.roomdb.db.entity.Professor;

@Database(entities = {Professor.class, Course.class, Languages.class}, version = 3)
public abstract class AppDb extends RoomDatabase {

    private static AppDb INSTANCE;

    public abstract ProfessorDAO professorDAO();

    public abstract CourseDAO courseDAO();

    public abstract LanguagesDAO languagesDAO();


    public static AppDb getAppDb(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDb.class, Constans.NAME_DATABASE)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
