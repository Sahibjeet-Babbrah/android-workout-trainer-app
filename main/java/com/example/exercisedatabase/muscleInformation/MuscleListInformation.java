package com.example.exercisedatabase.muscleInformation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercisedatabase.R;
import com.example.exercisedatabase.userExersiseList.viewAllExersiseList;
import com.example.exercisedatabase.userExersiseList.exersiseListDatabaseHandler;
import com.example.exercisedatabase.userExersiseList.exersiseLogDatabaseHandler;
import com.example.exercisedatabase.muscleInformation.MuscleDatabaseHandler;
import com.example.exercisedatabase.user_table_information.UserDatabaseHandler;

import java.util.ArrayList;

public class MuscleListInformation extends AppCompatActivity {

    private Button backButton;
    private ListView lv;
    private ArrayList<String> exersiseGroupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_list_information);
        String name = getIntent().getStringExtra("Name");

        setTitle(name);
        goingBack();
        createList();
    }

    /**
     * Function to set the back button to go back to the previous.
     */
    private void goingBack() {
        backButton = (Button)findViewById(R.id.BackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Function to start the activity to enter a new set of muscle information.
     */
    private void enterNewMeasurement() {
        Intent intent = new Intent(this, NewMeasurementEnter.class);
        intent.putExtra("Name", getIntent().getStringExtra("Name"));
        intent.putExtra("ID", getIntent().getStringExtra("ID"));
        startActivity(intent);
    }

    /**
     * Function to start the activity to view the set of muscle information.
     */
    private void enterViewMeasurements() {
        Intent intent = new Intent(this, viewMuscleInformation.class);
        intent.putExtra("ID", getIntent().getStringExtra("ID"));
        intent.putExtra("Name", getIntent().getStringExtra("Name"));
        startActivity(intent);
    }

    private void enterNewExersise() {
        Intent intent = new Intent(this, viewAllExersiseList.class);
        intent.putExtra("ID", getIntent().getStringExtra("ID"));
        intent.putExtra("Name", getIntent().getStringExtra("Name"));
        startActivity(intent);
    }

    /**
     * Function to create the list of the muscle options.
     */
    private void createList() {
        exersiseGroupList = new ArrayList<String>();
        exersiseGroupList.add("Add New Body Measurements");
        exersiseGroupList.add("View Body Measurements");
        exersiseGroupList.add("Exersise List");
        exersiseGroupList.add("Delete User");
        lv = (ListView)findViewById(R.id.muscleListView);
        ArrayAdapter<String> list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exersiseGroupList);
        lv.setAdapter(list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Log.d("Tag", "Add New Body");
                    enterNewMeasurement();
                } else if (position == 1) {
                    Log.d("Tag", "View Old Body Measurements");
                    enterViewMeasurements();
                } else if (position == 2) {
                    // Temporary test of create new Exersises, this should go to a menu containing list of all the groups which will display all exersises per group, then you select a new
                    // exersise based on the group your in.
                    enterNewExersise();
                } else if (position == 3) {
                    deleteUser();
                }
            }
        });
    }

    private void deleteUser() {
        exersiseLogDatabaseHandler logDB = new exersiseLogDatabaseHandler(getApplicationContext());
        exersiseListDatabaseHandler listDB = new exersiseListDatabaseHandler(getApplicationContext());
        MuscleDatabaseHandler muscleDB = new MuscleDatabaseHandler(getApplicationContext());
        UserDatabaseHandler userDB = new UserDatabaseHandler(getApplicationContext());
        logDB.deleteUser(Integer.parseInt(getIntent().getStringExtra("ID")));
        listDB.deleteUserFromList(Integer.parseInt(getIntent().getStringExtra("ID")));
        muscleDB.deleteUserFromList(Integer.parseInt(getIntent().getStringExtra("ID")));
        userDB.deleteUser(Integer.parseInt(getIntent().getStringExtra("ID")));

    }
}
