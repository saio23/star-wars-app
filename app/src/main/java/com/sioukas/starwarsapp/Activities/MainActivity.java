package com.sioukas.starwarsapp.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sioukas.starwarsapp.Adapters.PeopleListAdapter;
import com.sioukas.starwarsapp.DataService.RetrofitInstance;
import com.sioukas.starwarsapp.Interface.FetchDataService;
import com.sioukas.starwarsapp.Model.SWPeopleDetailModel;
import com.sioukas.starwarsapp.Model.SWPeopleListModel;
import com.sioukas.starwarsapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<SWPeopleListModel> {

    private List<SWPeopleDetailModel> peopleList;
    private FetchDataService service;
    private ProgressDialog progressDialog;
    private RecyclerView rvPeople;
    private PeopleListAdapter peopleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);
        initialize();
    }

    private void initialize() {
        initView();
        startFetchDataService();
        progressDialog.show();

    }

    private void startFetchDataService() {
        peopleList = new ArrayList<>(); //init Array for characters
        peopleAdapter = new PeopleListAdapter(this, peopleList);  // create adapter for RecyclerView
        rvPeople.setAdapter(peopleAdapter); //set the adapter to the RecyclerView
        // create Data Service for retrieving data from Swapi api using Retrofit Library.
        service = RetrofitInstance.getRetrofitInstance().create(FetchDataService.class);
//      Set HTTP GET request call
        Call<SWPeopleListModel> peopleModelCall = service.getPeople();
//      Queue the request call and add the callback to the Activity
        peopleModelCall.enqueue(this);
    }

    private void initView() {
//        Initialize view set the variables to the corresponding UI elements to the layout
        setupProgressDialog();
        rvPeople = findViewById(R.id.rvPeopleList);
        rvPeople.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setupProgressDialog() {
//        create a progress dialog to inform user the data is being downloaded from the API
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.FetchDataProgressDialogTitle));
        progressDialog.setMessage(getString(R.string.PleaseWaitDialogMessage));
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(android.R.style.Widget_Holo_Light_ProgressBar_Large);
    }

    @Override
    public void onResponse(Call<SWPeopleListModel> call, Response<SWPeopleListModel> response) {
        Log.d("RetroSuccess", "onResponse: Fetch of data ");
//        When the data is retrieved from the API asynchronously the response body containing the list of
//        characters is being added to the peopleList with a Modeled POJO class.
        if (response.body() != null) {
//            add the response results to the list
            peopleList.addAll(response.body().getResults());
//            refresh the adapter and show the newly added items in the RecyclerView
            peopleAdapter.notifyDataSetChanged();
//            get the next page URL from the response body
            String nextPageURL = response.body().getNext();
            if (nextPageURL != null) {
//                if there is a next page execute another HTTP GET request using HTTPS to avoid error for CLEAR_TEXT traffic.
                service.getNext(nextPageURL.replace("http:", "https:")).enqueue(this);
            } else {
//                when next page is null remove progress dialog.
                progressDialog.dismiss();
            }
        }


    }

    @Override
    public void onFailure(Call<SWPeopleListModel> call, Throwable t) {
//        Log any issues if there is an error with the HTTP request
        Log.d("RetroFailure", "onResponse: " + t.getMessage());
    }
}