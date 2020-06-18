package com.example.exercisedatabase.userExersiseList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class exersiseLogDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSTION = 2;
    private static final String DATABASE_NAME = "EXERISE_LOG_SHEET";

    private static final String WEIGHT_TABLE_NAME = "Log_Sheet_Weight";
    private static final String WEIGHT_ID = "_ID";
    private static final String WEIGHT_EXERSISE = "EXERSISE_NAME";
    private static final String WEIGHT_EXER_GROUP = "EXERSISE_GROUP";
    private static final String WEIGHT_DATE = "WEIGHT_DATE";
    private static final String WEIGHT_TYPE = "WEIGHT_TYPE";
    private static final String WEIGHT_WEIGHT = "WEIGHT";
    private static final String WEIGHT_SETS = "SETS";
    private static final String WEIGHT_REPS = "REPS";
    private static final String WEIGHT_REST = "RESTS";

    private static final String ENDURANCE_TABLE_NALE = "Log_Sheet_Endurance";
    private static final String ENDURANCE_ID = "_ID";
    private static final String ENDURANCE_DATE = "ENDUR_DATE";
    private static final String ENDURANCE_EXERSISE = "ENDUR_EXERSISE_NAME";
    private static final String ENDURANCE_GROUP = "EXERSISE_GROUP";
    private static final String ENDURANCE_TIME_MIN = "TIME_MIN";
    private static final String ENDURANCE_TIME_SEC = "TIME_SEC";
    private static final String ENDURANCE_DISTANCE = "DISTANCE";
    private static final String ENDURANCE_INTENSITY = "INTENSITY";
    private static final String ENDURANCE_TYPE = "ENDURANCE_TYPE";


    public exersiseLogDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSTION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WEIGHT_TABLE = "CREATE TABLE " + WEIGHT_TABLE_NAME + "(" + WEIGHT_ID + " INTEGER," + WEIGHT_EXERSISE + " TEXT," + WEIGHT_EXER_GROUP + " TEXT," + WEIGHT_DATE + " TEXT," +
                WEIGHT_TYPE + " TEXT," + WEIGHT_WEIGHT + " FLOAT," + WEIGHT_SETS + " INTEGER," + WEIGHT_REPS + " INTEGER," + WEIGHT_REST + " INTEGER" + ")";

        String CREATE_ENDURANCE_TABLE = "CREATE TABLE " + ENDURANCE_TABLE_NALE + "(" + ENDURANCE_ID + " INTEGER," + ENDURANCE_EXERSISE + " TEXT," + ENDURANCE_GROUP + " TEXT," +
                ENDURANCE_DATE + " TEXT," + ENDURANCE_TIME_MIN + " FLOAT," + ENDURANCE_TIME_SEC + " FLOAT," + ENDURANCE_DISTANCE + " FLOAT," +
                ENDURANCE_TYPE + " TEXT," + ENDURANCE_INTENSITY + " TEXT" + ")";

        db.execSQL(CREATE_WEIGHT_TABLE);
        db.execSQL(CREATE_ENDURANCE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WEIGHT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ENDURANCE_TABLE_NALE);
        onCreate(db);
    }

    /**
     * Function to add a new log to the weight class
     */
    public void addWeightLog(weightLog log) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WEIGHT_ID, log.getID());
        values.put(WEIGHT_EXERSISE, log.getExersiseName());
        values.put(WEIGHT_EXER_GROUP, log.getExersiseGroup());
        values.put(WEIGHT_DATE, log.getDate());
        values.put(WEIGHT_TYPE, log.getWeightType());
        values.put(WEIGHT_WEIGHT, log.getWeight());
        values.put(WEIGHT_SETS, log.getSets());
        values.put(WEIGHT_REPS, log.getReps());
        values.put(WEIGHT_REST, log.getRests());

        db.insert(WEIGHT_TABLE_NAME,null, values);
        db.close();
    }

    /**
     * Function to get the logs for the previous exercises.
     */
    public List<weightLog> getWeightExercise(int _ID, String exerciseGroup, String exerciseName) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<weightLog> list = new ArrayList<weightLog>();

        String[] columns = {WEIGHT_ID, WEIGHT_EXERSISE, WEIGHT_EXER_GROUP, WEIGHT_DATE, WEIGHT_TYPE, WEIGHT_WEIGHT, WEIGHT_SETS, WEIGHT_REPS, WEIGHT_REST};
        String[] search = {String.valueOf(_ID), exerciseGroup, exerciseName};
        Cursor cursor = db.query(WEIGHT_TABLE_NAME, columns, WEIGHT_ID + " =? AND " + WEIGHT_EXER_GROUP + " =? AND " + WEIGHT_EXERSISE + " =?", search, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                weightLog log = new weightLog(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
                log.setDate(cursor.getString(3));
                log.setWeightType(cursor.getString(4));
                log.setWeight(Float.parseFloat(cursor.getString(5)));
                log.setSets(Integer.parseInt(cursor.getString(6)));
                log.setReps(Integer.parseInt(cursor.getString(7)));
                log.setRests(Integer.parseInt(cursor.getString(8)));

                list.add(log);
            } while(cursor.moveToNext());
        }

        db.close();
        return list;
    }

    public void deleteExerciseFromWeightList(Integer _ID, String exerciseGroup, String exerciseName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + WEIGHT_TABLE_NAME + " WHERE " + WEIGHT_ID + " = " + _ID.toString() + " AND " + WEIGHT_EXER_GROUP + " = " + exerciseGroup + " AND " + WEIGHT_EXERSISE + " = " + exerciseName + ";");
        db.close();
    }

    /**
     * Function to add a new log to the endurance exercise
     */
    public void addEnduranceLog(enduranceLog log) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ENDURANCE_ID, log.getID());
        values.put(ENDURANCE_EXERSISE, log.getExersiseName());
        values.put(ENDURANCE_GROUP, log.getExersiseGroup());
        values.put(ENDURANCE_DATE, log.getDate());
        values.put(ENDURANCE_TIME_MIN, log.getTimeMinutes());
        values.put(ENDURANCE_TIME_SEC, log.getTimeSeconds());
        values.put(ENDURANCE_DISTANCE, log.getDistance());
        values.put(ENDURANCE_TYPE, log.getEndurance_type());
        values.put(ENDURANCE_INTENSITY, log.getIntensity());

        db.insert(ENDURANCE_TABLE_NALE, null, values);
        db.close();
    }

    /**
     * Function to return previouse endurance logs.
     */
    public List<enduranceLog> getEnduranceExercise(int _ID, String exerciseGroup, String exerciseName) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<enduranceLog> list = new ArrayList<enduranceLog>();

        String[] columns = {ENDURANCE_ID, ENDURANCE_EXERSISE, ENDURANCE_GROUP, ENDURANCE_DATE, ENDURANCE_TIME_MIN, ENDURANCE_TIME_SEC, ENDURANCE_DISTANCE, ENDURANCE_TYPE, ENDURANCE_INTENSITY};
        String[] search = {String.valueOf(_ID), exerciseName, exerciseGroup};
        Cursor cursor = db.query(ENDURANCE_TABLE_NALE, columns, ENDURANCE_ID + " =? AND " + ENDURANCE_EXERSISE + " =? AND " + ENDURANCE_GROUP + " =?", search, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                enduranceLog log = new enduranceLog(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
                log.setDate(cursor.getString(3));
                log.setTimeMinutes(Float.parseFloat(cursor.getString(4)));
                log.setTimeSeconds(Float.parseFloat(cursor.getString(5)));
                log.setDistance(Float.parseFloat(cursor.getString(6)));
                log.setEndurance_type(cursor.getString(7));
                log.setIntensity(cursor.getString(8));

                list.add(log);
            } while(cursor.moveToNext());
        }

        db.close();
        return list;
    }

    /**
     * Deleting a specific exercise from the endurance list.
     */
    public void deleteExerciseFromEnduranceList(Integer _ID, String exerciseGroup, String exerciseName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + ENDURANCE_TABLE_NALE + " WHERE " + ENDURANCE_ID + " = " + _ID.toString() + " AND " + ENDURANCE_GROUP + " = " + exerciseGroup + " AND " + ENDURANCE_EXERSISE + " = " + exerciseName + ";");
        db.close();
    }

    /**
     * Function to delete a user from both tables.
     */
    public void deleteUser(Integer _ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + WEIGHT_TABLE_NAME + " WHERE " + WEIGHT_ID + " = " + _ID.toString());
        db.execSQL("DELETE FROM " + ENDURANCE_TABLE_NALE + " WHERE " + ENDURANCE_ID + " = " + _ID.toString());
        db.close();
    }
}
