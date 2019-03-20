package pl.coderslab.model;

import java.sql.Timestamp;

public class Vehicle {

    private int id;
    private String model;
    private String make;
    private int manufactured;
    private String registrationNumber;
    private Timestamp nextReview;

    public Vehicle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getManufactured() {
        return manufactured;
    }

    public void setManufactured(int manufactured) {
        this.manufactured = manufactured;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Timestamp getNextReview() {
        return nextReview;
    }

    public void setNextReview(Timestamp nextReview) {
        this.nextReview = nextReview;
    }
}
