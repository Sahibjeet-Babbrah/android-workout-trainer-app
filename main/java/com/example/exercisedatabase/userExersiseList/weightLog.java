package com.example.exercisedatabase.userExersiseList;

public class weightLog {
    private int ID;
    private String exersiseName;
    private String exersiseGroup;
    private String date;
    private String weightType;
    private float oneRepMax;
    private float weight;
    private int sets;
    private int reps;
    private int rests;

    public weightLog(int _id, String _exersiseName, String _exersiseGroup) {
        ID = _id;
        exersiseName = _exersiseName;
        exersiseGroup = _exersiseGroup;
    }

    public void setID(int _id) {
        ID = _id;
    }

    public void setExersiseName(String exersiseName) {
        this.exersiseName = exersiseName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setExersiseGroup(String exersiseGroup) {
        this.exersiseGroup = exersiseGroup;
    }

    public void setWeightType(String weightType) {
        this.weightType = weightType;
    }

    public void setOneRepMax(float oneRepMax) {
        this.oneRepMax = oneRepMax;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setRests(int rests) {
        this.rests = rests;
    }

    public void setSets(int sets) {
        this.sets = sets;
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

    public String getWeightType() {
        return weightType;
    }

    public float getOneRepMax() {
        return oneRepMax;
    }

    public float getWeight() {
        return weight;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public int getRests() {
        return rests;
    }
}
