package com.example.williammontiel.willmontiel.models;

/**
 * Created by william.montiel on 09/02/2017.
 */

public class MarvelCharacter {
    public int id;
    public String name;
    public String comic;
    public String imageUrl;

    public MarvelCharacter(int id, String name, String imageUrl, String comic) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
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

    public String getComic() {
        return comic;
    }

    public void setComic(String comic) {
        this.comic = comic;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
