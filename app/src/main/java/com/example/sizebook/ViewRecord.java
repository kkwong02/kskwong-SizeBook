package com.example.sizebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * The details of a selected record are displayed in this activity. The user can edit or delete the
 * record here.
 */

public class ViewRecord extends AppCompatActivity {
    private ArrayList<Person> records;
    private int item_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        Intent intent = getIntent();

        records = (ArrayList<Person>) intent.getSerializableExtra("records");
        item_index = (int) intent.getSerializableExtra("index");

        loadValues(records.get(item_index));

        Button edit = (Button) findViewById(R.id.edit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editRecord();
                finish();
            }
        });
    }

    private void editRecord() {
        Intent intent = new Intent(this, EditRecord.class);
        intent.putExtra("index", item_index);
        intent.putExtra("records", records);
        startActivity(intent);
    }

    private void loadValues(Person record) {
        String num_format = "%.1f";

        TextView name = (TextView) findViewById(R.id.name_field);
        TextView date = (TextView) findViewById(R.id.date_field);
        TextView neck = (TextView) findViewById(R.id.neck_field);
        TextView bust = (TextView) findViewById(R.id.bust_field);
        TextView chest = (TextView) findViewById(R.id.chest_field);
        TextView waist = (TextView) findViewById(R.id.waist_field);
        TextView hip = (TextView) findViewById(R.id.hip_field);
        TextView inseam = (TextView) findViewById(R.id.inseam_field);
        TextView comment = (TextView) findViewById(R.id.comment_field);


        name.setText(record.getName());
        date.setText(record.getDate());

        if (record.getNeck() != null) {
            neck.setText("Neck: " + String.format(num_format, record.getNeck().getSize())
                    + " inches");
        }

        if (record.getBust() != null) {
            bust.setText("Bust: " + String.format(num_format, record.getBust().getSize())
                    + " inches");
        }

        if (record.getChest() != null) {
            chest.setText("Chest: " + String.format(num_format, record.getChest().getSize())
                    + " inches");
        }

        if (record.getWaist() != null) {
            waist.setText("Waist: " + String.format(num_format, record.getWaist().getSize())
                    + " inches");
        }

        if (record.getHip() != null) {
            hip.setText("Hip: " + String.format(num_format, record.getHip().getSize()) + " inches");
        }

        if (record.getInseam() != null) {
            inseam.setText("Inseam: " + String.format(num_format, record.getInseam().getSize())
                    + " inches");
        }

        if (record.getComment() != null) {
            comment.setText(String.format("Comment: %s", record.getComment()));
        }
    }

}
