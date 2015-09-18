package com.example.jianhuayang.mylists;

/**
 * Created by jianhuayang on 19/02/15.
 */
public class Presidents {

    private String firstName;
    private String fullName;

    public Presidents(String firstName, String fullName) {
        this.firstName = firstName;
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
