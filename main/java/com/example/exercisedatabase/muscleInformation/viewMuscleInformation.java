package com.example.exercisedatabase.muscleInformation;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercisedatabase.R;

import java.util.ArrayList;
import java.util.List;

public class viewMuscleInformation extends AppCompatActivity {

    /**
     * Function used to create the data when the information is called.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_muscle_information);
        // Create a new database handler object
        MuscleDatabaseHandler db = new MuscleDatabaseHandler(getApplicationContext());
        // Set the table name to be equal to the name plus the information
        setTitle(getIntent().getStringExtra("Name") + " - Information");

        // Get the list of information tied to the ID of the current user.
        List<muscleInformation> list = db.getMeasurementData(Integer.parseInt(getIntent().getStringExtra("ID")));
        // Get the listview object on the user.
        ListView lv = (ListView)findViewById(R.id.muscleListView2);
        ArrayList<muscleInformation> newList = new ArrayList<muscleInformation>();
        // Swap from a list to an ArrayList
        for(muscleInformation m : list) {
            newList.add(m);
        }

        // Create the muscle adapter object and apply to the information
        muscleAdapter musclesAd = new muscleAdapter(this, newList);
        lv.setAdapter(musclesAd);
        // Call the function to create the click handler for the button
        returnKey();
    }

    /**
     * Function to set the call to finish the page and return back.
     */
    private void returnKey() {
        Button button = (Button)findViewById(R.id.returnFromInfo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the intent and go back to the previous screen
                finish();
            }
        });
    }
}
