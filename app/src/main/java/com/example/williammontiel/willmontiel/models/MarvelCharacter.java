package com.example.williammontiel.willmontiel.models;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by william.montiel on 09/02/2017.
 */

public class MarvelCharacter {
    public int id;
    public String name;
    public String description;
    public JSONObject thumbnail;
    public int totalComics;
    public List<JSONObject> comics;
    public int totalSeries;
    public List<JSONObject> series;
    public int totalStories;
    public List<JSONObject> stories;
    public List<JSONObject> urls;

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

    public List<JSONObject> getSeries() {
        return series;
    }

    public void setSeries(List<JSONObject> series) {
        this.series = series;
    }

    public List<JSONObject> getStories() {
        return stories;
    }

    public void setStories(List<JSONObject> stories) {
        this.stories = stories;
    }

    public List<JSONObject> getUrls() {
        return urls;
    }

    public void setUrls(List<JSONObject> urls) {
        this.urls = urls;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalComics() {
        return totalComics;
    }

    public void setTotalComics(int totalComics) {
        this.totalComics = totalComics;
    }

    public int getTotalSeries() {
        return totalSeries;
    }

    public void setTotalSeries(int totalSeries) {
        this.totalSeries = totalSeries;
    }

    public int getTotalStories() {
        return totalStories;
    }

    public void setTotalStories(int totalStories) {
        this.totalStories = totalStories;
    }
}
