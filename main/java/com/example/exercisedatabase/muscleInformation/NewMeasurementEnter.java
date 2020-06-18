package com.example.exercisedatabase.muscleInformation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class NewMeasurementEnter extends AppCompatActivity {

    private DatePickerDialog picker;
    private EditText height1;
    private EditText height2;
    private EditText weight;
    private Spinner sp;
    private EditText eText;
    private Button cancel;
    private Button accept;

    /**
     * Function called to create everything
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_measurement_enter);
        setCancel();
        setAccept();
        setDate();
        setSpinner();
        setGeneralListeners();
        setTitle(getIntent().getStringExtra("Name") + " - New Measurements");
        //Toast.makeText(getApplicationContext(), getIntent().getStringExtra("ID"), Toast.LENGTH_LONG).show();
    }

    /**
     * Function to set all the general listeners for the data entry and called to change the BMI calculations
     */
    private void setGeneralListeners() {
        height1 = (EditText)findViewById(R.id.heightInput1);
        height2 = (EditText)findViewById(R.id.heightInput2);
        weight = (EditText)findViewById(R.id.weightInput);
        height1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                return;
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkBMI(height1, height2, weight);
            }
        });

        height2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                return;
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkBMI(height1, height2, weight);
            }
        });

        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                return;
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkBMI(height1, height2, weight);
            }
        });
    }

    /**
     * Function to calculate the BMI from the inputted values of the height and weight.
     */
    private void checkBMI(EditText he1, EditText he2, EditText we1) {
        if ((he1 != null && !he1.getText().toString().isEmpty()) && (he2 != null && !he2.getText().toString().isEmpty()) && (we1 != null && !we1.getText().toString().isEmpty())) {
            float h1 = Float.valueOf(height1.getText().toString());
            float h2 = Float.valueOf(height2.getText().toString());
            float w1 = Float.valueOf(weight.getText().toString());
            float total = 0;
            float BMI = 0;
            if (sp.getSelectedItem().toString().equals("Metric")) {
                float metres = h1 * (float)100.0;
                total = metres + h2;
                total = total/100.0f;
                BMI = w1 / (total * total);
            }
            else if (sp.getSelectedItem().toString().equals("Imperial")) {
                float feet = h1 * (float)12.0;
                total = feet + h2;
                BMI = w1 / (total * total);
                BMI = BMI * 703;
            }
            EditText te = (EditText)findViewById(R.id.bmiInput);
            DecimalFormat df = new DecimalFormat("####.##");
            te.setText(df.format(BMI));
        }
    }

    /**
     * Function to set all of the spinner listener to change measurement types from metric to imperial.
     */
    private void setSpinner() {
        sp = (Spinner)findViewById(R.id.measurementTypeSpinner);
        String[] measureType = {"Metric", "Imperial"};
        ArrayAdapter<String> temp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, measureType);
        sp.setAdapter(temp);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView a1 = (TextView)findViewById(R.id.heigthText1);
                TextView a2 = (TextView)findViewById(R.id.heightText2);
                TextView a3 = (TextView)findViewById(R.id.weightView);
                if (position == 0) {
                    a1.setText("metres");
                    a2.setText("cm");
                    a3.setText("kilograms");
                }
                else if (position == 1) {
                    a1.setText("feet");
                    a2.setText("inches");
                    a3.setText("pounds");
                }
                checkBMI(height1, height2, weight );
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        Spinner sp2 = (Spinner)findViewById(R.id.measurementTypeSpinner2);
        sp2.setAdapter(temp);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView a1 = (TextView)findViewById(R.id.neckView);
                TextView a2 = (TextView)findViewById(R.id.chestView);
                TextView a3 = (TextView)findViewById(R.id.waistView);
                TextView a4 = (TextView)findViewById(R.id.hipView);
                if (position == 0) {
                    a1.setText("cm");
                    a2.setText("cm");
                    a3.setText("cm");
                    a4.setText("cm");
                } else if (position == 1) {
                    a1.setText("inches");
                    a2.setText("inches");
                    a3.setText("inches");
                    a4.setText("inches");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        Spinner sp3 = (Spinner)findViewById(R.id.measurementTypeSpinner3);
        sp3.setAdapter(temp);
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView a1 = (TextView)findViewById(R.id.bicepView);
                TextView a2 = (TextView)findViewById(R.id.forearmView);
                TextView a3 = (TextView)findViewById(R.id.wristView);
                if (position == 0) {
                    a1.setText("cm");
                    a2.setText("cm");
                    a3.setText("cm");
                } else if (position == 1) {
                    a1.setText("inches");
                    a2.setText("inches");
                    a3.setText("inches");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        sp3 = (Spinner)findViewById(R.id.measurementTypeSpinner4);
        sp3.setAdapter(temp);
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView a1 = (TextView)findViewById(R.id.thighView);
                TextView a2 = (TextView)findViewById(R.id.calvesView);
                if (position == 0) {
                    a1.setText("cm");
                    a2.setText("cm");
                } else if (position == 1) {
                    a1.setText("inches");
                    a2.setText("inches");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Set the listener for the cancel button, to finish the selelection, but not save the information.
     */
    private void setCancel() {
        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Set the listener for the accept button, to finish the selection and save the information into the database.
     */
    private void setAccept() {
        accept = (Button)findViewById(R.id.addMeasurementButton);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {
                    MuscleDatabaseHandler db = new MuscleDatabaseHandler(getApplicationContext());
                    muscleInformation measure = getInfo();
                    db.addMeasurements(measure);
                    finish();
                }
            }
        });
    }

    private muscleInformation getInfo() {
        muscleInformation info = new muscleInformation();
        ArrayList<TextView> textViews = getTextViews();
        info.setId(Integer.parseInt(getIntent().getStringExtra("ID")));
        info.setDate(textViews.get(0).getText().toString());
        info.setHeight(Float.parseFloat(textViews.get(1).getText().toString()), Float.parseFloat(textViews.get(2).getText().toString()));
        info.setWeight(Float.parseFloat(textViews.get(3).getText().toString()));
        info.setBMI(Float.parseFloat(textViews.get(4).getText().toString()));
        info.setNeck(Float.parseFloat(textViews.get(5).getText().toString()));
        info.setChest(Float.parseFloat(textViews.get(6).getText().toString()));
        info.setWaist(Float.parseFloat(textViews.get(7).getText().toString()));
        info.setHips(Float.parseFloat(textViews.get(8).getText().toString()));
        info.setBiceps(Float.parseFloat(textViews.get(9).getText().toString()));
        info.setForearms(Float.parseFloat(textViews.get(10).getText().toString()));
        info.setWrist(Float.parseFloat(textViews.get(11).getText().toString()));
        info.setThighs(Float.parseFloat(textViews.get(12).getText().toString()));
        info.setCalves(Float.parseFloat(textViews.get(13).getText().toString()));

        // Get the measurement types from the user inputted information
        Spinner temp = (Spinner)findViewById(R.id.measurementTypeSpinner);
        info.setGeneralMeasurement(temp.getSelectedItem().toString());
        temp = (Spinner)findViewById(R.id.measurementTypeSpinner2);
        info.setUpperMeasurement(temp.getSelectedItem().toString());
        temp = (Spinner)findViewById(R.id.measurementTypeSpinner3);
        info.setArms_measurement(temp.getSelectedItem().toString());
        temp = (Spinner)findViewById(R.id.measurementTypeSpinner4);
        info.setLegs_measurement(temp.getSelectedItem().toString());

        // Return the user information out of the function
        return info;
    }

    /**
     * Function to return all of the textViews in an arrayList for easy use.
     */
    private ArrayList<TextView> getTextViews() {
        ArrayList<TextView> texts = new ArrayList<TextView>();
        texts.add((TextView)findViewById(R.id.dateInput));
        texts.add((TextView)findViewById(R.id.heightInput1));
        texts.add((TextView)findViewById(R.id.heightInput2));
        texts.add((TextView)findViewById(R.id.weightInput));
        texts.add((TextView)findViewById(R.id.bmiInput));
        texts.add((TextView)findViewById(R.id.neckInput));
        texts.add((TextView)findViewById(R.id.chestSize));
        texts.add((TextView)findViewById(R.id.waistSize));
        texts.add((TextView)findViewById(R.id.hipSize));
        texts.add((TextView)findViewById(R.id.bicepSize));
        texts.add((TextView)findViewById(R.id.forearmSize));
        texts.add((TextView)findViewById(R.id.wristSize));
        texts.add((TextView)findViewById(R.id.thighSize));
        texts.add((TextView)findViewById(R.id.calvesSize));
        return texts;
    }

    /**
     * Function for the date selection, to automatically select the date for the current day.
     */
    private void setDate() {
        eText = (EditText) findViewById(R.id.dateInput);
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
                picker = new DatePickerDialog(NewMeasurementEnter.this, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
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
     * Function to check the values inputted have been inputted.
     */
    private boolean checkInput() {
        TextView height1 = (TextView)findViewById(R.id.heightInput1);
        TextView height2 = (TextView)findViewById(R.id.heightInput2);
        TextView weight = (TextView)findViewById(R.id.weightInput);
        try {
            Float h1 = Float.parseFloat(height1.getText().toString());
            Float h2 = Float.parseFloat(height2.getText().toString());
            Float w = Float.parseFloat(weight.getText().toString());
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Height and Weight Required", Toast.LENGTH_LONG).show();
            return false;
        }
        return checkBodyInput();
    }

    /**
     * Function to make sure information for the upper body has been entered.
     */
    private boolean checkBodyInput() {
        TextView neck = (TextView)findViewById(R.id.neckInput);
        TextView chest = (TextView)findViewById(R.id.chestSize);
        TextView waist = (TextView)findViewById(R.id.waistSize);
        TextView hips = (TextView)findViewById(R.id.hipSize);
        try {
            Float.parseFloat(neck.getText().toString());
            Float.parseFloat(chest.getText().toString());
            Float.parseFloat(waist.getText().toString());
            Float.parseFloat(hips.getText().toString());
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Upper Body Must Not Be Empty, Enter 0 If Not Needed", Toast.LENGTH_LONG).show();
            return false;
        }
        return checkArmsInput();
    }

    /**
     * Function to make sure information for the arms has been entered.
     */
    private boolean checkArmsInput() {
        TextView bicep = (TextView)findViewById(R.id.bicepSize);
        TextView forearm = (TextView)findViewById(R.id.forearmSize);
        TextView wrist = (TextView)findViewById(R.id.wristSize);
        try {
            Float.parseFloat(bicep.getText().toString());
            Float.parseFloat(forearm.getText().toString());
            Float.parseFloat(wrist.getText().toString());
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Arms Must Not Be Empty, Enter 0 If Not Needed", Toast.LENGTH_LONG).show();
            return false;
        }
        return checkLegsInput();
    }

    /**
     * Function to make sure information for the legs has been entered.
     */
    private boolean checkLegsInput() {
        TextView thigh = (TextView)findViewById(R.id.thighSize);
        TextView calves = (TextView)findViewById(R.id.calvesSize);
        try {
            Float.parseFloat(thigh.getText().toString());
            Float.parseFloat(calves.getText().toString());
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Legs Must Not Be Empty, Enter 0 If Not Needed", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
