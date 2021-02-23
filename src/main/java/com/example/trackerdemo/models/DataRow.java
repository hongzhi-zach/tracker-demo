package com.example.trackerdemo.models;

public class DataRow {
    private String state;
    private String country;
    private int latestTotalCases;
    private int diffFromPrevDay;

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public int getDiffFromPrevDay() {
        return diffFromPrevDay;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    public void setDiffFromPrevDay(int diffFromPrevDay) {
        this.diffFromPrevDay = diffFromPrevDay;
    }
}
