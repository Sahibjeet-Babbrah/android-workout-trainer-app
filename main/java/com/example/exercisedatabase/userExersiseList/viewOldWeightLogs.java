package com.example.exercisedatabase.userExersiseList;

import android.content.Intent;
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

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class viewOldWeightLogs extends AppCompatActivity {

    private TableLayout tl;
    private TableRow tr;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_old_weight_logs);
        setTitle(getIntent().getStringExtra("ExName") + "-" + " Previous Logs");
        tl = (TableLayout)findViewById(R.id.weightLogDisplayTable);
        getData();
        setButton();
    }

    private void setButton() {
        button = (Button)findViewById(R.id.pleaseWork1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewGraph();
            }
        });
    }

    private void viewGraph() {
        Intent intent = new Intent(this, viewOldWeightLogsGraphedInfo.class);
        intent.putExtra("ID", getIntent().getStringExtra("ID"));
        intent.putExtra("Exersise", getIntent().getStringExtra("Exersise"));
        intent.putExtra("ExName", getIntent().getStringExtra("ExName"));
        startActivity(intent);
    }

    /**
     * Function to create the table to display the information.
     */
    private void getData() {
        exersiseLogDatabaseHandler db = new exersiseLogDatabaseHandler(this);
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);
        List<weightLog> logs = db.getWeightExercise(Integer.parseInt(getIntent().getStringExtra("ID")), getIntent().getStringExtra("Exersise"), getIntent().getStringExtra("ExName"));
        Collections.reverse(logs);

        for (weightLog e: logs) {
            String measurement = "";
            if (e.getWeightType().equals("Metric")) {
                measurement = "kg";
            } else {
                measurement = "lb";
            }
            tr = new TableRow(this);

            TextView date = new TextView(this);
            date.setText(e.getDate());
            date.setGravity(Gravity.CENTER);
            tr.addView(date);

            TextView weight = new TextView(this);
            weight.setText(df.format(e.getWeight()) + " " + measurement);
            weight.setGravity(Gravity.CENTER);
            tr.addView(weight);

            TextView sets = new TextView(this);
            sets.setText(String.valueOf(e.getSets()));
            sets.setGravity(Gravity.CENTER);
            tr.addView(sets);

            TextView reps = new TextView(this);
            reps.setText(String.valueOf(e.getReps()));
            reps.setGravity(Gravity.CENTER);
            tr.addView(reps);

            String temp;
            if (e.getRests() == 0f) {
                temp = " ";
            } else {
                temp = String.valueOf(e.getRests()) + " sec.";
            }
            TextView rest = new TextView(this);
            rest.setText(temp);
            rest.setGravity(Gravity.CENTER);
            tr.addView(rest);
            tr.setPadding(0, 5, 0,5);

            tl.addView(tr);
        }
    }

    /*
    private void getInfomation() {
        exersiseLogDatabaseHandler db = new exersiseLogDatabaseHandler(getApplicationContext());
        ListView lv = (ListView)findViewById(R.id.viewOldWeightLogsView);
        List<weightLog> logs = db.getWeightExercise(Integer.parseInt(getIntent().getStringExtra("ID")), getIntent().getStringExtra("Exersise"), getIntent().getStringExtra("ExName"));
        ArrayList<weightLog> temp = new ArrayList<weightLog>();

        for (weightLog w: logs) {
            temp.add(w);
        }

        weightLogAdapter adapter = new weightLogAdapter(this, temp);
        lv.setAdapter(adapter);
    }*/


}
