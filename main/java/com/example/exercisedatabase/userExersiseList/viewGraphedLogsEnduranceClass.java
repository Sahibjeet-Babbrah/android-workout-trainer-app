package com.example.exercisedatabase.userExersiseList;

import android.graphics.Color;
//import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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

import java.util.ArrayList;
import java.util.List;

public class viewGraphedLogsEnduranceClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_graphed_logs_endurance_class);
        setTitle(getIntent().getStringExtra("ExName"));
        Log.d("m", "JKJksjldf");
        createGraph();
    }

    private void createGraph() {

        List<BarEntry> timeList = new ArrayList<BarEntry>();
        List<Entry> timeTrend = new ArrayList<Entry>();
        exersiseLogDatabaseHandler db = new exersiseLogDatabaseHandler(getApplicationContext());
        List<enduranceLog> logs = db.getEnduranceExercise(Integer.parseInt(getIntent().getStringExtra("ID")), getIntent().getStringExtra("Exersise"), getIntent().getStringExtra("ExName"));
        String[] dates = new String[logs.size()];

        for (int i = 0; i < logs.size(); i++) {
            timeList.add(new BarEntry(i, (logs.get(i).getTimeMinutes() * 60) + logs.get(i).getTimeSeconds()));
            dates[i] = logs.get(i).getDate();
        }

        float a = 1f;
        float b1 = 0f;
        float b2 = 0f;
        float c = 1f;
        float d = 0f;
        float e = 0f;
        float f = 1f;

        for (int i = 0; i < logs.size(); i++) {
            float time = ((logs.get(i).getTimeMinutes() * 60) + logs.get(i).getTimeSeconds());
            a += i * time;
            b1 += i;
            b2 += time;
            c += i*i;
            d += i;
            e += time;
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
            timeTrend.add(new Entry(i, (slope * (i + 1)) + yInter));
        }

        // Create the dataset for the bar graph
        BarDataSet dataSet = new BarDataSet(timeList, "Time (Seconds)");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.RED);
        dataSet.setValueTextSize(12f);

        // Create the dataset for the line graph
        LineDataSet lineSet = new LineDataSet(timeTrend, "Trend");
        lineSet.setColor(Color.RED);
        lineSet.setDrawValues(false);

        LineData lineData = new LineData(lineSet);
        BarData barData = new BarData(dataSet);

        CombinedData multiple = new CombinedData();
        multiple.setData(lineData);
        multiple.setData(barData);

        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter();
        formatter.setValues(dates);
        CombinedChart chart = (CombinedChart) findViewById(R.id.combinedCharEndurance);
        XAxis bottomAxis = chart.getXAxis();
        bottomAxis.setGranularity(1f);
        bottomAxis.setValueFormatter(formatter);
        // Set the data in the table and create it.
        chart.setData(multiple);
        chart.invalidate();
    }
}
