package com.example.williammontiel.willmontiel.resources;

/**
 * Created by william.montiel on 09/02/2017.
 */

public class Cons {
    final static public String MARVEL_DEVELOPER_APIKEY = "c9cd45cc18a495a5f6f1a619cc16bc13";
    final static public String MARVEL_DEVELOPER_HASH = "6505d2217a44995181d90e93f3aadb99";
    final static public String MARVEL_DEVELOPER_TIMESTAMP = "1464344031.142341";
    public static final String URL_BASE = "http://gateway.marvel.com/";
    public static final String API = "v1/";
    public static final String ALL_CHARACTERS = "/public/characters";
    public static final String CHARACTER = "/public/characters/";
    public static final String APIKEY = "?apikey=";
    public static final String HASH = "&hash=";
    public static final String OFFSET = "&offset=";
    public static final String TIMESTAMP = "&ts=";
    public static final String QUERY = "&nameStartsWith=";


    public static final String GET_ALL_CHARACTERS = URL_BASE + API + ALL_CHARACTERS + APIKEY + MARVEL_DEVELOPER_APIKEY + HASH + MARVEL_DEVELOPER_HASH + OFFSET + TIMESTAMP + MARVEL_DEVELOPER_TIMESTAMP;
    public static final String GET_CHARACTER = URL_BASE + API + CHARACTER + APIKEY + MARVEL_DEVELOPER_APIKEY + HASH + MARVEL_DEVELOPER_HASH + OFFSET;

    final static public String ERROR_404 = "Página no encontrada";
    final static public String ERROR_401 = "Acceso denegado";
    final static public String SERVER_ERROR = "Ocurrió un error, por favor intenta más tarde.";
    final static public String NO_CHARACTERS = "No se encontraron más coincidencias";
}
