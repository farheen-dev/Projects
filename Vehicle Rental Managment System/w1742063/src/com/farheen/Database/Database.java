package com.farheen.Database;

import com.farheen.Model.*;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;

public class Database{

    //Connecting to the database
    MongoClient client = new MongoClient("localhost", 27017);
    //creating a database
    MongoDatabase database = client.getDatabase("WestminsterRentalVehicleManager");
    MongoCollection VehicleRentalcollection = database.getCollection("VehicleRental");
    MongoCollection ReservedVehiclecollection = database.getCollection("ReservedVehicle");
    Document document = new Document();
    Scanner input = new Scanner(System.in);


    //addToStore method to add items to the database
    public void addToStore( Vehicle vehicleItem)
    {
        Document document = getDoc(vehicleItem);
        VehicleRentalcollection.insertOne(document);
        client.close();
        System.out.println();
    }

    //get Document object from the database
    public  Document getDoc( Vehicle vehicleItem)
    {
        Document Schedule = new Document();
        //Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String pickUpDate = formatter.format(vehicleItem.getScheduleSlot().getPickUpDate());
        String dropOffDate = formatter.format(vehicleItem.getScheduleSlot().getDropOffDate());

        Schedule.put("pickUpDate",pickUpDate );
        Schedule.put("dropOffDate", dropOffDate);

        if (vehicleItem instanceof Car) {

            document.put("PlateNo",vehicleItem.getPlateNo());
            document.put("Make",vehicleItem.getMake());
            document.put("Model",vehicleItem.getModel());
            document.put("Availability",vehicleItem.isAvailability());
            document.put("Rental Price",vehicleItem.getRentalPrice().toString());
            document.put("Engine Capacity",vehicleItem.getEngineCapacity());
            document.put("Schedule Slot",Schedule);
            document.put("Air Condition",((Car) vehicleItem).isHasAirCondition());
            document.put("No of Seats",((Car) vehicleItem).getNoOfSeats());
            document.put("type", "Car");
        }
        if (vehicleItem instanceof MotorBike) {

            document.put("PlateNo",vehicleItem.getPlateNo());
            document.put("Make",vehicleItem.getMake());
            document.put("Model",vehicleItem.getModel());
            document.put("Availability",vehicleItem.isAvailability());
            document.put("Rental Price",vehicleItem.getRentalPrice().toString());
            document.put("Engine Capacity",vehicleItem.getEngineCapacity());
            document.put("Schedule Slot",Schedule);
            document.put("Helmet Availability",((MotorBike) vehicleItem).isHelmetAvailable());
            document.put("Storage Availability",((MotorBike) vehicleItem).isStorageAvailable());
            document.put("type", "MotorBike");
        }
            return document;
    }

    //getSizeOfStore Items method to get the no of item in the database
    public long getSizeOfStore()
    {
        return VehicleRentalcollection.count();
    }

    // deleteItem method to delete item from the database
    public boolean deleteItem(String key)
    {
        FindIterable<Document> findIterable = VehicleRentalcollection.find(eq("PlateNo",key ));
        System.out.println(findIterable.first().get("type").toString());
        VehicleRentalcollection.deleteOne(new Document("PlateNo", key));
        return true;
    }

    //checkItemIsAvailable method to search items in the database
    public  boolean checkAvailability(String plateNo)
    {
        FindIterable<Document> findIterable = VehicleRentalcollection.find(eq("PlateNo",plateNo ));

        if(findIterable.first() != null){
            return true;
        }
        else{
            return  false;
        }
    }

    //sortStore method to sort items
    public void sortVehicleStore()
    {
        ShowItems("sort");

    }

    //ShowItems method to show/print com.farheen.Model.Vehicle items in the database
    //Where it will be printed in Capital to Simple in ascending order
    public void ShowItems(String flag)
    {

        List<Document> foundDocument = new ArrayList<Document>();

        if(flag.equals("sort"))
        {
            foundDocument = (List<Document>) VehicleRentalcollection.find().sort(ascending("Make")).into(new ArrayList<Document>());

        }else{
            foundDocument = (List<Document>) VehicleRentalcollection.find().into(new ArrayList<Document>());

        }

        List<Document> car = new ArrayList<Document>();
        List<Document> motorBike = new ArrayList<Document>();

        for (Document VehicleItem:foundDocument)
        {
            if( VehicleItem.get("type").equals("Car"))
            {
                car.add(VehicleItem);
            }

            if(VehicleItem.get("type").equals("MotorBike")){
                motorBike.add(VehicleItem);
            }
        }

        System.out.println("List of Car");
        for (Document car_item:car)
        {
            System.out.print("PlateNo:"+car_item.get("PlateNo")+" ");
            System.out.print("Make:"+car_item.get("Make"));
            System.out.println();
        }
        System.out.println();

        System.out.println("List of MotorBike");
        for (Document motorBike_item:motorBike)
        {
            System.out.print("PlateNo:"+motorBike_item.get("PlateNo")+" ");
            System.out.print("Make:"+motorBike_item.get("Make"));
            System.out.println();
        }
    }

    // getReport method to generate report
    public String getReport()
    {
        List<Document> foundDocument = (List<Document>) VehicleRentalcollection.find().into(new ArrayList<Document>());
        String report=" ";
        for (Document VehicleItem:foundDocument) {
            report += " "+VehicleItem.toString()+" "+System.lineSeparator();
        }

        return report;
    }

    //To get the vehicle from the Vehicle store manager
    public List<Vehicle> getVehicle()
    {
        List<Document> foundDocument = (List<Document>) VehicleRentalcollection.find().sort(ascending("Make")).into(new ArrayList<Document>());

        return vehicleList(foundDocument);
    }

    //Vehcicle list method which is used to get the data to the tables in the GUI
    public List<Vehicle> vehicleList( List<Document> foundDocument)
     {
        List<Document> car = new ArrayList<Document>();
        List<Document> motorBike = new ArrayList<Document>();

        for (Document item:foundDocument) {
            if( item.get("type").equals("Car"))
            {
                car.add(item);
            }

            if(item.get("type").equals("MotorBike")){
                motorBike.add(item);
            }
        }

        List<Vehicle> vehicles = new ArrayList<Vehicle>();

        for(Document car_item:car)
        {
            if (car_item == null) {
                continue; // or break, whatever is better in your case
            }

            try {
                Document q = (Document) car_item.get("Schedule Slot");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                Date pickUpDate = formatter.parse(q.get("pickUpDate").toString());
                Date dropOffDate = formatter.parse(q.get("dropOffDate").toString());

                Schedule s = new Schedule(pickUpDate,dropOffDate);
                Car c = new Car(car_item.get("PlateNo").toString(),car_item.get("Make").toString(),car_item.get("Model").toString(),Boolean.parseBoolean(car_item.get("Availability").toString()),new BigDecimal(car_item.get("Rental Price").toString()),Double.parseDouble(car_item.get("Engine Capacity").toString()),s,Boolean.parseBoolean(car_item.get("Air Condition").toString()),Integer.parseInt(car_item.get("No of Seats").toString()));

                vehicles.add(c);
            }catch (Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }

        for (Document motorBike_item:motorBike)

        {
            if (motorBike_item == null) {
                continue; // or break, whatever is better in your case
            }
            try {
                Document q = (Document) motorBike_item.get("Schedule Slot");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                Date pickUpDate = formatter.parse(q.get("pickUpDate").toString());
                Date dropOffDate = formatter.parse(q.get("dropOffDate").toString());
                System.out.println("Date is: "+pickUpDate+dropOffDate);

                Schedule s = new Schedule(pickUpDate,dropOffDate);
                MotorBike mb = new MotorBike(motorBike_item.get("PlateNo").toString(),motorBike_item.get("Make").toString(),motorBike_item.get("Model").toString(),Boolean.parseBoolean(motorBike_item.get("Availability").toString()),new BigDecimal(motorBike_item.get("Rental Price").toString()),Double.parseDouble(motorBike_item.get("Engine Capacity").toString()),s,Boolean.parseBoolean(motorBike_item.get("Helmet Availability").toString()),Boolean.parseBoolean(motorBike_item.get("Storage Availability").toString()));
                vehicles.add(mb);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return vehicles;
    }

    //filtering the vehicle store list shown in the GUI according to the given parameters
    public List<Vehicle> filter(String type,String key)
    {
        List<Document> filter ;

        System.out.println(type);

        if(type.equals("Engine Capacity"))
        {
            filter  = (List<Document>)VehicleRentalcollection.find(eq(type,Double.parseDouble(key) )).into(new ArrayList<Document>());
        }else{
            filter  = (List<Document>)VehicleRentalcollection.find(eq(type,key )).into(new ArrayList<Document>());
        }

        return vehicleList(filter);
    }

    //Finding if the plateNo with the vehicle is in the vehicle Rental store
    public String findInStore(String key , String TypeofVehicle)
    {
        FindIterable<Document> findIterable = VehicleRentalcollection.find(eq("PlateNo",key ));
        String item =" ";

        if(findIterable.first() == null){
            System.out.println("b");
            item = "There is no vehicle with that plate No";
        }else if(findIterable.first().get("type").equals(TypeofVehicle) && Boolean.parseBoolean(findIterable.first().get("Availability").toString())){
            item = "The Vehicle is available,To reserve fill the Client info";
        }else if(!findIterable.first().get("type").equals(TypeofVehicle) ){
            item = "Type is not valid";
        }else{
            item = "Vehicle is not Available";
        }

        return item;
    }

    //Adding the reservation detail of the customer to the Reservation store in the database
    public Reserve addToReservation(Customer customer , String plateNo, Date pickup, Date dropoff)
    {

        FindIterable<Document> findIterable = VehicleRentalcollection.find(eq("PlateNo",plateNo ));
        Document vehicle =  findIterable.first();

        Document q = (Document) vehicle.get("Schedule Slot");

        //Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String pickUpDate = formatter.format(pickup);
        String dropOffDate = formatter.format(dropoff);

        q.put("pickUpDate",pickUpDate );
        q.put("dropOffDate", dropOffDate);

        vehicle.put("Schedule Slot",q);
        vehicle.put("Availability",false);
        Document user = new Document();

        user.put("name",customer.getName());
        user.put("contactNo",customer.getContactNo());
        user.put("email",customer.getEmail());
        user.put("address",customer.getAddress());

        Document Reservation = new Document();

        Reservation.put("customer",user);
        Reservation.put("vehicle",vehicle);

        ReservedVehiclecollection.insertOne(Reservation);

       VehicleRentalcollection.deleteOne(new Document("PlateNo",plateNo));
       VehicleRentalcollection.insertOne(vehicle);

        Vehicle v =null;
        Schedule s = new Schedule(pickup,dropoff);

        if( vehicle.get("type").equals("Car"))
        {
            v = new Car(vehicle.get("PlateNo").toString(),vehicle.get("Make").toString(),vehicle.get("Model").toString(),Boolean.parseBoolean(vehicle.get("Availability").toString()),new BigDecimal(vehicle.get("Rental Price").toString()),Double.parseDouble(vehicle.get("Engine Capacity").toString()),s,Boolean.parseBoolean(vehicle.get("Air Condition").toString()),Integer.parseInt(vehicle.get("No of Seats").toString()));
        }

        if(vehicle.get("type").equals("MotorBike"))
        {
            v = new MotorBike(vehicle.get("PlateNo").toString(),vehicle.get("Make").toString(),vehicle.get("Model").toString(),Boolean.parseBoolean(vehicle.get("Availability").toString()),new BigDecimal(vehicle.get("Rental Price").toString()),Double.parseDouble(vehicle.get("Engine Capacity").toString()),s,Boolean.parseBoolean(vehicle.get("Helmet Availability").toString()),Boolean.parseBoolean(vehicle.get("Storage Availability").toString()));

        }

        return new Reserve(v,customer);
    }

}

