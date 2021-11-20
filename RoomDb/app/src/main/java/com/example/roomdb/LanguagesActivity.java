package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.roomdb.db.database.AppDb;
import com.example.roomdb.db.entity.Course;
import com.example.roomdb.db.entity.Languages;

import java.util.ArrayList;
import java.util.List;

public class LanguagesActivity extends AppCompatActivity {

    private EditText etName;
    private Button btAdd, btShowCourses, btDelete,btShowAllLanguages;

    private Languages languages;
    private List<Languages> listLanguages;
    private  List<Course> listCourses;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages2);
        configView();
    }

    private void configView(){
        languages = new Languages();
        listLanguages = new ArrayList<>();
        listCourses = new ArrayList<>();
        listView = findViewById(R.id.listView);

        etName = findViewById(R.id.languageActivityName);
        btAdd = findViewById(R.id.languageActivityBtAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languages.setName(etName.getText().toString());
                AppDb.getAppDb(getApplicationContext()).languagesDAO().insert(languages);
                listLanguages = AppDb.getAppDb(getApplicationContext()).languagesDAO().findAllLanguages();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(LanguagesActivity.this,
                        android.R.layout.simple_list_item_1, show(listLanguages));
                listView.setAdapter(adapter);
            }
        });

        btShowAllLanguages = findViewById(R.id.languageActivityBtShowAllLanguages);
        btShowAllLanguages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    listLanguages = AppDb.getAppDb(getApplicationContext()).languagesDAO().findAllLanguages();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(LanguagesActivity.this,
                            android.R.layout.simple_list_item_1, show(listLanguages));
                    listView.setAdapter(adapter);
                }
        });

        btShowCourses = findViewById(R.id.languageActivityBtShowCourses);
        btShowCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listCourses = AppDb.getAppDb(getApplicationContext()).courseDAO().findCoursesForLanguage(etName.getText().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(LanguagesActivity.this,
                        android.R.layout.simple_list_item_1, show(listCourses));
                listView.setAdapter(adapter);
            }
        });



        btDelete = findViewById(R.id.languageActivityBtDeleteCourseByName);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDb.getAppDb(getApplicationContext()).languagesDAO().deleteLanguageByName(etName.getText().toString());
                listLanguages = AppDb.getAppDb(getApplicationContext()).languagesDAO().findAllLanguages();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(LanguagesActivity.this,
                        android.R.layout.simple_list_item_1, show(listLanguages));
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();
            }
        });
    }

    private <E> String[] show(List<E> array){
        return array.stream().map(E::toString).toArray(String[]::new);
    }
}