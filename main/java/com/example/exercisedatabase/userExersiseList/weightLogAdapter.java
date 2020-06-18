package com.example.exercisedatabase.userExersiseList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.exercisedatabase.R;

import java.util.ArrayList;

public class weightLogAdapter extends ArrayAdapter<weightLog> {

    public weightLogAdapter(Activity context, ArrayList<weightLog> logs) {
        super(context, 0, logs);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // The the data for the position
        weightLog log = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_weight_log, parent, false);
        }

        // Look up view for data population
        TextView date = (TextView)convertView.findViewById(R.id.itemWeightDate);
        TextView oneWeightMax = (TextView)convertView.findViewById(R.id.itemWeightOneRepMax);
        TextView oneWeightType = (TextView)convertView.findViewById(R.id.itemWeightOneRepType);
        TextView weight = (TextView)convertView.findViewById(R.id.itemWeightWeight);
        TextView weightType = (TextView)convertView.findViewById(R.id.itemWeightWeightType);
        TextView reps = (TextView)convertView.findViewById(R.id.itemWeightReps);
        TextView rest = (TextView)convertView.findViewById(R.id.itemWeightRest);

        date.setText(log.getDate());
        oneWeightMax.setText(String.valueOf(log.getOneRepMax()));
        weight.setText(String.valueOf(log.getWeight()));
        reps.setText(String.valueOf(log.getReps()));
        rest.setText(String.valueOf(log.getRests()));
        if (log.getWeightType().equals("Metric")) {
            oneWeightType.setText(" Kilograms");
            weightType.setText(" Kilograms");
        } else {
            oneWeightType.setText(" Pounds");
            weightType.setText(" Pounds");
        }

        return convertView;
    }
}
