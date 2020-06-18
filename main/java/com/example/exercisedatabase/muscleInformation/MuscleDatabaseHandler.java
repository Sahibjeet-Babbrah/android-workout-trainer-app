package com.example.exercisedatabase.muscleInformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MuscleDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "muscleMeasurements";
    private static final String TABLE_MEASUREMENTS = "measurements";
    private static final String KEY_EXERSISE_NAME = "exersise";
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_HEIGHT_2 = "height2";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_BMI = "bmi";
    private static final String KEY_NECK = "neck";
    private static final String KEY_BICEP = "bicep";
    private static final String KEY_FOREARM = "forearm";
    private static final String KEY_WRIST = "wrist";
    private static final String KEY_CHEST = "chest";
    private static final String KEY_WAIST = "waist";
    private static final String KEY_HIPS = "hips";
    private static final String KEY_THIGHS = "thighs";
    private static final String KEY_CALVES = "calves";
    private static final String KEY_GENERAL_MEASUREMENT = "general_measurements";
    private static final String KEY_UPPER_MEASRUEMENT = "upper_measurements";
    private static final String KEY_ARMS_MEASUREMENT = "arms_measurements";
    private static final String KEY_LEGS_MEASUREMENT = "legs_measurements";

    public MuscleDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEASUREMENTS_TABLE = "CREATE TABLE " + TABLE_MEASUREMENTS + "(" + KEY_ID + " INTEGER," + KEY_DATE + " TEXT," + KEY_EXERSISE_NAME + " TEXT," + KEY_HEIGHT + " FLOAT," + KEY_HEIGHT_2 + " FLOAT," + KEY_WEIGHT + " FLOAT," + KEY_BMI + " FLOAT," +
                KEY_NECK + " FLOAT," + KEY_BICEP + " FLOAT," + KEY_FOREARM + " FLOAT," + KEY_WRIST + " FLOAT," + KEY_CHEST + " FLOAT," + KEY_WAIST + " FLOAT," + KEY_HIPS + " FLOAT," + KEY_THIGHS + " FLOAT," + KEY_CALVES + " FLOAT," +
                KEY_GENERAL_MEASUREMENT + " TEXT," + KEY_UPPER_MEASRUEMENT + " TEXT," + KEY_ARMS_MEASUREMENT + " TEXT," + KEY_LEGS_MEASUREMENT + " Text" + ");";
        db.execSQL(CREATE_MEASUREMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEASUREMENTS);

        onCreate(db);
    }

    public void addMeasurements(muscleInformation measurements) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, measurements.getId());
        values.put(KEY_DATE, measurements.getDate());
        values.put(KEY_EXERSISE_NAME, measurements.getMeasurement());
        values.put(KEY_HEIGHT, measurements.getHeight());
        values.put(KEY_HEIGHT_2, measurements.getHeight2());
        values.put(KEY_WEIGHT, measurements.getWeight());
        values.put(KEY_BMI, measurements.getBMI());
        values.put(KEY_NECK, measurements.getNeck());
        values.put(KEY_BICEP, measurements.getBiceps());
        values.put(KEY_FOREARM, measurements.getForearms());
        values.put(KEY_WRIST, measurements.getWrist());
        values.put(KEY_CHEST, measurements.getChest());
        values.put(KEY_WAIST, measurements.getWaist());
        values.put(KEY_HIPS, measurements.getHips());
        values.put(KEY_THIGHS, measurements.getThighs());
        values.put(KEY_CALVES, measurements.getCalves());
        values.put(KEY_GENERAL_MEASUREMENT, measurements.getGeneral_measurement());
        values.put(KEY_UPPER_MEASRUEMENT, measurements.getUpper_measurement());
        values.put(KEY_ARMS_MEASUREMENT, measurements.getArms_measurement());
        values.put(KEY_LEGS_MEASUREMENT, measurements.getLegs_measurement());

        //Inserting rows
        db.insert(TABLE_MEASUREMENTS, null, values);

        db.close();
    }

    /**
     * Function to return a list of all the previous versions measurements.
     * @param id The user for the data to be getting.
     */
    public List<muscleInformation> getMeasurementData(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<muscleInformation> fullList = new ArrayList<muscleInformation>();

        // Create the lists to extract the information form the list
        String columns[] = {KEY_ID, KEY_DATE,KEY_EXERSISE_NAME,KEY_HEIGHT, KEY_HEIGHT_2, KEY_WEIGHT, KEY_BMI, KEY_NECK, KEY_BICEP, KEY_FOREARM, KEY_WRIST, KEY_CHEST, KEY_WAIST, KEY_HIPS, KEY_THIGHS, KEY_CALVES, KEY_GENERAL_MEASUREMENT, KEY_UPPER_MEASRUEMENT, KEY_ARMS_MEASUREMENT, KEY_LEGS_MEASUREMENT};
        String search[] = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_MEASUREMENTS, columns, KEY_ID + "=?", search, null, null, null, null);
        // Search through the returned data and create a list of information
        if (cursor.moveToFirst()) {
            do {
                muscleInformation measure = new muscleInformation();
                measure.setId(Integer.parseInt(cursor.getString(0)));
                measure.setDate(cursor.getString(1));
                measure.setMeasurement(cursor.getString(2));
                measure.setHeight(Float.parseFloat(cursor.getString(3)), Float.parseFloat(cursor.getString(4)));
                measure.setWeight(Float.parseFloat(cursor.getString(5)));
                measure.setBMI(Float.parseFloat(cursor.getString(6)));
                measure.setNeck(Float.parseFloat(cursor.getString(7)));
                measure.setBiceps(Float.parseFloat(cursor.getString(8)));
                measure.setForearms(Float.parseFloat(cursor.getString(9)));
                measure.setWrist(Float.parseFloat(cursor.getString(10)));
                measure.setChest(Float.parseFloat(cursor.getString(11)));
                measure.setWaist(Float.parseFloat(cursor.getString(12)));
                measure.setHips(Float.parseFloat(cursor.getString(13)));
                measure.setThighs(Float.parseFloat(cursor.getString(14)));
                measure.setCalves(Float.parseFloat(cursor.getString(15)));
                measure.setGeneralMeasurement(cursor.getString(16));
                measure.setUpperMeasurement(cursor.getString(17));
                measure.setArms_measurement(cursor.getString(18));
                measure.setLegs_measurement(cursor.getString(19));

                // Add the information to the list
                fullList.add(measure);
            }while(cursor.moveToNext());
        }
        db.close();
        //Return the list
        return fullList;
    }

    /**
     * Function function to delete all user information from the data table user their userID.
     * @param userID The user id of the user to be deleted.
     */
    public void deleteUserFromList(Integer userID) {
        // Open up the database
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete all of the information from the database table
        db.execSQL("DELETE FROM " + TABLE_MEASUREMENTS + " WHERE " + KEY_ID + " = " + userID.toString() + ";");
        // Close the database
        db.close();
    }

    /**
     * Function to delete a specific exercise from the user.
     * @param userID The user id from which the exercise is deleted from.
     * @param exersiseName The exercise to be deleted from the list.
     */
    public void deleteExersiseFromUser(Integer userID, String exersiseName) {
        // Open up the database
        SQLiteDatabase db = this.getWritableDatabase();
        //Delete the information from the database table
        db.execSQL("DELETE FROM " + TABLE_MEASUREMENTS + " WHERE " + KEY_ID + " = " + userID.toString() + " AND " + KEY_EXERSISE_NAME + " = " + exersiseName + ";");
        // Close the database
        db.close();
    }
}
