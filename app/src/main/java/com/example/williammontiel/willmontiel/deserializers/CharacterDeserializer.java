package com.example.williammontiel.willmontiel.deserializers;

import com.example.williammontiel.willmontiel.misc.JsonKeys;
import com.example.williammontiel.willmontiel.models.MarvelCharacter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william.montiel on 09/02/2017.
 */

public class CharacterDeserializer extends DeserializerValidator{
    public JSONObject jsonObject = new JSONObject();
    public MarvelCharacter character = new MarvelCharacter();

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }


    public void deserialize() {
        try {
            int id = validateInt(JsonKeys.CHARACTER_ID, jsonObject);
            String name = validateString(JsonKeys.CHARACTER_NAME, jsonObject);
            JSONObject comicsObject = jsonObject.getJSONObject(JsonKeys.CHARACTER_COMICS_OBJECT);
            JSONArray comicsArray = comicsObject.getJSONArray(JsonKeys.CHARACTER_COMICS_ARRAY);

            List comics = new ArrayList();
            for (int i = 0; i < comicsArray.length(); i++) {
                comics.add(comicsArray.getJSONObject(i));
            }

            JSONObject thumbnail = jsonObject.getJSONObject(JsonKeys.CHARACTER_THUMBNAIL);

            character.setId(id);
            character.setName(name);
            character.setComics(comics);
            character.setThumbnail(thumbnail);
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }
    }

    public MarvelCharacter getCharacter() {
        return this.character;
    }
}

