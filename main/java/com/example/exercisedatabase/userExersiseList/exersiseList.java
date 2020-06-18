package com.example.exercisedatabase.userExersiseList;

public class exersiseList {

    private Integer userID;
    private String group;
    private String exersiseName;
    private String exersiseDescription;
    private String exersiseType;
    private String photoLocation;
    private String photoName;

    public exersiseList(Integer ID, String gr) {
        userID = ID;
        group = gr;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getGroup() {
        return group;
    }

    public String getExersiseType() {
        return exersiseType;
    }

    public void setExersiseType(String t) {
        exersiseType = t;
    }

    public String getExersiseName() {
        return exersiseName;
    }

    public String getExersiseDescription() {
        return exersiseDescription;
    }

    public void setExersiseName(String newName) {
        exersiseName = newName;
    }

    public void setExersiseDescription(String newDesc) {
        exersiseDescription = newDesc;
    }

    public String getPhotoLocation() {
        return photoLocation;
    }

    public void setPhotoLocation(String _phot) {
        photoLocation = _phot;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String _name) {
        photoName = _name;
    }
}
