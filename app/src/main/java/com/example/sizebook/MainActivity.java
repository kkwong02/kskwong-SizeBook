package com.example.sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newRecord = (Button) findViewById(R.id.add_record);

        newRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecord(v);

            }
        });
    }

    private void addRecord (View view){
        Intent createRecord = new Intent(this, AddRecord.class);
        startActivity(createRecord);
    }

}
