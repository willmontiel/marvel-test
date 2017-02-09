package com.example.williammontiel.willmontiel.deserializers;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by william.montiel on 09/02/2017.
 */

public class DeserializerValidator {
    public String validateString(String key, JSONObject jsonObject) {
        try {
            String value = jsonObject.getString(key).trim();
            if (!value.isEmpty() && value != null && value != "null" && value != "" && value != "null") {
                return value;
            }
            return "";
        }
        catch(JSONException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public double validateDouble(String key, JSONObject jsonObject) {
        try {
            String value = jsonObject.getString(key).trim();
            if (!value.isEmpty() && value != null && value != "null" && value != "" && value != "null") {
                return Double.parseDouble(value);
            }
            return 0;
        }
        catch(JSONException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public int validateInt(String key, JSONObject jsonObject) {
        try {
            String value = jsonObject.getString(key).trim();
            if (!value.isEmpty() && value != null && value != "null" && value != "" && value != "null") {
                return Integer.parseInt(value);
            }
            return 0;
        }
        catch(JSONException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
