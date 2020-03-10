package com.farheen.Model;

import java.math.BigDecimal;
import java.util.Objects;

public class MotorBike extends Vehicle {

    // instance variable declaration
    private boolean helmetAvailable;
    private boolean storageAvailable;

    //constructor
    public MotorBike(String plateNo, String make, String model, boolean availability, BigDecimal rentalPrice, double engineCapacity, Schedule scheduleSlot, boolean helmetAvailable, boolean storageAvailable)
    {
        super(plateNo, make, model, availability, rentalPrice, engineCapacity, scheduleSlot);
        this.helmetAvailable = helmetAvailable;
        this.storageAvailable = storageAvailable;
    }

    //setters
    public void setHelmetAvailable(boolean helmetAvailable) {
        this.helmetAvailable = helmetAvailable;
    }

    public void setStorageAvailable(boolean storageAvailable) {
        this.storageAvailable = storageAvailable;
    }

    // getters
    public boolean isHelmetAvailable() {
        return helmetAvailable;
    }

    public boolean isStorageAvailable() {
        return storageAvailable;
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

    //overriding equals and hashcode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MotorBike)) return false;
        if (!super.equals(o)) return false;
        MotorBike motorBike = (MotorBike) o;
        return helmetAvailable == motorBike.helmetAvailable &&
                storageAvailable == motorBike.storageAvailable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), helmetAvailable, storageAvailable);
    }

    // overriding toSting method
    @Override
    public String toString() {
        return "MotorBike{" +
                "helmetAvailable=" + helmetAvailable +
                ", storageAvailable=" + storageAvailable +
                "} " + super.toString();
    }
}
