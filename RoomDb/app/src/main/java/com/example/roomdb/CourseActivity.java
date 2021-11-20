package com.example.roomdb;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roomdb.db.database.AppDb;
import com.example.roomdb.db.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {

    ListView listView;
    private EditText etIdProfessor, etName, etDuration, etIdLanguage;
    private Button btAdd, btShowCoursesByProfessor, btUpdateCourseInfo,btShowAllCourses,btDeleteByName;
    private Course course;
    private List<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        configView();
    }

    private void configView() {
        course = new Course();
        courses = new ArrayList<>();
        etIdProfessor = findViewById(R.id.courseActivityIdProfessor);
        etName = findViewById(R.id.courseActivityName);
        etIdLanguage = findViewById(R.id.courseActivityIdLanguage);
        listView = findViewById(R.id.listView);
        etDuration = findViewById(R.id.courseActivityIdDuration);

        btAdd = findViewById(R.id.courseActivityBtAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course.setDuration(etDuration.getText().toString());
                course.setName(etName.getText().toString());
                course.setProfessorId(Integer.parseInt(etIdProfessor.getText().toString()));
                course.setLanguageId(Integer.parseInt(etIdLanguage.getText().toString()));
                AppDb.getAppDb(getApplicationContext()).courseDAO().insert(course);

                courses = AppDb.getAppDb(getApplicationContext()).courseDAO().findAllCourses();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CourseActivity.this,
                        android.R.layout.simple_list_item_1, show(courses));
                listView.setAdapter(adapter);

            }
        });

        btShowAllCourses = findViewById(R.id.courseActivityBtShowAllCourses);
        btShowAllCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courses = AppDb.getAppDb(getApplicationContext()).courseDAO().findAllCourses();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CourseActivity.this,
                        android.R.layout.simple_list_item_1, show(courses));
                listView.setAdapter(adapter);
            }
        });



        btShowCoursesByProfessor = findViewById(R.id.courseActivityBtShowCoursesByProfessor);
        btShowCoursesByProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courses = AppDb.getAppDb(getApplicationContext()).courseDAO().findCoursesForProfessor(Integer.parseInt(etIdProfessor.getText().toString()));

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CourseActivity.this,
                        android.R.layout.simple_list_item_1, show(courses));
                listView.setAdapter(adapter);
            }
        });

        btUpdateCourseInfo = findViewById(R.id.courseActivityBtUpdateCourseInfo);
        btUpdateCourseInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDb.getAppDb(getApplicationContext()).courseDAO().updateCourseByID(Integer.parseInt(etIdLanguage.getText().toString()), Integer.parseInt(etIdProfessor.getText().toString()), etDuration.getText().toString(), etName.getText().toString());
                courses = AppDb.getAppDb(getApplicationContext()).courseDAO().findAllCourses();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CourseActivity.this,
                        android.R.layout.simple_list_item_1, show(courses));
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Информация обновлена для курса "+ etName.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });

        btDeleteByName = findViewById(R.id.courseActivityBtDeleteByName);
        btDeleteByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDb.getAppDb(getApplicationContext()).courseDAO().deleteCourseByName(etName.getText().toString());
                courses = AppDb.getAppDb(getApplicationContext()).courseDAO().findAllCourses();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CourseActivity.this,
                        android.R.layout.simple_list_item_1, show(courses));
                listView.setAdapter(adapter);
            }
        });
    }

    private <E> String[] show(List<E> array){
        return array.stream().map(E::toString).toArray(String[]::new);
    }


}

