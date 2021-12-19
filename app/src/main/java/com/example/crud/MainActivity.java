package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public EditText editID;
    Button buttonAdd, buttonViewAll,buttonUpdate,buttonDelete;
    EditText editName, editAge;
    Switch switchIsActive;
    ListView listViewStudent;
    public Integer retid() {
        editID=(EditText)findViewById(R.id.editTextid);
        return Integer.parseInt(editID.getText().toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonViewAll = findViewById(R.id.buttonViewAll);
        editName = findViewById(R.id.editTextName);
        editAge = findViewById(R.id.editTextAge);

        switchIsActive = findViewById(R.id.switchStudent);
        listViewStudent = findViewById(R.id.listViewStudent);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            studentmodel studentModel;

            @Override
            public void onClick(View v) {
                try {
                    studentModel = new studentmodel(editName.getText().toString(), Integer.parseInt(editAge.getText().toString()), switchIsActive.isChecked(),0);
                    Toast.makeText(MainActivity.this, studentModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                Dbhelper dbHelper = new Dbhelper(MainActivity.this);
                dbHelper.addStudent(studentModel);
            }
        });
        editID=(EditText)findViewById(R.id.editTextid);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            studentmodel studentModel;

            public void onClick(View v) {
                try {
                    studentModel = new studentmodel(editName.getText().toString(), Integer.parseInt(editAge.getText().toString()), switchIsActive.isChecked(),retid());
                    Toast.makeText(MainActivity.this, studentModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                Dbhelper dbHelper = new Dbhelper(MainActivity.this);
                dbHelper.updateStudent(studentModel);
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dbhelper dbHelper = new Dbhelper(MainActivity.this);
                dbHelper.deleteTitle(retid());
            }
        });
        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dbhelper dbHelper = new Dbhelper(MainActivity.this);
                List<studentmodel> list = dbHelper.getAllStudents();
                ArrayAdapter arrayAdapter = new ArrayAdapter<studentmodel>(MainActivity.this, android.R.layout.simple_list_item_1,list);
                listViewStudent.setAdapter(arrayAdapter);
                
            }
        });

    }
}