package com.example.exercisedatabase.muscleInformation;

/**
 * Class to store all of the muscle related information.
 */
public class muscleInformation {
    private Integer id;
    private String date;
    private String measurement;
    private float BMI;
    private float neck;
    private float calves;
    private float chest;
    private float biceps;
    private float forearms;
    private float weight;
    private float height;
    private float height2;
    private float wrist;
    private float waist;
    private float hips;
    private float thighs;
    private String general_measurement;
    private String upper_measurement;
    private String arms_measurement;
    private String legs_measurement;

    public muscleInformation(){};

    /**
     * Constructor
     * @param measureType The type in which the measurements will be stored as, possibly gotten during exersise creation.
     * @param currentDate The date on which the measurement was taken.*/
    public muscleInformation(String measureType, String currentDate) {
        date = currentDate;
        measurement = measureType;
    }

    /**
     * Setter for the type of general measurement.
     */
    public void setGeneralMeasurement(String t) {
        general_measurement = t;
    }

    /**
     * Getter for the type of general measurement.
     */
    public String getGeneral_measurement() {
        return general_measurement;
    }

    /**
     * Setter for the type o upper measurement.
     */
    public void setUpperMeasurement(String t) {
        upper_measurement = t;
    }

    /**
     * Getter for the type of upper measurement.
     */
    public String getUpper_measurement() {
        return upper_measurement;
    }

    /**
     * Setter for the type of arms measurement.
     */
    public void setArms_measurement(String arms_measurement) {
        this.arms_measurement = arms_measurement;
    }

    /**
     * Getter for the type of arms measurement.
     */
    public String getArms_measurement() {
        return arms_measurement;
    }

    /**
     * Setter for the type of legs measurement.
     */
    public void setLegs_measurement(String t) {
        legs_measurement = t;
    }

    /**
     * Getter for the type of legs measurement.
     */
    public String getLegs_measurement() {
        return legs_measurement;
    }

    /**
     * Getter for the size of thighs.
     */
    public float getThighs() {
        return thighs;
    }

    /**
     * Setter for the size of thighs.
     */
    public void setThighs(float thighs) {
        this.thighs = thighs;
    }

    /**
     * Getter for the size of hips.
     */
    public float getHips() {
        return hips;
    }

    /**
     * Setter for the size of hips.
     */
    public void setHips(float hips) {
        this.hips = hips;
    }

    /**
     * Getter for the size of waist.
     */
    public float getWaist() {
        return waist;
    }

    /**
     * Setter for the size of waist.
     */
    public void setWaist(float waist) {
        this.waist = waist;
    }

    /**
     * Getter for the size of wrists.
     */
    public float getWrist() {
        return wrist;
    }

    /**
     * Setter for the size of wrists.
     */
    public void setWrist(float wrist) {
        this.wrist = wrist;
    }

    /**
     * Getter for the size of neck.
     */
    public float getNeck() {
        return neck;
    }

    /**
     * Setter for the size of neck.
     */
    public void setNeck(float neck) {
        this.neck = neck;
    }

    /**
     * Setter for the BMI.
     */
    public void setBMI(float BMI) {
        this.BMI = BMI;
    }

    /**
     * Getter for the BMI.
     */
    public float getBMI() {
        return BMI;
    }

    /**
     * Getter for the ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for the ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Setter for the date.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Setter for the measurement.
     */
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    /**
     * Getter for the date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter for the measurement.
     */
    public String getMeasurement() {
        return measurement;
    }

    /**
     * Setter for the calves.
     */
    public void setCalves(float newSize) {
        calves = newSize;
    }

    /**
     * Getter for the calves.
     */
    public float getCalves() {
        return calves;
    }

    /**
     * Setter for the chest.
     */
    public void setChest(float newSize) {
        chest = newSize;
    }

    /**
     * Getter for the chest.
     */
    public float getChest() {
        return chest;
    }

    /**
     * Setter for the bicep.
     */
    public void setBiceps(float newSize) {
        biceps = newSize;
    }

    /**
     * Getter for the bicep.
     */
    public float getBiceps() {
        return biceps;
    }

    /**
     * Setter for the forearms.
     */
    public void setForearms(float newSize) {
        forearms = newSize;
    }

    /**
     * Getter for the forearms.
     */
    public float getForearms() {
        return forearms;
    }

    /**
     * Setter for the weight.
     */
    public void setWeight(float newSize) {
        weight = newSize;
    }

    /**
     * Getter for the weight.
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Setter for the heights.
     */
    public void setHeight(float newSize, float newSize2) {
        height = newSize;
        height2 = newSize2;
    }

    /**
     * Getter for the height 1.
     */
    public float getHeight() {
        return height;
    }

    /**
     * Getter for the height 2.
     */
    public float getHeight2() {
        return height2;
    }
}
