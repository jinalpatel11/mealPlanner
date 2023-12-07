package com.example.mealplanner.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mealplanner.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user_db";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    private static final String COLUMN_PROFILE_PHOTO = "profile_photo";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_BIRTHDAY = "birthday";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_ACTIVITY_LEVEL = "activity_level";
    private static final String COLUMN_EMAIL = "email";

    // Create table query
    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_USERNAME + " TEXT PRIMARY KEY," +
                    COLUMN_PASSWORD + " TEXT," +
                    COLUMN_PROFILE_PHOTO + " TEXT," +
                    COLUMN_FIRST_NAME + " TEXT," +
                    COLUMN_LAST_NAME + " TEXT," +
                    COLUMN_BIRTHDAY + " TEXT," +
                    COLUMN_HEIGHT + " TEXT," +
                    COLUMN_WEIGHT + " TEXT," +
                    COLUMN_ACTIVITY_LEVEL + " TEXT," +
                    COLUMN_EMAIL + " TEXT)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if it exists and create new ones
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Method to add a user to the database
    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if the email already exists
        if (!isEmailExist(user.getEmail())) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, user.getUsername());
            values.put(COLUMN_PASSWORD, user.getPassword());
            values.put(COLUMN_PROFILE_PHOTO, user.getProfilePhoto());
            values.put(COLUMN_FIRST_NAME, user.getFirstName());
            values.put(COLUMN_LAST_NAME, user.getLastName());
            values.put(COLUMN_BIRTHDAY, user.getBirthdate());
            values.put(COLUMN_HEIGHT, user.getHeight());
            values.put(COLUMN_WEIGHT, user.getWeight());
            values.put(COLUMN_ACTIVITY_LEVEL, user.getActivityLevel());
            values.put(COLUMN_EMAIL, user.getEmail());

            long result = db.insert(TABLE_USERS, null, values);
            db.close();
            return result;
        } else {
            // Return -1 indicating the user with that email already exists
            db.close();
            return -1;
        }
    }

    // Method to check if email already exists
    public boolean isEmailExist(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_EMAIL + "=?",
                new String[]{email}, null, null, null);

        boolean result = cursor != null && cursor.getCount() > 0;

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return result;
    }


    // Method to get a user from the database
    public User getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_EMAIL + "=?",
                new String[]{email}, null, null, null);
        User user = null;

        if (cursor != null && cursor.moveToFirst()) {
            user = new User();

            for (int i = 0; i < cursor.getColumnCount(); i++) {
                String columnName = cursor.getColumnName(i);
                switch (columnName) {
                    case COLUMN_USERNAME:
                        user.setUsername(cursor.getString(i));
                        break;
                    case COLUMN_PASSWORD:
                        user.setPassword(cursor.getString(i));
                        break;
                    case COLUMN_PROFILE_PHOTO:
                        user.setProfilePhoto(cursor.getString(i));
                        break;
                    case COLUMN_FIRST_NAME:
                        user.setFirstName(cursor.getString(i));
                        break;
                    case COLUMN_LAST_NAME:
                        user.setLastName(cursor.getString(i));
                        break;
                    case COLUMN_BIRTHDAY:
                        user.setBirthdate(cursor.getString(i));
                        break;
                    case COLUMN_HEIGHT:
                        user.setHeight(cursor.getString(i));
                        break;
                    case COLUMN_WEIGHT:
                        user.setWeight(cursor.getString(i));
                        break;
                    case COLUMN_ACTIVITY_LEVEL:
                        user.setActivityLevel(cursor.getString(i));
                        break;
                    case COLUMN_EMAIL:
                        user.setEmail(cursor.getString(i));
                        break;
                    // Add cases for other columns as needed
                }
            }

            cursor.close();
        }
        db.close();
        return user;
    }


    // Method to check if the entered password is correct for a user
    public boolean isPasswordCorrect(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null,
                COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{username, password}, null, null, null);
        boolean result = cursor != null && cursor.getCount() > 0;
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return result;
    }
}
