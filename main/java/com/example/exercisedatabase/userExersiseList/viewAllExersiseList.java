package com.example.exercisedatabase.userExersiseList;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercisedatabase.R;

import java.util.List;

public class viewAllExersiseList extends AppCompatActivity {

    private String[] muscleGroups = {"Neck" ,"Bicep", "Forearm", "Wrist", "Chest", "Waist", "Hips", "Thighs", "Calves"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_exersise_list);
        setTitle(getIntent().getStringExtra("Name") + " - Exersise Groups");
        setList();
    }

    /**
     * Function to set the list to the list view.
     */
    private void setList() {
        // Set the back listener to return from this view.
        Button button = (Button)findViewById(R.id.viewExersiseGroupsBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Set all the items in the list view.
        ListView lv = (ListView)findViewById(R.id.viewAllExersistListList);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, muscleGroups);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Clicked", parent.getItemAtPosition(position).toString());
                enterExersiseGroups(parent.getItemAtPosition(position).toString());
            }
        });
    }

    private void enterExersiseGroups(String groupName) {
        Intent intent = new Intent(this, exersisesLocatedInGroups.class);
        intent.putExtra("Name", getIntent().getStringExtra("Name"));
        intent.putExtra("ID", getIntent().getStringExtra("ID"));
        intent.putExtra("Exersise", groupName);
        startActivity(intent);
    }
}