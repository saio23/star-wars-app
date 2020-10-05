package com.sioukas.starwarsapp.Activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sioukas.starwarsapp.DataService.RetrofitInstance;
import com.sioukas.starwarsapp.Interface.FetchDataService;
import com.sioukas.starwarsapp.Model.SWMoviesListModel;
import com.sioukas.starwarsapp.Model.SWPeopleDetailModel;
import com.sioukas.starwarsapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterDetailsActivity extends AppCompatActivity implements Callback<SWMoviesListModel> {
    TextView tvName, tvHeight, tvMass, tvHairColor, tvSkinColor, tvEyeColor, tvBirthYear, tvGender, tvFilms;
    SWPeopleDetailModel character;
    List<String> moviesList;
    private FetchDataService service;
    private int movieCounter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_details_activity);
        initialize();
    }

    private void initialize() {
        moviesList = new ArrayList<>();
        initView();
        if (getIntent().hasExtra("CHARACTER")) {
            character = (SWPeopleDetailModel) getIntent().getExtras().get("CHARACTER");
            service = RetrofitInstance.getRetrofitInstance().create(FetchDataService.class);
            for (String film : character.getFilms()) {
                service.getMovie(film.replace("http:", "https:")).enqueue(this);
            }
            movieCounter = character.getFilms().size();
            setDetails(character);
        }
    }

    private void setDetails(SWPeopleDetailModel character) {
        tvName.setText(character.getName());
        tvHeight.setText(character.getHeight());
        tvMass.setText(character.getMass());
        tvHairColor.setText(character.getHair_color());
        tvSkinColor.setText(character.getSkin_color());
        tvEyeColor.setText(character.getEye_color());
        tvBirthYear.setText(character.getBirth_year());
        tvGender.setText(character.getGender());
    }

    private void initView() {
        tvName = findViewById(R.id.tvPeopleName);
        tvHeight = findViewById(R.id.tvHeight);
        tvMass = findViewById(R.id.tvMass);
        tvHairColor = findViewById(R.id.tvHairColor);
        tvSkinColor = findViewById(R.id.tvSkinColor);
        tvEyeColor = findViewById(R.id.tvEyeColor);
        tvBirthYear = findViewById(R.id.tvBirthYear);
        tvGender = findViewById(R.id.tvGender);
        tvFilms = findViewById(R.id.tvFilms);
    }

    private void setMoviesList() {
        String newTitle = "";
        for (String title : moviesList) {
            newTitle += title + "\n\t";
        }
        tvFilms.setText(newTitle);
    }


    @Override
    public void onResponse(Call<SWMoviesListModel> call, Response<SWMoviesListModel> response) {
        if (response.body() != null) {
            moviesList.add(response.body().getTitle());
            movieCounter--;
        }
        if (movieCounter == 0)
            setMoviesList();


    }

    @Override
    public void onFailure(Call<SWMoviesListModel> call, Throwable t) {
        Toast.makeText(this, "Failure occured while fetching data.", Toast.LENGTH_SHORT).show();
    }
}

