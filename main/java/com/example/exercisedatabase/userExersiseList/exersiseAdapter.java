package com.example.exercisedatabase.userExersiseList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exercisedatabase.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class exersiseAdapter extends ArrayAdapter<exersiseList> {

    public exersiseAdapter(Context context, ArrayList<exersiseList> users) {
        super(context, 0, users);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for the position
        exersiseList exersise = getItem(position);
        // Check if the existing view is reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_exersise, parent, false);
        }
        // Lookup view for data population
        TextView one = (TextView)convertView.findViewById(R.id.exersiseDisplayName);
        TextView two = (TextView)convertView.findViewById(R.id.exersiseDisplayType);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView9);

        try {
            File f = new File(Environment.getExternalStorageDirectory().toString() + "/saved_images", exersise.getPhotoName());
            FileInputStream in = new FileInputStream(f);
            Bitmap b = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/saved_images/" + exersise.getPhotoName());
            if (b != null) {
                imageView.setImageBitmap(b);
                Log.d("Gmam", "NULLLLLLLLLLLLLLLLLLLLLLLLL");
            }
        } catch (FileNotFoundException e) {
            // Do Nothing
        } catch (NullPointerException e) {

        }

        String temp = "Exersise Type : " + exersise.getExersiseType();
        one.setText(exersise.getExersiseName());
        two.setText(temp);

        return convertView;
    }
}
