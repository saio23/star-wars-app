package com.sioukas.starwarsapp.DataService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    //    create an instance for the retrofit client to handle HTTP requests
    private static Retrofit retrofit;
    //Base URL is the link to the Star Wars API
    private static final String BASE_URL = "https://swapi.dev/api/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //used for converting JSON file to Java Objects with the help of POJO classes.
                    .build();
        }
        return retrofit;
    }
}

