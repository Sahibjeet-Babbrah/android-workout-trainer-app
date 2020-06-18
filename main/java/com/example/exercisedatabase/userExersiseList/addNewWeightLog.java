package com.example.exercisedatabase.userExersiseList;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
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
import com.example.exercisedatabase.muscleInformation.NewMeasurementEnter;

import java.util.Calendar;

public class addNewWeightLog extends AppCompatActivity {

    private String[] measurement = {"Metric", "Imperial"};
    private EditText eText;
    private DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_weight_log);
        setTitle(getIntent().getStringExtra("Name") + " - New " + getIntent().getStringExtra("ExName") + " Log");
        setMeasurement();
        setDate();
        setRollPicker();
        setAcceptButton();
    }

    /**
     * Set the listener for the accept button.
     */
    private void setAcceptButton() {
        Button button = (Button)findViewById(R.id.setWeightAccept);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEntries()) {
                    exersiseLogDatabaseHandler db = new exersiseLogDatabaseHandler(getApplicationContext());

                    Spinner sp = (Spinner)findViewById(R.id.addWeightMeasurementSpinner);
                    EditText date = (EditText)findViewById(R.id.addWeightDate);
                    EditText weight = (EditText)findViewById(R.id.weightInput);
                    EditText sets = (EditText)findViewById(R.id.setsInput);
                    EditText reps = (EditText)findViewById(R.id.repsInput);
                    EditText rest = (EditText)findViewById(R.id.restInput);

                    weightLog we = new weightLog(Integer.parseInt(getIntent().getStringExtra("ID")), getIntent().getStringExtra("ExName"), getIntent().getStringExtra("Exersise"));
                    we.setWeight(Float.parseFloat(weight.getText().toString()));
                    we.setSets(Integer.parseInt(sets.getText().toString()));
                    we.setReps(Integer.parseInt(reps.getText().toString()));
                    if (rest.getText().toString().isEmpty() == false) {
                        we.setRests(Integer.parseInt(rest.getText().toString()));
                    }
                    we.setWeightType(sp.getSelectedItem().toString());
                    we.setDate(date.getText().toString());

                    db.addWeightLog(we);
                    Toast.makeText(getApplicationContext(), "Log Added Successfully", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    /**
     * Function to check all the inputs have been entered.
     */
    private boolean checkEntries() {
        EditText weight = (EditText)findViewById(R.id.weightInput);
        EditText sets = (EditText)findViewById(R.id.setsInput);
        EditText reps = (EditText)findViewById(R.id.repsInput);
        EditText rest = (EditText)findViewById(R.id.restInput);

        if (weight.getText().toString().equals("")) {
            Toast.makeText(this, "Weight Required", Toast.LENGTH_LONG).show();
            return false;
        } else if (sets.getText().toString().equals("")) {
            Toast.makeText(this, "Sets Required", Toast.LENGTH_LONG).show();
            return false;
        } else if (reps.getText().toString().equals("")) {
            Toast.makeText(this, "Reps Required", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    /**
     * Function to set the roll picker listener
     */
    private void setRollPicker() {
        Spinner sp = (Spinner)findViewById(R.id.addWeightMeasurementSpinner);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView2 = (TextView)findViewById(R.id.weightInputMeasurementType);
                if (position == 0) {
                    textView2.setText("kg");
                } else {
                    textView2.setText("pounds");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
    }

    /**
     * Set the measurements spinner, to switch from metric measurements to imperial.
     */
    private void setMeasurement() {
        Button button = (Button)findViewById(R.id.addWeightCancel);
        Spinner sp = (Spinner)findViewById(R.id.addWeightMeasurementSpinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, measurement);
        sp.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Function to set the date picker automatically
     */
    private void setDate() {
        eText = (EditText) findViewById(R.id.addWeightDate);
        eText.setInputType(InputType.TYPE_NULL);
        //Set the date to the current date.
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
                // Date Picker Dialog
                picker = new DatePickerDialog(addNewWeightLog.this, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
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
