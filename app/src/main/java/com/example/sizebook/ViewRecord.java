package com.example.sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * The details of a selected record are displayed in this activity. The user can edit or delete the
 * record here.
 */

public class ViewRecord extends AppCompatActivity {
    private Person record;
    private ArrayList<Person> records;
    private int item_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        ArrayList<Person> records;
        int item_index;

        Intent intent = getIntent();

        records = (ArrayList<Person>) intent.getSerializableExtra("records");
        item_index = (int) intent.getSerializableExtra("index");

        Button delete = (Button) findViewById(R.id.delete);
        Button edit = (Button) findViewById(R.id.edit);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something on click
            }
        });

    }

    private void loadValues(Person record){
        String num_format = "%.1f";

        TextView name = (TextView) findViewById(R.id.name);
        TextView date = (TextView) findViewById(R.id.date);
        TextView neck = (TextView) findViewById(R.id.neck);
        TextView bust = (TextView) findViewById(R.id.bust);
        TextView chest = (TextView) findViewById(R.id.chest);
        TextView waist = (TextView) findViewById(R.id.waist);
        TextView hip = (TextView) findViewById(R.id.hip);
        TextView inseam = (TextView) findViewById(R.id.inseam);
        TextView comment = (TextView) findViewById(R.id.comments);

        name.setText(record.getName());
        date.setText(record.getDate());

        if (!record.getNeck().equals(null)) {
            neck.setText(String.format(num_format, record.getNeck().getSize()));
        }

        if (!record.getBust().equals(null)) {
            bust.setText(String.format(num_format, record.getBust().getSize()));
        }

        if (!record.getChest().equals(null)) {
            chest.setText(String.format(num_format, record.getChest().getSize()));
        }

        if (!record.getWaist().equals(null)) {
            waist.setText(String.format(num_format, record.getWaist().getSize()));
        }

        if (!record.getHip().equals(null)) {
            hip.setText(String.format(num_format, record.getHip().getSize()));
        }

        if (!record.getInseam().equals(null)) {
            inseam.setText(String.format(num_format, record.getInseam().getSize()));
        }

        if(!comment.equals(null)){
            comment.setText(record.getComment());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadValues(records.get(item_index));
    }
}
