package com.example.williammontiel.willmontiel.schemas;

import android.provider.BaseColumns;

import com.example.williammontiel.willmontiel.misc.JsonKeys;

/**
 * Created by william.montiel on 10/02/2017.
 */

public class Schemas {

    public static abstract class DbEntry {
        public static final String DB_NAME = JsonKeys.DB_NAME;
    }

    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = JsonKeys.USER_TABLE_NAME;
        public static final String EMAIL = JsonKeys.USER_EMAIL;
        public static final String PASSWORD = JsonKeys.USER_PASSWORD;
    }
}