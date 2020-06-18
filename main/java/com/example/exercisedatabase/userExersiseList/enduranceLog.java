package com.example.exercisedatabase.userExersiseList;

public class enduranceLog {
    private int ID;
    private String exersiseName;
    private String exersiseGroup;
    private String date;
    private float timeMinutes;
    private float timeSeconds;
    private float distance;
    private String intensity;
    private String endurance_type;

    public enduranceLog(int _id, String _name, String _group) {
        ID = _id;
        exersiseName = _name;
        exersiseGroup = _group;
    }

    public void setEndurance_type(String t) {
        endurance_type = t;
    }

    public String getEndurance_type() {
        return endurance_type;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setExersiseName(String exersiseName) {
        this.exersiseName = exersiseName;
    }

    public void setExersiseGroup(String exersiseGroup) {
        this.exersiseGroup = exersiseGroup;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTimeMinutes(float timeMinutes) {
        this.timeMinutes = timeMinutes;
    }

    public void setTimeSeconds(float timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public int getID() {
        return ID;
    }

    public String getExersiseName() {
        return exersiseName;
    }

    public String getExersiseGroup() {
        return exersiseGroup;
    }

    public String getDate() {
        return date;
    }

    public float getTimeMinutes() {
        return timeMinutes;
    }

    public float getTimeSeconds() {
        return timeSeconds;
    }

    public float getDistance() {
        return distance;
    }

    public String getIntensity() {
        return intensity;
    }
}
