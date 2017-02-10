package com.example.williammontiel.willmontiel.deserializers;

import android.util.Log;

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
    public MarvelCharacter character;

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }


    public void deserialize() {
        try {
            int id = validateInt(JsonKeys.CHARACTER_ID, jsonObject);
            String name = validateString(JsonKeys.CHARACTER_NAME, jsonObject);
            String description = validateString(JsonKeys.CHARACTER_DESCRIPTION, jsonObject);

            JSONObject comicsObject = jsonObject.getJSONObject(JsonKeys.CHARACTER_COMICS_OBJECT);
            int totalComics = comicsObject.getInt(JsonKeys.CHARACTER_TOTAL_COMICS);
            JSONArray comicsArray = comicsObject.getJSONArray(JsonKeys.CHARACTER_COMICS_ARRAY);

            JSONObject seriesObject = jsonObject.getJSONObject(JsonKeys.CHARACTER_SERIES_OBJECT);
            int totalSeries = seriesObject.getInt(JsonKeys.CHARACTER_TOTAL_SERIES);
            JSONArray seriesArray = seriesObject.getJSONArray(JsonKeys.CHARACTER_SERIES_ARRAY);

            JSONObject storiesObject = jsonObject.getJSONObject(JsonKeys.CHARACTER_STORIES_OBJECT);
            int totalStories = storiesObject.getInt(JsonKeys.CHARACTER_TOTAL_STORIES);
            JSONArray storiesArray = storiesObject.getJSONArray(JsonKeys.CHARACTER_STORIES_ARRAY);

            JSONArray urlsArray = jsonObject.getJSONArray(JsonKeys.CHARACTER_URLS_ARRAY);

            List comics = new ArrayList();
            List series = new ArrayList();
            List stories = new ArrayList();
            List<JSONObject> urls = new ArrayList<JSONObject>();

            for (int i = 0; i < comicsArray.length(); i++) {
                comics.add(comicsArray.getJSONObject(i));
            }

            for (int i = 0; i < seriesArray.length(); i++) {
                series.add(seriesArray.getJSONObject(i));
            }

            for (int i = 0; i < storiesArray.length(); i++) {
                stories.add(storiesArray.getJSONObject(i));
            }

            for (int i = 0; i < urlsArray.length(); i++) {
                urls.add(urlsArray.getJSONObject(i));
            }

            JSONObject thumbnail = jsonObject.getJSONObject(JsonKeys.CHARACTER_THUMBNAIL);

            character = new MarvelCharacter();
            character.setId(id);
            character.setName(name);
            character.setDescription(description);
            character.setTotalComics(totalComics);
            character.setComics(comics);
            character.setTotalSeries(totalSeries);
            character.setSeries(series);
            character.setTotalStories(totalStories);
            character.setStories(stories);
            character.setUrls(urls);
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

