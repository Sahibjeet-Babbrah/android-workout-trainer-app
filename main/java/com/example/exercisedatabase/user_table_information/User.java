package com.example.exercisedatabase.user_table_information;

public class User {
    private int _id;
    private String _name;
    private String _phone_number;
    private String _date_of_birth;
    private String _gender;

    public User() {}
    public User(int id, String name, String phone_number, String date_of_birth, String gender) {
        _id = id;
        _name = name;
        _phone_number = phone_number;
        _date_of_birth = date_of_birth;
        _gender = gender;
    }

    public User(String name, String phone_number, String date_of_birth, String gender) {
        _name = name;
        _phone_number = phone_number;
        _date_of_birth = date_of_birth;
        _gender = gender;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int id) {
        _id = id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        _name = name;
    }

    public String get_phone_number() {
        return _phone_number;
    }

    public void set_phone_number(String phone_number) {
        _phone_number = phone_number;
    }

    public String get_date_of_birth() {
        return _date_of_birth;
    }

    public void set_date_of_birth(String date_of_birth) {
        _date_of_birth = date_of_birth;
    }

    public String get_gender() {
        return _gender;
    }

    public void set_gender(String gender) {
        _gender = gender;
    }
}
