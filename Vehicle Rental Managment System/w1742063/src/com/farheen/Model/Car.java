package com.farheen.Model;

import java.math.BigDecimal;
import java.util.Objects;

public class Car extends Vehicle{

    // instance variable declaration
    private boolean hasAirCondition;
    private int noOfSeats;

    //constructor
    public Car(String plateNo, String make, String model, boolean availability, BigDecimal rentalPrice, double engineCapacity, Schedule scheduleSlot, boolean hasAirCondition, int noOfSeats)
    {
        super(plateNo, make, model, availability, rentalPrice, engineCapacity, scheduleSlot);
        this.hasAirCondition = hasAirCondition;
        this.noOfSeats = noOfSeats;
    }

    // setter
    public void setHasAirCondition(boolean hasAirCondition) {
        this.hasAirCondition = hasAirCondition;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    //getter
    public boolean isHasAirCondition() {
        return hasAirCondition;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    // overriding abstract method from the Vehicle abstract class
    @Override
    public boolean checkAvailability() {
        return false;
    }

    @Override
    public BigDecimal calculateRent() {
        return null;
    }

    // overriding equals and hashcode method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return hasAirCondition == car.hasAirCondition &&
                noOfSeats == car.noOfSeats;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasAirCondition, noOfSeats);
    }


    // overriding toString method
    @Override
    public String toString() {
        return "Car{" +
                "hasAirCondition=" + hasAirCondition +
                ", noOfSeats=" + noOfSeats +
                "} " + super.toString();
    }
}
