package com.example.williammontiel.willmontiel.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.williammontiel.willmontiel.models.User;
import com.example.williammontiel.willmontiel.schemas.Schemas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william.montiel on 11/02/2017.
*/

 public class UserDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = Schemas.DbEntry.DB_NAME;

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("CREATE TABLE " + Schemas.UserEntry.TABLE_NAME + " ("
                    + Schemas.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Schemas.UserEntry.EMAIL + " TEXT NOT NULL,"
                    + Schemas.UserEntry.PASSWORD + " TEXT NOT NULL)");

        }
        catch (SQLException	ex) {
            Log.d("LALA", ex.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public long saveUser(User user) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();

            return sqLiteDatabase.insert(
                    Schemas.UserEntry.TABLE_NAME,
                    null,
                    user.toContentValues());
        }
        catch (SQLException	ex) {
            Log.d("LALA", ex.toString());
            return -1;
        }

    }

    public User getUser(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + Schemas.UserEntry.TABLE_NAME + " WHERE email = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{email});

        if (cursor.getCount() <= 0) {
            return null;
        }

        User user = new User();
        cursor.moveToFirst();
        user.setEmail(cursor.getString(cursor.getColumnIndex(Schemas.UserEntry.EMAIL)));

        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                Schemas.UserEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);


        if (cursor.getCount() <= 0) {
            return users;
        }

        Log.d("LALA", "" + cursor.getCount());

        while (cursor.moveToNext()) {
            User user = new User();
            user.setEmail(cursor.getString(cursor.getColumnIndex(Schemas.UserEntry.EMAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(Schemas.UserEntry.PASSWORD)));
            users.add(user);
        }

        return users;
    }

    public User validateLogin(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + Schemas.UserEntry.TABLE_NAME + " WHERE email = ? AND password = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{email, password});

        if (cursor.getCount() <= 0) {
            return null;
        }

        User user = new User();
        cursor.moveToFirst();
        user.setEmail(cursor.getString(cursor.getColumnIndex(Schemas.UserEntry.EMAIL)));

        return user;
    }
 }