package com.farheen.Model;
import java.math.BigDecimal;
import java.util.Objects;

public abstract class Vehicle {

    // Instance variable declaration
    private String plateNo;
    private String make;
    private String model;
    private boolean availability;
    private BigDecimal rentalPrice;
    private double engineCapacity;
    private Schedule scheduleSlot;

    //constructor
    public Vehicle(String plateNo, String make, String model, boolean availability, BigDecimal rentalPrice, double engineCapacity, Schedule scheduleSlot) {
        this.plateNo = plateNo;
        this.make = make;
        this.model = model;
        this.availability = availability;
        this.rentalPrice = rentalPrice;
        this.engineCapacity = engineCapacity;
        this.scheduleSlot = scheduleSlot;
    }


    //setters
    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }


    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }


    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }


    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }


    public void setScheduleSlot(Schedule scheduleSlot) {
        this.scheduleSlot = scheduleSlot;
    }

    // getters
    public String getPlateNo() {
        return plateNo;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public boolean isAvailability() {
        return availability;
    }

    public Schedule getScheduleSlot() {
        return scheduleSlot;
    }

    // declaration of abstract method
    public abstract boolean  checkAvailability();

    public abstract BigDecimal calculateRent();

    //overriding equals and hashcode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return availability == vehicle.availability &&
                Double.compare(vehicle.engineCapacity, engineCapacity) == 0 &&
                plateNo.equals(vehicle.plateNo) &&
                make.equals(vehicle.make) &&
                model.equals(vehicle.model) &&
                rentalPrice.equals(vehicle.rentalPrice) &&
                scheduleSlot.equals(vehicle.scheduleSlot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNo, make, model, availability, rentalPrice, engineCapacity, scheduleSlot);
    }

    //Overriding the toString method
    @Override
    public String toString() {
        return "Vehicle{" +
                "plateNo='" + plateNo + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", availability=" + availability +
                ", rentalPrice=" + rentalPrice +
                ", engineCapacity=" + engineCapacity +
                ", scheduleSlot=" + scheduleSlot +
                '}';
    }
}
