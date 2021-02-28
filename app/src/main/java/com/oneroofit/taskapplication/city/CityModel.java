package com.oneroofit.taskapplication.city;

public class CityModel {
    String id;
    String city;
    String last_name;
    String first_name;

    public CityModel(String id, String city, String last_name, String first_name) {
        this.id = id;
        this.city = city;
        this.last_name = last_name;
        this.first_name = first_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}
