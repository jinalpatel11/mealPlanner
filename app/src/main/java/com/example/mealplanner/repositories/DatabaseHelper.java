package com.example.mealplanner.repositories;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mealplanner.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user_db";
    private static final int DATABASE_VERSION = 4;

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

    private static final String COLUMN_SEX = "sex";

    // Create table query
    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_EMAIL + " TEXT PRIMARY KEY," +
                    COLUMN_USERNAME + " TEXT," +
                    COLUMN_PASSWORD + " TEXT," +
                    COLUMN_PROFILE_PHOTO + " TEXT," +
                    COLUMN_FIRST_NAME + " TEXT," +
                    COLUMN_LAST_NAME + " TEXT," +
                    COLUMN_BIRTHDAY + " TEXT," +
                    COLUMN_HEIGHT + " TEXT," +
                    COLUMN_WEIGHT + " TEXT," +
                    COLUMN_SEX + " TEXT," +
                    COLUMN_ACTIVITY_LEVEL + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_USERS);
        } catch (SQLException e) {
            // Handle database creation error
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(db);
        } catch (SQLException e) {
            // Handle database upgrade error
            e.printStackTrace();
        }
    }

    // Method to add a user to the database
    public long addUser(User user) {

        try ( SQLiteDatabase db = this.getWritableDatabase();) {
            // Check if the email already exists
            if (!isEmailExist( user.getEmail())) {
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
                values.put(COLUMN_SEX, user.getSex());
                long result = db.insert(TABLE_USERS, null, values);
                return result;
            } else {
                // Return -1 indicating the user with that email already exists
                return -1;
            }
        } catch (SQLException e) {
            // Handle database error
            e.printStackTrace();
            return -1;
        }
    }

    // Method to check if email already exists
    public boolean isEmailExist( String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.query(TABLE_USERS, null, COLUMN_EMAIL + "=?",
                new String[]{email}, null, null, null)) {
            return cursor != null && cursor.getCount() > 0;
        } catch (SQLException e) {
            // Handle database error
            e.printStackTrace();
            return false;
        }
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
                    case COLUMN_SEX:
                        user.setSex(cursor.getString(i));
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
    public boolean isPasswordCorrect(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null,
                COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{email, password}, null, null, null);
        boolean result = cursor != null && cursor.getCount() > 0;
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return result;
    }


    // Method to update a user in the database
    public int updateUser(User updatedUser) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, updatedUser.getUsername());
        values.put(COLUMN_PASSWORD, updatedUser.getPassword());
        values.put(COLUMN_PROFILE_PHOTO, updatedUser.getProfilePhoto());
        values.put(COLUMN_FIRST_NAME, updatedUser.getFirstName());
        values.put(COLUMN_LAST_NAME, updatedUser.getLastName());
        values.put(COLUMN_BIRTHDAY, updatedUser.getBirthdate());
        values.put(COLUMN_HEIGHT, updatedUser.getHeight());
        values.put(COLUMN_WEIGHT, updatedUser.getWeight());
        values.put(COLUMN_SEX, updatedUser.getSex());
        values.put(COLUMN_ACTIVITY_LEVEL, updatedUser.getActivityLevel());

        int rowsAffected = db.update(TABLE_USERS, values,
                COLUMN_EMAIL + "=?",
                new String[]{updatedUser.getEmail()});

        db.close();

        return rowsAffected;
    }
}
