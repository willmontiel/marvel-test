package com.example.williammontiel.willmontiel.schemas;

import android.provider.BaseColumns;

import com.example.williammontiel.willmontiel.misc.JsonKeys;

/**
 * Created by william.montiel on 10/02/2017.
 */

public class User {

    public static abstract class LawyerEntry implements BaseColumns {
        public static final String TABLE_NAME = JsonKeys.USER_TABLE_NAME;
        public static final String IDUSER = JsonKeys.USER_ID;
        public static final String EMAIL = JsonKeys.USER_EMAIL;
        public static final String PASSWORD = JsonKeys.USER_PASSWORD;
    }
}