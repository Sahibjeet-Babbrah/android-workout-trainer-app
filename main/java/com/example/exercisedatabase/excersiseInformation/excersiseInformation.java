package com.example.exercisedatabase.excersiseInformation;

/**
 * Class to store the exercise information for a certain exersise.
 */
public class excersiseInformation {

    private Integer id;
    private String exersiseName;
    private Integer sets = null;
    private Integer reps = null;
    private Float weight = null;
    private Float restTime = null;
    private Float excersiseTime = null;
    private Float distance = null;
    private Float hr = null;
    private String intensity = null;
    private String notes = null;
    private String date;

    public excersiseInformation() {}
    public excersiseInformation(String exersise, int userID, String newDate) {
        exersiseName = exersise;
        id = userID;
        date = newDate;
    }

    public String getExersiseName() {
        return exersiseName;
    }

    public String getDate() {
        return date;
    }

    public void setExersiseName(String exersiseName) {
        this.exersiseName = exersiseName;
    }

    public Integer getId() {
        return id;
    }

    public void setSets(int numberOfSets) {
        sets = numberOfSets;
    }

    public int getSets() {
        return sets;
    }

    public void setReps(int numberOfReps) {
        reps = numberOfReps;
    }

    public int getReps() {
        return reps;
    }

    public void setWeight(float weightUsed) {
        weight = weightUsed;
    }

    public float getWeight() {
        return weight;
    }

    public void setRestTime(float restTimeElasped) {
        restTime = restTimeElasped;
    }

    public float getRest() {
        return restTime;
    }

    public void setExcersiseTime(float excersiseTimeElasped) {
        excersiseTime = excersiseTimeElasped;
    }

    public float getExcersiseTime() {
        return excersiseTime;
    }

    public void setDistance(float distanceRun) {
        distance = distanceRun;
    }

    public float getDistance() {
        return distance;
    }

    public void setHR(float newHR) {
        hr = newHR;
    }

    public float getHR() {
        return hr;
    }

    public void setIntensity(String newIntensity) {
        intensity = newIntensity;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setNotes(String newNotes) {
        notes = newNotes;
    }

    public String getNotes() {
        return notes;
    }
}
