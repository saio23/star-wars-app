package com.sioukas.starwarsapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SWPeopleListModel {
    // POJO class for People List object retrieved from the API
    @SerializedName("count")
    private Integer count;

    @SerializedName("next")
    private String next;

    @SerializedName("previous")
    private String previous;

    @SerializedName("results")
    private List<SWPeopleDetailModel> results;

    public SWPeopleListModel(Integer count, String next, String previous, List<SWPeopleDetailModel> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<SWPeopleDetailModel> getResults() {
        return results;
    }

    public void setResults(List<SWPeopleDetailModel> results) {
        this.results = results;
    }
}
