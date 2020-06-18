package com.example.exercisedatabase.userExersiseList;

import android.Manifest;
//import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercisedatabase.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class exersiseListInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exersise_list_information);
        setTitle(getIntent().getStringExtra("Name") + "-" + getIntent().getStringExtra("Exersise") + " Exercise");
        setText();
        setButtonListeners();
    }

    /**
     * Set the button to open the log sheet addon depending on the type of exercise
     */
    private void setButtonListeners() {
        Button button = (Button)findViewById(R.id.exersiseListAddNew);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getStringExtra("ExType").equals("Weights")) {
                    openAddNewWeightLog();
                } else if (getIntent().getStringExtra("ExType").equals("Endurance")) {
                    openAddNewEnduranceLog();
                }
            }
        });
        button = (Button)findViewById(R.id.exersiseListView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getStringExtra("ExType").equals("Weights")) {
                    viewOldWeightLogs();
                } else if (getIntent().getStringExtra("ExType").equals("Endurance")) {
                    viewOldEnduranceLogs();
                }
            }
        });
    }

    /**
     * Function to view old weight logs
     */
    private void viewOldWeightLogs() {
        Intent intent = new Intent(this, viewOldWeightLogs.class);
        intent.putExtra("ID", getIntent().getStringExtra("ID"));
        intent.putExtra("ExName", getIntent().getStringExtra("ExName"));
        intent.putExtra("Exersise", getIntent().getStringExtra("Exersise"));
        startActivity(intent);
    }

    /**
     * Function to view old endurance logs
     */
    private void viewOldEnduranceLogs() {
        Intent intent = new Intent(this, viewOldEnduranceLogs.class);
        intent.putExtra("ID", getIntent().getStringExtra("ID"));
        intent.putExtra("ExName", getIntent().getStringExtra("ExName"));
        intent.putExtra("Exersise", getIntent().getStringExtra("Exersise"));
        startActivity(intent);
    }

    /**
     * Function to open the weight exersise log sheet data entry.
     */
    private void openAddNewWeightLog() {
        Intent intent = new Intent(this, addNewWeightLog.class);
        intent.putExtra("ID", getIntent().getStringExtra("ID"));
        intent.putExtra("ExType", getIntent().getStringExtra("ExType"));
        intent.putExtra("ExName", getIntent().getStringExtra("ExName"));
        intent.putExtra("Name", getIntent().getStringExtra("Name"));
        intent.putExtra("Exersise", getIntent().getStringExtra("Exersise"));
        startActivity(intent);
    }

    private void openAddNewEnduranceLog() {
        Intent intent = new Intent(this, addNewEnduranceLog.class);
        intent.putExtra("ID", getIntent().getStringExtra("ID"));
        intent.putExtra("ExType", getIntent().getStringExtra("ExType"));
        intent.putExtra("ExName", getIntent().getStringExtra("ExName"));
        intent.putExtra("Name", getIntent().getStringExtra("Name"));
        intent.putExtra("Exersise", getIntent().getStringExtra("Exersise"));
        startActivity(intent);
    }

    /**
     * Set all the information to the correct exersise.
     */
    private void setText() {
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }

        //Log.d("Name_1", Environment.getExternalStorageDirectory().toString());
        //Log.d("Name_2", getIntent().getStringExtra("PhotoName"));
        ImageView imageView = (ImageView)findViewById(R.id.imageView2);
        try {
            File f = new File(Environment.getExternalStorageDirectory().toString() + "/saved_images", getIntent().getStringExtra("PhotoName"));
            FileInputStream in = new FileInputStream(f);
            Bitmap b = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/saved_images/"+getIntent().getStringExtra("PhotoName"));
            if (b != null) {
                imageView.setImageBitmap(b);
                Log.d("Gmam", "NULLLLLLLLLLLLLLLLLLLLLLLLL");
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (NullPointerException e) {

        }

        TextView textName = (TextView)findViewById(R.id.exersiseListExersiseName);
        TextView textDesc = (TextView)findViewById(R.id.exersiseListExersiseDesc);
        TextView textType = (TextView)findViewById(R.id.exersiseListExersiseType);

        textName.setText(getIntent().getStringExtra("ExName"));
        textDesc.setText(getIntent().getStringExtra("ExDesc"));
        textType.setText(getIntent().getStringExtra("ExType"));
    }
}
