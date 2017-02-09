package com.example.williammontiel.willmontiel.resources;

/**
 * Created by william.montiel on 09/02/2017.
 */

public class Cons {
    final static public String MARVEL_DEVELOPER_APIKEY = "442225472160";
    final static public String MARVEL_DEVELOPER_HASH = "442225472160";
    public static final String URL_BASE = "http://gateway.marvel.com/";
    public static final String API = "v1/";
    public static final String ALL_CHARACTERS = "/public/characters";
    public static final String CHARACTER = "/v1/public/characters/";
    public static final String APIKEY = "?apikey=";
    public static final String HASH = "?hash=";
    public static final String OFFSET = "?offset=";


    public static final String GET_ALL_CHARACTERS = URL_BASE + API + ALL_CHARACTERS + APIKEY + MARVEL_DEVELOPER_APIKEY + HASH + MARVEL_DEVELOPER_HASH + OFFSET;
    public static final String GET_CHARACTER = URL_BASE + API + CHARACTER + APIKEY + MARVEL_DEVELOPER_APIKEY + HASH + MARVEL_DEVELOPER_HASH + OFFSET;

    final static public String ERROR_404 = "Recurso no encontrado";
    final static public String SERVER_ERROR = "Recurso no encontrado";
}
