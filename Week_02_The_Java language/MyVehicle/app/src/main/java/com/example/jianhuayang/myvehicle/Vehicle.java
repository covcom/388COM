package com.example.jianhuayang.myvehicle;

/**
 * Created by jianhuayang on 25/09/15.
 */
public class Vehicle {
    private String make;
    private int year;
    private String message;

    public Vehicle() {
        this.make = "Volvo";
        this.year = 2012;
        this.message = "The default car is a " + make + " built in " + year;
    }

    public Vehicle(String make, int year) {
        this.make = make;
        this.year = year;
        this.message = "Your car is a " + make + " built in " + year;
    }

    public Vehicle(String make) {
        this();
        this.make = make;
        message = "You didn't type in year value";
    }

    @Override
    public String toString() {
        return message;
    }

    public String getMessage() {
        return message;
    }


}