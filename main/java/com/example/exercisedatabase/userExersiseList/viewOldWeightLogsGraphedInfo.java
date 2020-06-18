package com.example.exercisedatabase.userExersiseList;

import android.graphics.Color;
//import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercisedatabase.R;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class viewOldWeightLogsGraphedInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_old_weight_logs_graphed_info);
        createGraph();
    }

    /**
     * Function to create the graph for everything.
     */
    private void createGraph() {
        List<BarEntry> entries = new ArrayList<BarEntry>();
        ArrayList<Entry> enter3 = new ArrayList<Entry>();
        exersiseLogDatabaseHandler db = new exersiseLogDatabaseHandler(this);
        List<weightLog> logs = db.getWeightExercise(Integer.parseInt(getIntent().getStringExtra("ID")), getIntent().getStringExtra("Exersise"), getIntent().getStringExtra("ExName"));
        String[] dates = new String[logs.size()];

        // Set all the weight data
        for (int i = 0; i < logs.size(); i++) {
            if (logs.get(i).getWeightType().equals("Imperial")) {
                float hgWeight = logs.get(i).getWeight() / 2.2046f;
                entries.add(new BarEntry(i, hgWeight));
            } else {
                entries.add(new BarEntry(i, logs.get(i).getWeight()));
            }
            dates[i] = logs.get(i).getDate();
        }

        // Calculate the equation for the trend line
        float a = 1f;
        float b1 = 0f;
        float b2 = 0f;
        float c = 1f;
        float d = 0f;
        float e = 0f;
        float f = 1f;
        for (int i = 0; i < logs.size(); i++) {
            float hgWeight;
            if (logs.get(i).getWeightType().equals("Imperial")) {
                hgWeight = logs.get(i).getWeight() / 2.2046f;
            } else {
                hgWeight = logs.get(i).getWeight();
            }
            a += (i * hgWeight);
            b1 += i;
            b2 += hgWeight;
            c += i * i;
            d += i;
            e += hgWeight;
            f += i;
        }

        float A = logs.size() * a;
        float B = b1 * b2;
        float C = logs.size() * c;
        float D = d * d;
        float slope = (A - B) / (C - D);
        float F = slope * f;
        float yInter = (e - F) / logs.size();

        for (int i = 0; i < logs.size(); i++) {
            enter3.add(new BarEntry(i, (slope * (i + 1)) + yInter));
        }

        // Create the dataset for the bar graph
        BarDataSet dataSet = new BarDataSet(entries, "Weight (Kilograms)"); // Add entries to dataset
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.RED);
        dataSet.setValueTextSize(12f);
        // Create the dataset for the line graph
        LineDataSet dataSet2 = new LineDataSet(enter3, "Trendline");
        dataSet2.setColor(Color.RED);
        dataSet2.setDrawValues(false);
        // Place the datasets into the respective datasets wrappers
        LineData lineData = new LineData(dataSet2);
        BarData barData = new BarData(dataSet);

        CombinedData multiple = new CombinedData();
        multiple.setData(barData);
        multiple.setData(lineData);
        // Create the formatter for the X-Axis
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter();
        formatter.setValues(dates);
        CombinedChart chart = (CombinedChart) findViewById(R.id.combinedChar);
        XAxis bottomAxis = chart.getXAxis();
        bottomAxis.setGranularity(1f);
        bottomAxis.setValueFormatter(formatter);

        // Set the data in the table and create it.
        chart.setData(multiple);
        chart.invalidate();
    }
}