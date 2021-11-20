package com.example.roomdb.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdb.db.entity.Course;

import java.util.List;

@Dao
public interface CourseDAO {

    @Insert
    void insert(Course course);

    @Query("SELECT * FROM course")
    List<Course> findAllCourses();

    @Query("SELECT * FROM course WHERE professorId=:professorId")
    List<Course> findCoursesForProfessor(int professorId);

    @Query("SELECT course.id, course.name,course.duration,course.professorId,course.languageId  FROM course,languages WHERE course.languageId = languages.id AND languages.name=:name")
    List<Course> findCoursesForLanguage(String name);

    @Query("UPDATE course SET languageId = :languageId, professorId= :professorId,duration = :durationId WHERE name =:name")
    void updateCourseByID(int languageId,int professorId,String durationId,String name);

    @Query("DELETE FROM course WHERE name=:name")
    void deleteCourseByName(String name);

}
