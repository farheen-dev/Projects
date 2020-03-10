package com.farheen.Controller;

import com.farheen.Model.*;

import java.io.IOException;

public interface RentalVehicleManager {

    // public abstract methods
    public boolean addVehicle(Vehicle vehicleItem);
    public boolean deleteVehicle(String plateNo);
    public boolean printList();
    public boolean save() throws IOException;



}
