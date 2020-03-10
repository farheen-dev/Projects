package com.farheen.Model;

import java.util.Objects;

public class Reserve {

    // Declaration of Instance Variables
    private Vehicle vehicle;
    private Customer client;

    //Constructor
    public Reserve(Vehicle vehicle, Customer client) {
        this.vehicle = vehicle;
        this.client = client;
    }

    //Getters
    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getClient() {
        return client;
    }

    //Overriding equals and hashcode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserve reserve = (Reserve) o;
        return vehicle.equals(reserve.vehicle) &&
                client.equals(reserve.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicle, client);
    }

    //Overriding toString method
    @Override
    public String toString() {
        return "Reserve{" +
                "vehicle=" + vehicle +
                ", client=" + client +
                '}';
    }
}
