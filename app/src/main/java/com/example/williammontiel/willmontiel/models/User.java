package com.example.williammontiel.willmontiel.models;

import android.content.ContentValues;

import com.example.williammontiel.willmontiel.schemas.Schemas;

/**
 * Created by william.montiel on 10/02/2017.
 */

public class User {
    public String email;
    public String password;

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(Schemas.UserEntry.EMAIL, this.email);
        values.put(Schemas.UserEntry.PASSWORD, this.password);

        return values;
    }
}
