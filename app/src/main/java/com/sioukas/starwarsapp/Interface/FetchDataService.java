package com.sioukas.starwarsapp.Interface;

import com.sioukas.starwarsapp.Model.SWMoviesListModel;
import com.sioukas.starwarsapp.Model.SWPeopleListModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface FetchDataService {
    // Interface for Retrofit Library that describes the API endpoints.
// GET request for the People List endpoint.
    @GET("people/")
    Call<SWPeopleListModel> getPeople();

    // GET request for the next page url of People List endpoint.
    @GET
    Call<SWPeopleListModel> getNext(@Url String pageUrl);

    // GET request for the movie details endpoint.
    @GET
    Call<SWMoviesListModel> getMovie(@Url String film);
}
