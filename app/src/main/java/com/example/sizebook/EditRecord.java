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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class EditRecord extends AppCompatActivity {
    private ArrayList<Person> records;
    private int item_index;
    private static final String FILENAME = "sizeBook.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        Intent intent = getIntent();

        records = (ArrayList<Person>) intent.getSerializableExtra("records");
        item_index = (int) intent.getSerializableExtra("index");


        loadValues(records.get(item_index));

        Button finish = (Button) findViewById(R.id.save_edit);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name_field = (EditText) findViewById(R.id.edit_name);
                if(name_field.getText().toString().equals("")){
                    name_field.setError("Name field is required.");
                }
                else{
                    Person person = readInputFields(name_field.getText().toString());
                    if (person != null) {
                        records.set(item_index, person);
                        saveRecords();
                        finish();
                    }
                }
            }
        });

        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                records.remove(item_index);
                saveRecords();
                finish();
            }
        });

    }

    private void loadValues(Person record){
        String num_format = "%.1f";

        EditText name = (EditText) findViewById(R.id.edit_name);
        EditText date = (EditText) findViewById(R.id.edit_date);
        EditText neck = (EditText) findViewById(R.id.edit_neck);
        EditText bust = (EditText) findViewById(R.id.edit_bust);
        EditText chest = (EditText) findViewById(R.id.edit_chest);
        EditText waist = (EditText) findViewById(R.id.edit_waist);
        EditText hip = (EditText) findViewById(R.id.edit_hip);
        EditText inseam = (EditText) findViewById(R.id.edit_inseam);
        EditText comment = (EditText) findViewById(R.id.edit_comments);

        name.setText(record.getName());
        date.setText(record.getDate());

        if (record.getNeck() != null) {
            neck.setText(String.format(num_format, record.getNeck().getSize()));
        }

        if (record.getBust() != null) {
            bust.setText(String.format(num_format, record.getBust().getSize()));
        }

        if (record.getChest() != null) {
            chest.setText(String.format(num_format, record.getChest().getSize()));
        }

        if (record.getWaist() != null) {
            waist.setText(String.format(num_format, record.getWaist().getSize()));
        }

        if (record.getHip() != null) {
            hip.setText(String.format(num_format, record.getHip().getSize()));
        }

        if (record.getInseam() != null) {
            inseam.setText(String.format(num_format, record.getInseam().getSize()));
        }

        if(comment != null){
            comment.setText(record.getComment());
        }
    }

    public Person readInputFields(String name){
        Person record = new Person(name);

        // getting all the text from all the EditTexts
        EditText date = (EditText) findViewById(R.id.edit_date);
        EditText neck = (EditText) findViewById(R.id.edit_neck);
        EditText bust = (EditText) findViewById(R.id.edit_bust);
        EditText chest = (EditText) findViewById(R.id.edit_chest);
        EditText waist = (EditText) findViewById(R.id.edit_waist);
        EditText hip = (EditText) findViewById(R.id.edit_hip);
        EditText inseam = (EditText) findViewById(R.id.edit_inseam);
        EditText comment = (EditText) findViewById(R.id.edit_comments);

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
                date.setError("Invaid Format");
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
            Measurement chest_dim = new Measurement(Float.parseFloat(neck.getText().toString()));
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
    public void saveRecords() {
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
