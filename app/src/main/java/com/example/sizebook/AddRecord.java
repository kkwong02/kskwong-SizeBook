package com.example.sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * In this activity, the user can fill the form to add a new record.
 */
public class AddRecord extends RecordForm {
    private ArrayList<Person> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_form);

        Intent intent = getIntent();
        records = (ArrayList<Person>) intent.getSerializableExtra("records");

        Button finish = (Button) findViewById(R.id.save);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name_field = (EditText) findViewById(R.id.name);
                if(name_field.getText().toString().equals("")){
                    name_field.setError("Name field is required.");
                }
                else{
                    Person person = readInputFields(name_field.getText().toString());
                    if (person != null) {
                        records.add(person);
                        saveRecords(records);
                        finish();
                    }
                }
            }
        });
    }

    @Override
    public Person readInputFields(String name){
        Person person = super.readInputFields(name);
        return person;
    }

    @Override
    public void saveRecords(ArrayList<Person> records){
        super.saveRecords(records);
    }

}
