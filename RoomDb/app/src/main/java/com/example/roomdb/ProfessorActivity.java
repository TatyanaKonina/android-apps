package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.roomdb.db.database.AppDb;
import com.example.roomdb.db.entity.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorActivity extends AppCompatActivity {

    private EditText etName, etEmail;
    private Button btAdd, btListProfessors,btFindByName,btDeleteByName;
    ListView listView;

    private Professor professor;
    private List<Professor> listProfessors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        configView();
    }

    private void configView(){
        professor = new Professor();
        listProfessors = new ArrayList<>();
        etName = findViewById(R.id.professorActivityInputName);
        listView = findViewById(R.id.listView);
        etEmail = findViewById(R.id.professorActivityInputEmail);
        btAdd = findViewById(R.id.professorActivityBtAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professor.setName(etName.getText().toString());
                professor.setEmail(etEmail.getText().toString());

                AppDb.getAppDb(getApplicationContext()).professorDAO().insertProfessor(professor);
                listProfessors = AppDb.getAppDb(getApplicationContext()).professorDAO().findAllProfessor();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProfessorActivity.this,
                        android.R.layout.simple_list_item_1, show(listProfessors));
                listView.setAdapter(adapter);
            }
        });




        btListProfessors = findViewById(R.id.professorActivityBtListProfessors);
        btListProfessors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listProfessors = AppDb.getAppDb(getApplicationContext()).professorDAO().findAllProfessor();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProfessorActivity.this,
                        android.R.layout.simple_list_item_1, show(listProfessors));
                listView.setAdapter(adapter);
            }
        });



        btFindByName = findViewById(R.id.professorActivityBtFindByName);
        btFindByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listProfessors = AppDb.getAppDb(getApplicationContext()).professorDAO().findProfessorByName(etName.getText().toString());

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProfessorActivity.this,
                        android.R.layout.simple_list_item_1, show(listProfessors));
                listView.setAdapter(adapter);
//
//                //                вывод
                if( listProfessors.size() == 0){
                    Log.d("TAG", "Professor list is empty");
                }
            }
        });



        btDeleteByName = findViewById(R.id.professorActivityBtDelete);
        btDeleteByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listProfessors = AppDb.getAppDb(getApplicationContext()).professorDAO().findProfessorByName(etName.getText().toString());
                if (listProfessors.size() == 0){
                    Log.d("FIND", "Can't find professor with that name");
                }
                else {
                    AppDb.getAppDb(getApplicationContext()).professorDAO().deleteProfessorByName(etName.getText().toString());
                    listProfessors = AppDb.getAppDb(getApplicationContext()).professorDAO().findAllProfessor();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProfessorActivity.this,
                            android.R.layout.simple_list_item_1, show(listProfessors));
                    listView.setAdapter(adapter);
                }



            }
        });
    }

    private <E> String[] show(List<E> array){
        return array.stream().map(E::toString).toArray(String[]::new);
    }

}