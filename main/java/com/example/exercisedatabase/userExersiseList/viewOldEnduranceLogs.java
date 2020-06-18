package com.example.exercisedatabase.userExersiseList;

import android.content.Intent;
//import android.graphics.Color;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercisedatabase.R;

//import java.io.BufferedReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class viewOldEnduranceLogs extends AppCompatActivity {
    private TableLayout tl;
    private TableRow tr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_old_endurance_logs);
        tl = (TableLayout)findViewById(R.id.enduranceLogDisplayTable);
        setTitle(getIntent().getStringExtra("ExName") + " Previous Logs");
        setData();
        button();
    }

    private void button() {
        Button button = (Button)findViewById(R.id.viewGraphedEndurance);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewGraphed();
            }
        });
    }

    private void viewGraphed() {
        Intent intent = new Intent(this, viewGraphedLogsEnduranceClass.class);
        intent.putExtra("ID", getIntent().getStringExtra("ID"));
        intent.putExtra("Exersise", getIntent().getStringExtra("Exersise"));
        intent.putExtra("ExName", getIntent().getStringExtra("ExName"));
        startActivity(intent);
    }

    /**
     * Function to create the log display table to show all the information.
     */
    private void setData() {
        exersiseLogDatabaseHandler db = new exersiseLogDatabaseHandler(getApplicationContext());
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);
        List<enduranceLog> logs = db.getEnduranceExercise(Integer.parseInt(getIntent().getStringExtra("ID")), getIntent().getStringExtra("Exersise"), getIntent().getStringExtra("ExName"));
        Collections.reverse(logs);
        for (enduranceLog e: logs) {
            String measurement = "";
            if (e.getEndurance_type().equals("Metric (m)")) {
                measurement = "m";
            } else if (e.getEndurance_type().equals("Metric (km)")) {
                measurement = "km";
            } else if (e.getEndurance_type().equals("Imperial (ft)")) {
                measurement = "ft";
            } else if (e.getEndurance_type().equals("Imperial (miles)")) {
                measurement = "miles";
            }
            tr = new TableRow(this);

            TextView date = new TextView(this);
            date.setText(e.getDate());
            date.setGravity(Gravity.CENTER);
            tr.addView(date);

            TextView time = new TextView(this);
            String temp = df.format(e.getTimeMinutes()) + " min. " + df.format(e.getTimeSeconds()) + " sec.";
            time.setText(temp);
            time.setGravity(Gravity.CENTER);
            tr.addView(time);

            TextView distance = new TextView(this);
            if (e.getDistance() == 0f) {
                temp = " ";
            } else {
                temp = e.getDistance() + " " + measurement;
            }
            distance.setText(temp);
            distance.setGravity(Gravity.CENTER);
            tr.addView(distance);

            TextView intensity = new TextView(this);
            intensity.setText(e.getIntensity());
            intensity.setGravity(Gravity.CENTER);
            tr.addView(intensity);
            tr.setPadding(0, 5,0,5);

            tl.addView(tr);
        }
    }
}
