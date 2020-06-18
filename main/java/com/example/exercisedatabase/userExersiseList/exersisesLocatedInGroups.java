package com.example.exercisedatabase.userExersiseList;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercisedatabase.R;

import java.util.ArrayList;
import java.util.List;

public class exersisesLocatedInGroups extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exersises_located_in_groups);
        setTitle(getIntent().getStringExtra("Name") + " - " + getIntent().getStringExtra("Exersise") + " Exersises");
        setListView();
    }

    /**
     * TODO - set the listener for each clicked item
     */
    private void setListView() {
        exersiseListDatabaseHandler db = new exersiseListDatabaseHandler(getApplicationContext());
        ListView lv = (ListView)findViewById(R.id.exersiseListActually);
        List<exersiseList> list = db.getUserExersises(Integer.parseInt(getIntent().getStringExtra("ID")), getIntent().getStringExtra("Exersise"));
        final ArrayList<exersiseList> newList = new ArrayList<exersiseList>();

        for (exersiseList e : list) {
            newList.add(e);
        }
        exersiseAdapter adapter = new exersiseAdapter(this, newList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewInformation(newList.get(position));
            }
        });
    }

    /**
     * Open the view show the information about the exersises.
     */
    private void  viewInformation(exersiseList info) {
        Intent intent = new Intent(this, exersiseListInformation.class);
        intent.putExtra("ID", getIntent().getStringExtra("ID"));
        intent.putExtra("Name", getIntent().getStringExtra("Name"));
        intent.putExtra("Exersise", getIntent().getStringExtra("Exersise"));
        intent.putExtra("ExName", info.getExersiseName());
        intent.putExtra("ExDesc", info.getExersiseDescription());
        intent.putExtra("ExType", info.getExersiseType());
        intent.putExtra("PhotoInformation", info.getPhotoLocation());
        intent.putExtra("PhotoName", info.getPhotoName());
        startActivity(intent);
    }

    /**
     * Function to create the menu at the top bar for adding new exersises.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_exersises_menu, menu);
        return true;
    }

    /**
     * Listener for the menu selecting item.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addExersiseMenu:
                openAddExersiseMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Function to start the new activity to add a new exersise to the list.
     */
    public void openAddExersiseMenu() {
        Intent intent = new Intent(this, createNewExersise.class);
        intent.putExtra("ID", getIntent().getStringExtra("ID"));
        intent.putExtra("Exersise", getIntent().getStringExtra("Exersise"));
        startActivityForResult(intent, 2);
    }

    /**
     * Activity to wait for a result from the add new activity list, when it's done.
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            setListView();
        }
    }
}
