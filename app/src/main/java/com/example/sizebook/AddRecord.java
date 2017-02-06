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
public class AddRecord extends AppCompatActivity {
    private ArrayList<Person> records;
    private static final String FILENAME = "sizeBook.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Intent intent = getIntent();
        records = (ArrayList<Person>) intent.getSerializableExtra("records");

        Button finish = (Button) findViewById(R.id.add);
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
                        saveRecords();
                        finish();
                    }
                }
            }
        });
    }

    private Person readInputFields(String name){
        Person record = new Person(name);

        // getting all the text from all the EditTexts
        EditText date = (EditText) findViewById(R.id.date);
        EditText neck = (EditText) findViewById(R.id.neck);
        EditText bust = (EditText) findViewById(R.id.bust);
        EditText chest = (EditText) findViewById(R.id.chest);
        EditText waist = (EditText) findViewById(R.id.waist);
        EditText hip = (EditText) findViewById(R.id.hip);
        EditText inseam = (EditText) findViewById(R.id.inseam);
        EditText comment = (EditText) findViewById(R.id.comments);

        // checking what is filled in

        // date
        String date_string = date.getText().toString();
        if(!date_string.equals("")){
            // check if format is valid.
            // taken from http://stackoverflow.com/questions/35971163/how-to-validate-edittext-input-with-a-custom-regex-in-android
            // 2017/02/04

            // regex expression from http://stackoverflow.com/questions/22061723/regex-date-validation-for-yyyy-mm-dd
            // 2017/02/04
            String regex = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
            if (date_string.matches(regex)){
                record.setDate(date_string);
            }
            else{
                date.setError("Invalid Format");
                return null;
            }
        }
        String regex = "[0-9]*\\.?[05]";
        // neck
        if (!neck.getText().toString().equals("")){
            Measurement neck_dim = new Measurement(Float.parseFloat(neck.getText().toString()));
            record.setNeck(neck_dim);
        }

        // chest
        if (!chest.getText().toString().equals("")){
            Measurement chest_dim = new Measurement(Float.parseFloat(chest.getText().toString()));
            record.setChest(chest_dim);
        }

        // bust
        if (!bust.getText().toString().equals("")){
            Measurement bust_dim = new Measurement(Float.parseFloat(bust.getText().toString()));
            record.setBust(bust_dim);
        }

        // waist
        if (!waist.getText().toString().equals("")){
            Measurement waist_dim = new Measurement(Float.parseFloat(waist.getText().toString()));
            record.setWaist(waist_dim);
        }

        // hip
        if (!hip.getText().toString().equals("")){
            Measurement hip_dim = new Measurement(Float.parseFloat(hip.getText().toString()));
            record.setHip(hip_dim);
        }

        // inseam
        if (!inseam.getText().toString().equals("")){
            Measurement inseam_dim = new Measurement(Float.parseFloat(inseam.getText().toString()));
            record.setInseam(inseam_dim);
        }

        // comments
        if (!comment.getText().toString().equals("")){
            record.setComment(comment.getText().toString());
        }

        return record;
    }

    // save new record to file
    private void saveRecords() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();

            gson.toJson(records, out);

            out.flush(); // flush the writer

            fos.close(); // close output steam
        }

        catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
        catch (IOException e){
            throw new  RuntimeException();
        }
    }
}
