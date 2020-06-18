package com.example.exercisedatabase.user_table_information;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercisedatabase.MainActivity;
import com.example.exercisedatabase.R;

import java.util.Calendar;

public class Add_New_User extends AppCompatActivity {

    private Button addButton;
    private UserDatabaseHandler db;
    private DatePickerDialog picker;
    private EditText eText;
    private TextView tView;
    private Spinner spinner;
    private static final String[] genders = {"Select Gender", "Male", "Female", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__new__user);
        setTitle("Add New User");

        spinner = findViewById(R.id.genderSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genders);
        spinner.setAdapter(adapter);
        //Call function to create the handler for the add user button
        addButtonHandler();
        //Call function to create the handler for the date picker popup
        setDatePicker();
    }

    private void addButtonHandler() {
        addButton = (Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Button: ", "Clicked");
                if (checkInput())
                {
                    db = new UserDatabaseHandler(getApplicationContext());
                    EditText firstName = (EditText)findViewById(R.id.firstNameInput);
                    EditText lastName = (EditText)findViewById(R.id.lastNameInput);
                    EditText dateOfBirth = (EditText)findViewById(R.id.datePicker);
                    Spinner gender = (Spinner)findViewById(R.id.genderSpinner);
                    StringBuilder newString = new StringBuilder();
                    newString.append(firstName.getText().toString());
                    newString.append(" ");
                    newString.append(lastName.getText().toString());
                    User newUser = new User();
                    newUser.set_name(newString.toString());
                    newUser.set_date_of_birth(dateOfBirth.getText().toString());
                    newUser.set_gender(gender.getSelectedItem().toString());
                    db.addUser(newUser);
                    setResult(1);
                    finish();
                }
            }
        });
    }

    private void backToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private boolean checkInput() {
        EditText firstName = (EditText)findViewById(R.id.firstNameInput);
        EditText lastName = (EditText)findViewById(R.id.lastNameInput);
        EditText dateOfBirth = (EditText)findViewById(R.id.datePicker);
        Spinner gender = (Spinner)findViewById(R.id.genderSpinner);
        String fName = firstName.getText().toString();
        String lName = lastName.getText().toString();
        String dBirth = dateOfBirth.getText().toString();
        String gen = gender.getSelectedItem().toString();
        if ((fName != null && !fName.isEmpty()) && (lName != null && !lName.isEmpty()) && (dBirth != null && !dBirth.isEmpty()) && (!gen.equals("Select Gender"))) {
            return true;
        }
        else
        {
            Toast toast;
            if (fName.isEmpty()) {
                toast = Toast.makeText(this, "First Name Cannot be Empty", Toast.LENGTH_SHORT);
            }
            else if (lName.isEmpty()) {
                toast = Toast.makeText(this, "Last Name Cannot be Empty", Toast.LENGTH_SHORT);
            }
            else if (dBirth.isEmpty()) {
                toast = Toast.makeText(this, "Date of Birth Cannot be Empty", Toast.LENGTH_SHORT);
            }
            else {
                toast = Toast.makeText(this, "Gender Cannot be Empty", Toast.LENGTH_SHORT);
            }
            toast.show();
        }
        return false;
    }

    private void setDatePicker() {
        eText = (EditText) findViewById(R.id.datePicker);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // Date Picker Dialog
                picker = new DatePickerDialog(Add_New_User.this, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        eText.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });
    }
}
