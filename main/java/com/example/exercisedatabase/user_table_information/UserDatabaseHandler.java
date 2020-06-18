package com.example.exercisedatabase.user_table_information;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userManage";
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PO_NUM = "phone_number";
    private static final String KEY_BIRTH = "date_of_birth";
    private static final String KEY_GENDER = "gender";

    public UserDatabaseHandler(Context context) {
        //3rd argument is the CursorFactor instance
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Function to create a new database if one doesn't exist
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PO_NUM + " TEXT," + KEY_BIRTH + " TEXT," + KEY_GENDER + " Text" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    /**
     * Function to ungrade the table, if needed
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        //Create tables again
        onCreate(db);
    }

    /**
     * Function to add another user to the database.
     * @param user The user object to be added to the database
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.get_name());
        values.put(KEY_PO_NUM, user.get_phone_number());
        values.put(KEY_BIRTH, user.get_date_of_birth());
        values.put(KEY_GENDER, user.get_gender());

        //Inserting Row
        db.insert(TABLE_USERS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); //Close the database
    }

    /**
     * Function to return a user out of the database.
     * @param id The id of the user in the database
     * @return A user object containing the information from the database
     */
    User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] {KEY_ID, KEY_NAME, KEY_PO_NUM, KEY_BIRTH, KEY_GENDER}, KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return user;
    }

    /**
     * Function to return a list of all the users form the list
     * @return A List object containing all of the users from the database
     */
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        //Select all query
        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.set_id(Integer.parseInt(cursor.getString(0)));
                user.set_name(cursor.getString(1));
                user.set_phone_number(cursor.getString(2));
                user.set_date_of_birth(cursor.getString(3));
                user.set_gender(cursor.getString(4));
                // Adding contact to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // Return contact list
        return userList;
    }

    /**
     * Function to update the user information from the user.
     * @param user The new information for the user.
     * @return The id of the update in the database.
     */
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.get_name());
        values.put(KEY_PO_NUM, user.get_phone_number());
        values.put(KEY_BIRTH, user.get_date_of_birth());
        values.put(KEY_GENDER, user.get_gender());

        // Updating row
        return db.update(TABLE_USERS, values, KEY_ID + " = ?", new String[] {String.valueOf(user.get_id())});
    }

    /**
     * Function to delete a user.
     * @param id The user to be deleted from the database
     */
    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID +" =?", new String[] {String.valueOf(id)});
        db.close();
    }

    /**
     * Function to get the number of users in the database.
     * @return The number of users in the database
     */
    public int getUsersCount() {
        String countQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int numbers = cursor.getCount();
        cursor.close();

        // Return count
        return numbers;
    }
}
