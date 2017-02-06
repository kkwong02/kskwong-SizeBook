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


/**
 * This activity allows users to edit and delete records. This exteneds the RecordForm abstract class
 * to avoid redundant methods that are shared between AddRecord and Edit Record.
 */
public class EditRecord extends RecordForm {
    private ArrayList<Person> records;
    private int item_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        Intent intent = getIntent();

        records = (ArrayList<Person>) intent.getSerializableExtra("records");
        item_index = (int) intent.getSerializableExtra("index");

        loadValues(records.get(item_index));

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
                        records.set(item_index, person);
                        saveRecords(records);
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
                saveRecords(records);
                finish();
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

    private void loadValues(Person record){
        String num_format = "%.1f";

        EditText name = (EditText) findViewById(R.id.name);
        EditText date = (EditText) findViewById(R.id.date);
        EditText neck = (EditText) findViewById(R.id.neck);
        EditText bust = (EditText) findViewById(R.id.bust);
        EditText chest = (EditText) findViewById(R.id.chest);
        EditText waist = (EditText) findViewById(R.id.waist);
        EditText hip = (EditText) findViewById(R.id.hip);
        EditText inseam = (EditText) findViewById(R.id.inseam);
        EditText comment = (EditText) findViewById(R.id.comments);

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

}
