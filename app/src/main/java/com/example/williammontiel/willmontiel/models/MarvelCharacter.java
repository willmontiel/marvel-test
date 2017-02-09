package com.example.williammontiel.willmontiel.models;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by william.montiel on 09/02/2017.
 */

public class MarvelCharacter {
    public int id;
    public String name;
    public List<JSONObject> comics;
    public JSONObject thumbnail;

    public MarvelCharacter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JSONObject> getComics() {
        return comics;
    }

    public void setComics(List<JSONObject> comics) {
        this.comics = comics;
    }

    public JSONObject getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(JSONObject thumbnail) {
        this.thumbnail = thumbnail;
    }
}
