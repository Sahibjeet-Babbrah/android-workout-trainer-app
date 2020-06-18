package com.example.exercisedatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercisedatabase.muscleInformation.MuscleListInformation;
import com.example.exercisedatabase.user_table_information.Add_New_User;
import com.example.exercisedatabase.user_table_information.User;
import com.example.exercisedatabase.user_table_information.UserDatabaseHandler;
import com.example.exercisedatabase.user_table_information.UsersAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("User List");
        updateListView();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //MobileAds.setRequestConfiguration(new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("ABCDEF012345")).build());

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    /**
     * Function to update the listView of the loaded in view.
     * */
    public void updateListView() {
        UserDatabaseHandler db = new UserDatabaseHandler(getApplicationContext());
        lv = (ListView) findViewById(R.id.UserListView);
        // Message Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        final List<User> userList = db.getAllUsers();

        //Place all the data into an arrayList
        ArrayList<User> newArrList = new ArrayList<User>();
        if (db.getUsersCount() == 0) {
            openAddUserActivity();
        }

        //Transfer data from list to arrayList
        for (User us : userList) {
            newArrList.add(us);
        }
        UsersAdapter adapter = new UsersAdapter(this, newArrList);
        lv.setAdapter(adapter);

        //Listener for then an item is clicked
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String jdkf = Integer.toString(userList.get(position).get_id());
                String selected = ((TextView) view.findViewById(R.id.userName)).getText().toString();
                Log.d("NLA:", jdkf + " " + selected);
                muscleInformationActivity(jdkf, selected);
            }
        });
    }

    public void muscleInformationActivity(String value, String name) {
        Intent intent = new Intent(this, MuscleListInformation.class);
        intent.putExtra("Name", name);
        intent.putExtra("ID", value);
        startActivity(intent);
    }

    /**
     * Function to create the menu screen ontop of the main screen.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Function to set the actions for when a menu item is selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle Item Selection
        switch (item.getItemId()) {
            case R.id.action_add:
                openAddUserActivity();
                Log.d("Action add", "Adding User 4");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Function to open the add user activity.
     */
    public void openAddUserActivity() {
        Intent intent = new Intent(this, Add_New_User.class);
        //startActivity(intent);
        startActivityForResult(intent, 1);
    }

    /**
     * Function to check the return values of the Intents called.
     * @param requestCode The code requested by the intent
     * @param resultCode The resultant code returned from the finished intent
     * @param data The data returned from the closed intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            updateListView();
        }
    }
}