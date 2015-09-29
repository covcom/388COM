package com.example.jianhuayang.mycar;

/**
 * Created by jianhuayang on 28/09/15.
 */
public class Vehicle {
    public static int counter = 0;
    private String make;
    private int year;
    private String message;

    public Vehicle() {
        this.make = "Volvo";
        this.year = 2012;
        this.message = "This is the default message.";
    }

    public Vehicle(String make, int year) {
        this.make = make;
        this.year = year;
        this.message = "Your car is a " + make + " built in " + year + ".";
        count();
    }

    public Vehicle(String make) {
        this();
        this.make = make;
        message = "You didn't type in year value.";
        count();
    }

    @Override
    public String toString() {
        return message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private void count(){
        this.counter++;
    }

    interface controllerable {
        void control();
    }
}