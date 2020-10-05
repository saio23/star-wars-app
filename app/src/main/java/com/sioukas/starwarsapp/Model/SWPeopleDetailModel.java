package com.sioukas.starwarsapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SWPeopleDetailModel implements Parcelable {
    // POJO class for Characters object retrieved from the API
    @SerializedName("name")
    private String name;

    @SerializedName("height")
    private String height;

    @SerializedName("mass")
    private String mass;

    @SerializedName("hair_color")
    private String hair_color;

    @SerializedName("skin_color")
    private String skin_color;

    @SerializedName("eye_color")
    private String eye_color;

    @SerializedName("birth_year")
    private String birth_year;

    @SerializedName("gender")
    private String gender;

    @SerializedName("homeworld")
    private String homeworld;

    @SerializedName("films")
    private List<String> films;

    @SerializedName("species")
    private List<String> species;

    @SerializedName("vehicles")
    private List<String> vehicles;

    @SerializedName("starships")
    private List<String> starships;

    @SerializedName("created")
    private String created;

    @SerializedName("edited")
    private String edited;

    @SerializedName("String")
    private String url;

    public SWPeopleDetailModel(String name, String height, String mass, String hair_color, String skin_color, String eye_color, String birth_year, String gender, String homeworld, List<String> films, List<String> species, List<String> vehicles, List<String> starships, String created, String edited, String url) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hair_color = hair_color;
        this.skin_color = skin_color;
        this.eye_color = eye_color;
        this.birth_year = birth_year;
        this.gender = gender;
        this.homeworld = homeworld;
        this.films = films;
        this.species = species;
        this.vehicles = vehicles;
        this.starships = starships;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    public String getSkin_color() {
        return skin_color;
    }

    public void setSkin_color(String skin_color) {
        this.skin_color = skin_color;
    }

    public String getEye_color() {
        return eye_color;
    }

    public void setEye_color(String eye_color) {
        this.eye_color = eye_color;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getString() {
        return url;
    }

    public void setString(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.height);
        dest.writeString(this.mass);
        dest.writeString(this.hair_color);
        dest.writeString(this.skin_color);
        dest.writeString(this.eye_color);
        dest.writeString(this.birth_year);
        dest.writeString(this.gender);
        dest.writeString(this.homeworld);
        dest.writeStringList(this.films);
        dest.writeStringList(this.species);
        dest.writeStringList(this.vehicles);
        dest.writeStringList(this.starships);
        dest.writeString(this.created);
        dest.writeString(this.edited);
        dest.writeString(this.url);
    }

    protected SWPeopleDetailModel(Parcel in) {
        this.name = in.readString();
        this.height = in.readString();
        this.mass = in.readString();
        this.hair_color = in.readString();
        this.skin_color = in.readString();
        this.eye_color = in.readString();
        this.birth_year = in.readString();
        this.gender = in.readString();
        this.homeworld = in.readString();
        this.films = in.createStringArrayList();
        this.species = in.createStringArrayList();
        this.vehicles = in.createStringArrayList();
        this.starships = in.createStringArrayList();
        this.created = in.readString();
        this.edited = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<SWPeopleDetailModel> CREATOR = new Parcelable.Creator<SWPeopleDetailModel>() {
        @Override
        public SWPeopleDetailModel createFromParcel(Parcel source) {
            return new SWPeopleDetailModel(source);
        }

        @Override
        public SWPeopleDetailModel[] newArray(int size) {
            return new SWPeopleDetailModel[size];
        }
    };
}
