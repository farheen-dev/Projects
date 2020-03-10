import com.farheen.Controller.WestminsterRentalVehicleManager;
import com.farheen.Model.Car;
import com.farheen.Model.MotorBike;
import com.farheen.Model.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterRentalVehicleManagerTest {

     WestminsterRentalVehicleManager RentalTest;

    @BeforeEach
    void initEach()
    {
        RentalTest = new WestminsterRentalVehicleManager();
    }

    //Test Case for check the adding vehicle object To the database
    @Nested
    class addTest {

        //Checking Car Vehicle Object addition to the database
        @Test
        void testAddingCarEntity() throws ParseException {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date pickUpDate = formatter.parse("2012-12-04");
            Date dropOffDate = formatter.parse("2012-12-31");
            Schedule schedule = new Schedule(pickUpDate, dropOffDate);
            Car car = new Car("adf13", "DFD", "F", true, new BigDecimal("2245"), 2142, schedule, true, 3);
            assertEquals(true, RentalTest.addVehicle(car),
                    "Add Car object To the Database");
        }

        //Checking Motor Bike Vehicle Object addition to the database
        @Test
        void testAddingMotorBikeEntity() throws ParseException {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date pickUpDate = formatter.parse("2012-12-04");
            Date dropOffDate = formatter.parse("2012-12-31");
            Schedule schedule = new Schedule(pickUpDate, dropOffDate);
            MotorBike motorBike = new MotorBike("13", "DFD", "F", true, new BigDecimal("2245"), 2142, schedule, true, true);
            assertEquals(true, RentalTest.addVehicle(motorBike),
                    "Add Motor Bike Object to the Database");
        }
    }

    //Checking Car Vehicle Object deletion to the database
        @Test
        void testDeletingCarEntity()   {
            String plateNo = "adf13";
           assertEquals(true,RentalTest.deleteVehicle(plateNo) ,
                    "Delete Car object From the Database");
        }

    //Checking Motor Bike Vehicle Object deletion to the database
        @Test
        void testDeletingMotorBikeEntity()   {
            String plateNo = "13";
            assertEquals(true,RentalTest.deleteVehicle(plateNo) ,
                    "Delete Motor Bike object From the Database");
        }

    //Checking Vehicle PrintList
        @Test
        void testprintList()   {
            assertEquals(true,RentalTest.printList(),
                    "Print Vehicle List");
        }

    //Checking Report Generation
        @Test
        void testSave() throws IOException {
            assertEquals(true,RentalTest.save() ,
                    "Generates Report");
        }






}