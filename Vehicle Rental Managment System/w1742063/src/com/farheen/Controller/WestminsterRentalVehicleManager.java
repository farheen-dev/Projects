package com.farheen.Controller;
import com.farheen.Database.*;
import com.farheen.Model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class WestminsterRentalVehicleManager  implements RentalVehicleManager {

    //Creating an object for Scanner method
    public static Scanner input = new Scanner(System.in);

    // Declaring the constant Max_PARK_LOT Constant
    private static final int Max_PARK_LOT = 50;


    //Overriding add Vehicle Method
    @Override
    public boolean addVehicle(Vehicle vehicleItem)
    {
        boolean flag = false;
        Database db = new Database();
        if (db.getSizeOfStore() <= Max_PARK_LOT) {
            System.out.println("The space left is "+(Max_PARK_LOT-(db.getSizeOfStore()+1)));
            //using try catch to caught exceptions occur
            try {
                db.addToStore(vehicleItem);
                flag =true;
            }catch (Exception e){
                System.out.println(e.getMessage());

            }
        } else {
            throw new IllegalArgumentException("Parking Lot is full. No place to add Vehicles");
        }
        return flag;
    }

    //Overriding delete Vehicle Method
    @Override
    public boolean deleteVehicle(String plateNo)
    {

        boolean flag =false;
        // for handle exceptions
        try{
            Database db = new Database();
            if(db.getSizeOfStore() ==0)
            {
                throw new IllegalArgumentException("Store is empty. No Vehicle to delete");
            }

            flag = db.deleteItem(plateNo);
            System.out.println("The number of free spaces left is "+(Max_PARK_LOT -db.getSizeOfStore()));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return flag;

    }

    //Overriding PrintList for Rental Vehicle Method
    @Override
    public boolean printList()
    {
        boolean flag = false;
        try {
            Database db = new Database();
            db.sortVehicleStore();
        flag =true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return flag;
    }

    //Overriding Save Method
    @Override
    public boolean save() throws IOException
    {
        boolean flag =false;
        Random random= new Random();
        Database db = new Database();
        int i = random.nextInt();
        String report  = "Report"+i;
        File file = new File("C:\\Users\\Thanveer\\Desktop\\"+report+".txt");

        //Create the file
        if (file.createNewFile())
        {
            System.out.println("Report is Generated!");
            flag =true;
        } else {
            System.out.println("File already exists.");
        }

        //Write Content
        FileWriter writer = new FileWriter(file);
        writer.write(" Vehicle info "+ System.lineSeparator()+db.getReport());
        writer.close();

        return flag;
    }







}
