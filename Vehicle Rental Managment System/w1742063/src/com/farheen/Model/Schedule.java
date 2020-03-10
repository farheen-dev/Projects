package com.farheen.Model;

import java.util.Date;
import java.util.Objects;

public class Schedule {

    //instance variable declaration
    private Date pickUpDate;
    private Date dropOffDate;

    //Constructor
    public Schedule(Date pickUpDate, Date dropOffDate) {
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
    }

    //Setters
    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public void setDropOffDate(Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    //Getters
    public Date getPickUpDate() {
        return pickUpDate;
    }

    public Date getDropOffDate() {
        return dropOffDate;
    }


    //Overriding equals and hashcode method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return pickUpDate.equals(schedule.pickUpDate) &&
                dropOffDate.equals(schedule.dropOffDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickUpDate, dropOffDate);
    }

    //Overriding toString method
    @Override
    public String toString() {
        return "Schedule{" +
                "pickUpDate=" + pickUpDate +
                ", dropOffDate=" + dropOffDate +
                '}';
    }
}
