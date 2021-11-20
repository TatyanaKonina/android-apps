package com.example.roomdb.db.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdb.db.entity.Professor;

import java.util.List;

@Dao
public interface ProfessorDAO {
    @Insert
    void insertProfessor(Professor professor);

    @Query("SELECT * FROM professor")
    List<Professor> findAllProfessor();

    @Query("SELECT * FROM professor where name LIKE :name")
    List<Professor> findProfessorByName(String name);

    @Query("DELETE FROM professor where name LIKE :name")
    void deleteProfessorByName(String name);

}

