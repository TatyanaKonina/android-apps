package com.example.roomdb.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdb.db.entity.Languages;

import java.util.List;

@Dao
public interface LanguagesDAO {

    @Insert
    void insert(Languages languages);

    @Query("SELECT * FROM languages")
    List<Languages> findAllLanguages();



    @Query("DELETE FROM languages where name LIKE :name")
    void deleteLanguageByName(String name);
}
