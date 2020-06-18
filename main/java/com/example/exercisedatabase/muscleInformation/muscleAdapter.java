package com.example.exercisedatabase.muscleInformation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.exercisedatabase.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class muscleAdapter extends ArrayAdapter<muscleInformation> {
    /**
     * Function to create the muscle adapter function, called by the function
     */
    public muscleAdapter(Context context, ArrayList<muscleInformation> muscle) {
        super(context, 0, muscle);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position.
        muscleInformation muscle = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_muscle, parent, false);
        }
        // Lookup view for data population
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);

        /*
         * Get all of the textview to be updated for the information for the general information
         */
        TextView date = (TextView)convertView.findViewById(R.id.measuremenLayDate);
        TextView height1 = (TextView)convertView.findViewById(R.id.measurementLayHeight1);
        TextView height2 = (TextView)convertView.findViewById(R.id.measurementLayHeight2);
        TextView heightType1 = (TextView)convertView.findViewById(R.id.measurementHeightType1);
        TextView heightType2 = (TextView)convertView.findViewById(R.id.measurementHeightType2);
        TextView weight = (TextView)convertView.findViewById(R.id.measurementLayWeight);
        TextView weightType = (TextView)convertView.findViewById(R.id.measurementWeightType);
        TextView BMI = (TextView)convertView.findViewById(R.id.measurementLayBMI);

        /*
         * Get all the textview for the upper body information
         */
        TextView neck = (TextView)convertView.findViewById(R.id.measurementLayNeck);
        TextView neckType = (TextView)convertView.findViewById(R.id.measurementNeckType);
        TextView chest = (TextView)convertView.findViewById(R.id.measurementLayChest);
        TextView chestType = (TextView)convertView.findViewById(R.id.measurementChestType);
        TextView waist = (TextView)convertView.findViewById(R.id.measurementLayWaist);
        TextView waistType = (TextView)convertView.findViewById(R.id.measurementWaistType);
        TextView hips = (TextView)convertView.findViewById(R.id.measurementLayHips);
        TextView hipsType = (TextView)convertView.findViewById(R.id.measurementHipsType);

        /*
         * Get all the textview for the arm information
         */
        TextView bicep = (TextView)convertView.findViewById(R.id.measurementLayBicep);
        TextView bicepType = (TextView)convertView.findViewById(R.id.measurementBicepType);
        TextView forearm = (TextView)convertView.findViewById(R.id.measurementLayForearm);
        TextView forearmType = (TextView)convertView.findViewById(R.id.measurementForearmType);
        TextView wrist = (TextView)convertView.findViewById(R.id.measurementLayWrist);
        TextView wristType = (TextView)convertView.findViewById(R.id.measurementWristType);

        /*
         * Get all the textview for the legs information
         */
        TextView thigh = (TextView)convertView.findViewById(R.id.measurementLayThigh);
        TextView thighType = (TextView)convertView.findViewById(R.id.measurementThighType);
        TextView calves = (TextView)convertView.findViewById(R.id.measurementLayCalves);
        TextView calvesType = (TextView)convertView.findViewById(R.id.measurementCalvesType);

        /*
         * Place all of the general information onto the screen for the general information.
         */
        date.setText(muscle.getDate());
        height1.setText(df.format(muscle.getHeight()));
        height2.setText(df.format(muscle.getHeight2()));
        weight.setText(Float.toString(muscle.getWeight()));
        BMI.setText(Float.toString(muscle.getBMI()));
        if (muscle.getGeneral_measurement().equals("Metric")) {
            heightType1.setText(" metres and ");
            heightType2.setText(" centimeters");
            weightType.setText(" kilograms");
        } else {
            heightType1.setText(" feet and ");
            heightType2.setText(" inches");
            weightType.setText(" pounds");
        }

        /*
         * Place all of the general information onto the screen for the upper body information.
         */
        neck.setText(Float.toString(muscle.getNeck()));
        chest.setText(Float.toString(muscle.getChest()));
        waist.setText(Float.toString(muscle.getWaist()));
        hips.setText(Float.toString(muscle.getHips()));
        if (muscle.getUpper_measurement().equals("Metric")) {
            neckType.setText(" centimetres");
            chestType.setText(" centimetres");
            waistType.setText(" centimetres");
            hipsType.setText(" centimetres");
        } else {
            neckType.setText(" inches");
            chestType.setText(" inches");
            waistType.setText(" inches");
            hipsType.setText(" inches");
        }

        /*
         * Place all of the general information onto the screen for the arm information.
         */
        bicep.setText(Float.toString(muscle.getBiceps()));
        forearm.setText(Float.toString(muscle.getForearms()));
        wrist.setText(Float.toString(muscle.getWrist()));
        if (muscle.getArms_measurement().equals("Metric")) {
            bicepType.setText(" centimetres");
            forearmType.setText(" centimetres");
            wristType.setText(" centimetres");
        } else {
            bicepType.setText(" inches");
            forearmType.setText(" inches");
            wristType.setText(" inches");
        }

        /*
         * Place all of the general information onto the screen for the leg information.
         */
        thigh.setText(Float.toString(muscle.getThighs()));
        calves.setText(Float.toString(muscle.getCalves()));
        if (muscle.getLegs_measurement().equals("Metric")) {
            thighType.setText(" centimetres");
            calvesType.setText(" centimetres");
        } else {
            thighType.setText(" inches");
            calvesType.setText(" inches");
        }

        return convertView;
    }
}
