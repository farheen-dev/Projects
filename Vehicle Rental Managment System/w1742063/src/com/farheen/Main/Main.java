package com.farheen.Main;

import com.farheen.Controller.*;
import com.farheen.Database.*;
import com.farheen.Model.*;
import com.farheen.View.GraphicalUserInterface;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    //using Scanner object to take input from the keyboard
    public static Scanner input = new Scanner(System.in);
    public static WestminsterRentalVehicleManager VehicleRentalManager = new WestminsterRentalVehicleManager();

    private static void display(String[] args) throws IOException
    {
        int option = 0;

        while(option != 6){
            //using Do while loop for validation
            do {
                // Displaying the menu
                displayMenu();
                // User input validation
                while (!input.hasNextInt()) {
                    System.out.println("Invalid option entered \n");
                    // Displaying the  menu again
                    displayMenu();
                    input.next();//remove the none integer that was previously entered
                    // this is a must otherwise the program will loop through infinitely

                }
                // getting the input from the system and assigning it to a variable named option
                option = input.nextInt();

                //Using switch-case for the option 1,2,3,4,5,6
                switch (option)
                {
                    case 1:
                        System.out.println("Select the type of Vehicle you need to Add to the Park");
                        System.out.println("1- Car");
                        System.out.println("2- MotorBike");
                        System.out.print("Select a Vehicle type:");

                        //User Validations for
                        while (!input.hasNextInt()) {
                            System.out.println("Invalid option entered \n");
                            System.out.print("Select a Vehicle type:");
                            input.next();
                        }
                        int opt = input.nextInt();

                        if(opt == 1 ){
                            addVehicleForCarType();
                        }else if(opt == 2){
                            addVehicleForMotorBikeType();
                        }else{
                            System.out.println("Invalid option entered ");
                        }
                        break;
                    case 2:
                        Database db = new Database();
                        System.out.print("Enter the plateNo:");
                        input.nextLine();
                        String plateNo = input.nextLine();
                        if(db.checkAvailability(plateNo)){
                            VehicleRentalManager.deleteVehicle(plateNo);
                        }else {
                            System.out.println("No such plateNo found");
                        }
                        break;
                    case 3:
                        VehicleRentalManager.printList();
                        break;
                    case 4:
                        try {
                            GraphicalUserInterface.main(args);
                        } catch (Exception e) {
                            System.out.println("\n The launch function in GUI can be Called only once !!!");
                            continue;
                        }
                        break;
                    case 5:
                        VehicleRentalManager.save();
                        break;
                    case 6:
                        System.out.println("Exiting the Program");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("The option entered should be in the 1-6 \n");
                }
            } while (option < 1 || option > 6);
        }

    }

    // addVehicleForCarType method for add Cars to the store
    public static void addVehicleForCarType()
    {

        System.out.print("Enter the PlateNo:");
        input.nextLine();
        String plateNo =  input.nextLine();

        System.out.print("Enter the Make:");
        String make =  input.nextLine();

        System.out.print("Enter the Model:");
        String model = input.nextLine();

        System.out.print("Enter the Availability:");
        //User input validation
        while(!input.hasNextBoolean()){
            System.out.println("Invalid type entered \n");
            System.out.print("Enter the Availability:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }
        boolean availability = input.nextBoolean();

        System.out.print("Enter the Rental Price:");
        //User input validation
        while (!input.hasNextBigDecimal()) {
            System.out.println("Invalid type entered \n");
            System.out.print("Enter the Rental price:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }
        BigDecimal rentalPrice = input.nextBigDecimal();

        System.out.print("Enter the Engine Capacity:");
        while (!input.hasNextDouble()) {
            //User input validation
            System.out.println("Invalid type entered \n");
            System.out.print("Enter the Engine Capacity:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }
        double engineCapacity = input.nextDouble();


        System.out.print("Enter the Air Condition:");
        //User input validation
        while(!input.hasNextBoolean()){
            System.out.println("Invalid type entered \n");
            System.out.print("Enter the Air Condition:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }
        boolean hasAirCondition = input.nextBoolean();

        int noOfSeats;
        while (true){
            System.out.print("Enter the No of seats:");
            while (!input.hasNextInt()) {
                //User input validation
                System.out.println("Invalid type entered \n");
                System.out.print("Enter the No of seats:");
                input.next();//remove the none integer that was previously entered
                // this is a must otherwise the program will loop through infinitely
            }
             noOfSeats =  input.nextInt();
            if(noOfSeats > 5 ){
                System.out.println("Invalid option entered \n ");
                continue;
            }
            break;
        }


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date pickUpDate = formatter.parse("0000-00-00");
            Date dropOffDate = formatter.parse("0000-00-00");
            Schedule s = new Schedule(pickUpDate,dropOffDate);
            Car car = new Car(plateNo,make,model,availability,rentalPrice,engineCapacity,s,hasAirCondition,noOfSeats);
            System.out.println(car.toString());
            VehicleRentalManager.addVehicle(car);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }



    }

    // addVehicleForMotorBikeType method for add MotorBike to the store
    public static void addVehicleForMotorBikeType()
    {

        System.out.print("Enter the PlateNo:");
        input.nextLine();
        String plateNo =  input.nextLine();

        System.out.print("Enter the Make:");
        String make =  input.nextLine();

        System.out.print("Enter the Model:");
        String model = input.nextLine();

        System.out.print("Enter the Availability:");
        //User input validation
        while (!input.hasNextBoolean()) {
            System.out.println("Invalid type entered \n");
            System.out.print("Enter the Availability:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }
        boolean availability = input.nextBoolean();

        System.out.print("Enter the Rental Price:");
        //User input validation
        while (!input.hasNextBigDecimal()) {
            System.out.println("Invalid type entered \n");
            System.out.print("Enter the Rental price:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }
        BigDecimal rentalPrice = input.nextBigDecimal();

        System.out.print("Enter the Engine Capacity:");
        //User input validation
        while (!input.hasNextDouble()) {
            System.out.println("Invalid type entered \n");
            System.out.print("Enter the Engine Capacity:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }
        double engineCapacity = input.nextDouble();


        System.out.print("Enter the Availability of Helmet:");
        //User input validation
        while (!input.hasNextBoolean()) {
            System.out.println("Invalid type entered \n");
            System.out.print("Enter the Availability of Helmet:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }
        boolean hasHelmet = input.nextBoolean();

        System.out.print("Enter the Availability of Storage:");
        //User input validation
        while (!input.hasNextBoolean()) {
            System.out.println("Invalid type entered \n");
            System.out.print("Enter the Availability of Storage:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }
        boolean hasStorage = input.nextBoolean();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date pickUpDate = formatter.parse("0000-00-00");
            Date dropOffDate = formatter.parse("0000-00-00");
            Schedule s = new Schedule(pickUpDate,dropOffDate);
            MotorBike motorBike = new MotorBike(plateNo,make,model,availability,rentalPrice,
                    engineCapacity,s,hasHelmet,hasStorage);
            System.out.println(motorBike.toString());
            VehicleRentalManager.addVehicle(motorBike);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }


    }

    // displayMenu method for display the .Main Menu
    private static void displayMenu()
    {
        System.out.println("\n Menu to Manage the Vehicle Rental Stock");
        System.out.println("1)Add Vehicle");
        System.out.println("2)Delete Vehicle");
        System.out.println("3)Print Vehicle Rental List");
        System.out.println("4)Reserve Vehicle");
        System.out.println("5)Save");
        System.out.println("6)Exist Program");
        System.out.println(">>");
        System.out.print("Enter an option:");
    }


    //Main Method Where Execution Starts
    public static void main(String[] args) throws IOException {
        //Database d = new Database();
        display(args);


    }



}
