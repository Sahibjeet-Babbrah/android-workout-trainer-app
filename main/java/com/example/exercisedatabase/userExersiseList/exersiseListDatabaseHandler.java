package com.example.exercisedatabase.userExersiseList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;

//import com.example.exercisedatabase.muscleInformation.MuscleDatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class exersiseListDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "exersiseList";
    private static final String TABLE_INFO = "exerInfo";
    private static final String KEY_ID = "_ID";
    private static final String KEY_GROUP = "EXERSISE_GROUP";
    private static final String KEY_EXERSISE_NAME = "EXERSISE_NAME";
    private static final String KEY_DESCRIPTION = "DESCRIPTION";
    private static final String KEY_EXERSISE_TYPE = "EXERSISE_TYPE"; // Stores if the exersise is a weight, endurace, etc. style of activity, used for the type of logsheet created for that exersise.
    private static final String KEY_PHOTO_LOCATION = "PHOTO_LOCATION";
    private static final String KEY_PHOTO_NAME = "PHOTO_NAME";

    public exersiseListDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_INFO + "(" + KEY_ID + " INTEGER," + KEY_GROUP + " TEXT," + KEY_EXERSISE_NAME + " TEXT," + KEY_EXERSISE_TYPE + " TEXT," + KEY_DESCRIPTION + " Text," +
                KEY_PHOTO_LOCATION + " TEXT," + KEY_PHOTO_NAME + " TEXT" + ");";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFO);

        onCreate(db);
    }

    /**
     * Funtion to add a new exersise to the table.
     * @param newExec The new exersise to be added to the list
     */
    public void addExersise(exersiseList newExec) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(KEY_ID, newExec.getUserID());
        content.put(KEY_GROUP, newExec.getGroup());
        content.put(KEY_EXERSISE_NAME, newExec.getExersiseName());
        content.put(KEY_EXERSISE_TYPE, newExec.getExersiseType());
        content.put(KEY_DESCRIPTION, newExec.getExersiseDescription());
        content.put(KEY_PHOTO_LOCATION, newExec.getPhotoLocation());
        content.put(KEY_PHOTO_NAME, newExec.getPhotoName());

        // Place the information in the table
        db.insert(TABLE_INFO, null, content);
        // Close the database
        db.close();
    }

    /**
     * Function to return all the exersises connected to the user by the userID.
     * @param userID The integer representing the user id.
     * @return A list of all the exersices belonging to a user.
     */
    public List<exersiseList> getUserExersises(Integer userID, String exersiseType) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<exersiseList> list = new ArrayList<exersiseList>();

        String columns[] = {KEY_ID, KEY_GROUP, KEY_EXERSISE_NAME, KEY_EXERSISE_TYPE, KEY_DESCRIPTION, KEY_PHOTO_LOCATION, KEY_PHOTO_NAME};
        String search[] = {String.valueOf(userID), exersiseType};
        Cursor cursor = db.query(TABLE_INFO, columns, KEY_ID + "=? AND " + KEY_GROUP + "=?", search, null, null, null, null);
        // Parse the returned data and place it into a List
        if (cursor.moveToFirst()) {
            do {
                exersiseList data = new exersiseList(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
                data.setExersiseName(cursor.getString(2));
                data.setExersiseType(cursor.getString(3));
                data.setExersiseDescription(cursor.getString(4));
                data.setPhotoLocation(cursor.getString(5));
                data.setPhotoName(cursor.getString(6));

                list.add(data);
            } while(cursor.moveToNext());
        }
        db.close();
        // Return the list
        return list;
    }

    /**
     * Function to delete all the user information from the database.
     * @param userID User ID from which the information will be deleted from
     */
    public void deleteUserFromList(Integer userID) {
        // Open up the database
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete all the information from the user
        db.execSQL("DELETE FROM " + TABLE_INFO + " WHERE " + KEY_ID + " = " + userID.toString() + ";");
        // Close the database
        db.close();
    }

    /**
     * Function to delete an exersise from a user.
     * @param userID The user id from which the exersise to be deleted.
     * @param exerciseName The exercise to be deleted from the list.
     */
    public void deleteExcersiseFromUser(Integer userID, String exerciseName) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_INFO + " WHERE " + KEY_ID + " = " + userID.toString() + " AND " + KEY_EXERSISE_NAME + " = " + exerciseName + ";");

        db.close();
     }
}