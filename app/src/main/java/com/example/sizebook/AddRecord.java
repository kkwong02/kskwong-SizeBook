package com.example.sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private static final String FILENAME = "sizeBook.sav";
    private ArrayList<Person> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Intent intent = getIntent();
//        records = (ArrayList<Person>) intent.getSerializableExtra("records");
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
