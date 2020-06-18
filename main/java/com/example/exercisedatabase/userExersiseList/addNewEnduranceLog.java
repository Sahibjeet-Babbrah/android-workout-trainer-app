package com.example.exercisedatabase.userExersiseList;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
//import android.text.TextPaint;
//import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercisedatabase.R;

import java.util.Calendar;

public class addNewEnduranceLog extends AppCompatActivity {

    private String[] measurement = {"Metric (m)", "Metric (km)", "Imperial (ft)", "Imperial (miles)"};
    private String[] intensity = {"Easy", "Medium", "Hard"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_endurance_log);
        setTitle(getIntent().getStringExtra("Name") + " - New " + getIntent().getStringExtra("ExName") + " Log");
        setSpinners();
        setDate();
        setButton();
    }

    /**
     * Function to set the listeners for the button.
     */
    private void setButton() {
        Button button = (Button)findViewById(R.id.addEnduranceCancel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Log Add Cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        // Create the button listener for the accept button.
        button = (Button)findViewById(R.id.setEnduranceAccept);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {
                    enduranceLog log = new enduranceLog(Integer.parseInt(getIntent().getStringExtra("ID")), getIntent().getStringExtra("ExName"), getIntent().getStringExtra("Exersise"));
                    Spinner spinner1 = (Spinner)findViewById(R.id.addEnduranceMeasurementSpinner);
                    Spinner spinner2 = (Spinner)findViewById(R.id.addEnduranceIntensitySpinner);
                    EditText one = (EditText)findViewById(R.id.addEnduranceMinutes);
                    EditText two = (EditText)findViewById(R.id.addEnduranceSeconds);
                    EditText three = (EditText)findViewById(R.id.distanceInput);
                    EditText four = (EditText)findViewById(R.id.addEnduranceDate);

                    log.setDate(four.getText().toString());
                    log.setTimeMinutes(Float.parseFloat(one.getText().toString()));
                    log.setTimeSeconds(Float.parseFloat(two.getText().toString()));
                    if (three.getText().toString().isEmpty() == false) {
                        log.setDistance(Float.parseFloat(three.getText().toString()));
                    }
                    log.setEndurance_type(spinner1.getSelectedItem().toString());
                    log.setIntensity(spinner2.getSelectedItem().toString());

                    exersiseLogDatabaseHandler db = new exersiseLogDatabaseHandler(getApplicationContext());
                    db.addEnduranceLog(log);
                    Toast.makeText(getApplicationContext(), "Log Added Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    /**
     * Function to check the input and make sure there is input in the forms.
     */
    private boolean checkInput() {
        EditText one = (EditText)findViewById(R.id.addEnduranceMinutes);
        EditText two = (EditText)findViewById(R.id.addEnduranceSeconds);
        if (one.getText().toString().equals("")) {
            Toast.makeText(this, "Time must not be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (two.getText().toString().equals("")) {
            Toast.makeText(this, "Time must not be empty", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    /**
     * Function to set the date picker.
     */
    private void setDate() {
        final EditText eText = (EditText)findViewById(R.id.addEnduranceDate);
        eText.setInputType(InputType.TYPE_NULL);
        // Set the date to the current date
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        eText.setText((month + 1) + "/" + day + "/" + year);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                DatePickerDialog picker = new DatePickerDialog(addNewEnduranceLog.this, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        eText.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });
    }

    /**
     * Function to set the spinners
     */
    private void setSpinners() {
        Spinner sp = (Spinner)findViewById(R.id.addEnduranceMeasurementSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, measurement);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tp = (TextView)findViewById(R.id.distanceInputMeasurementType);
                if (position == 0) {
                    tp.setText("m");
                } else if (position == 1) {
                    tp.setText("km");
                } else if (position == 2) {
                    tp.setText("ft");
                } else if (position == 3) {
                    tp.setText("miles");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        sp = (Spinner)findViewById(R.id.addEnduranceIntensitySpinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, intensity);
        sp.setAdapter(adapter1);
    }
}
