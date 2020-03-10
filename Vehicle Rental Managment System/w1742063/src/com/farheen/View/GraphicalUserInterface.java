package com.farheen.View;

import com.farheen.Database.*;
import com.farheen.Model.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GraphicalUserInterface extends Application implements EventHandler<ActionEvent>  {

    Stage window;
    Scene scene1,scene2,scene3;
    Label label9;
    Label PickUpDate;
    Label NoOfReservationDaysLabel;
    Button nextButton ;
    Button okButton;
    Button backButton1;
    Button backButton2;
    Button goButton;
    Button ReserveButton;
    Button clearButton;
    Button clearButton1;
    Button CheckAvailabilityButton;
    Button closeButton;
    TextField filtering;
    TextField nameInput1;
    TextField PlateNoInput;
    TextField nameInput2;
    TextField EmailInput;
    TextField AddressLabelInput;
    TextField NoOfReservationDaysInput;
    TextArea area;
    DatePicker date;
    ChoiceBox<String> choiceBox;
    ChoiceBox<String> choiceBox1;
    TableView<Car> carInfoTable;
    TableView<MotorBike> motorBikeInfoTable;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage ;
        window.setTitle("Westminster Vehicle Rental System");

        //scene1 -> Vehicle Information ------------------------------------------------------------------------

        // Label for the Scene topic
        Label label = new Label("Welcome to Westminster Vehicle Rental");
        label.setTextFill(Color.DARKBLUE);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 36));

        //Layout
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(label);

        // Label for the carInfoTable
        Label label1 = new Label("Rental Car Information");
        label1.setTextFill(Color.BLACK);
        label1.setFont(Font.font("Calibri", FontWeight.BOLD, 17));

        // Label for the motorBikeInfoTable
        Label label2 = new Label("Rental MotorBike Information");
        label2.setTextFill(Color.BLACK);
        label2.setFont(Font.font("Calibri", FontWeight.BOLD, 17));

        //NextButton
        nextButton = new Button();
        nextButton.setText("Next");
        nextButton.setOnAction(this);

        //Search (filter)-----------------------------------

        // Label for the Filter the Vehicle
        Label label3 = new Label("Filter Vehicles");
        label3.setTextFill(Color.BLACK);
        label3.setFont(Font.font("Calibri", FontWeight.BOLD, 17));

        //Drop down menu (choice box)
        choiceBox = new ChoiceBox<String>();

        //getItems returns the ObservableList object which you can add items to
        choiceBox.getItems().add("Model");
        choiceBox.getItems().add("Engine Capacity");
        choiceBox.getItems().add("Make");

        //Set a default value
        choiceBox.setValue("Model");

        //goButton
        goButton = new Button("Go");
        goButton.setOnAction(this);

        //clearButton
        clearButton = new Button("Clear");
        clearButton.setOnAction(this);

        //Textbox to filtering
        filtering = new TextField();

        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(choiceBox,filtering,goButton,clearButton);
        hbox2.setSpacing(50);


        //Table1 - carInfoTable---------------------------------------------------------------

        //PlateNo column
        TableColumn<Car, String> PlateNoColumn = new TableColumn<>("PlateNo");
        PlateNoColumn.setMinWidth(200);
        PlateNoColumn.setCellValueFactory(new PropertyValueFactory<>("plateNo"));

        //Make column
        TableColumn<Car, String> MakeColumn = new TableColumn<>("Make");
        MakeColumn.setMinWidth(100);
        MakeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));

        //Model column
        TableColumn<Car, String> ModelColumn = new TableColumn<>("Model");
        ModelColumn.setMinWidth(100);
        ModelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        //RentalPrice column
        TableColumn<Car, String> RentalPriceColumn = new TableColumn<>("RentalPrice");
        RentalPriceColumn.setMinWidth(100);
        RentalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("rentalPrice"));

        //EngineCapacity column
        TableColumn<Car, String> EngineCapacityColumn = new TableColumn<>("EngineCapacity (cc)");
        EngineCapacityColumn.setMinWidth(100);
        EngineCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("engineCapacity"));

        //AirCondition column
        TableColumn<Car, String> AirConditionColumn = new TableColumn<>("AirCondition");
        AirConditionColumn.setMinWidth(100);
        AirConditionColumn.setCellValueFactory(new PropertyValueFactory<>("hasAirCondition"));

        //NoOfSeat column
        TableColumn<Car, String> NoOfSeatsColumn = new TableColumn<>("NoOfSeats");
        NoOfSeatsColumn.setMinWidth(100);
        NoOfSeatsColumn.setCellValueFactory(new PropertyValueFactory<>("noOfSeats"));


        carInfoTable = new TableView<>();
        carInfoTable.setPrefWidth(450);
        carInfoTable.setPrefHeight(300);
        carInfoTable.setItems(getProductCar());
        carInfoTable.getColumns().addAll(PlateNoColumn ,MakeColumn,ModelColumn,RentalPriceColumn,EngineCapacityColumn,AirConditionColumn,NoOfSeatsColumn);


        //Table2 - motorBikeInfoTable---------------------------------------------------------------

        //PlateNo column
        TableColumn<MotorBike, String> PlateNoColumn1 = new TableColumn<>("PlateNo");
        PlateNoColumn1.setMinWidth(200);
        PlateNoColumn1.setCellValueFactory(new PropertyValueFactory<>("plateNo"));

        //Make column
        TableColumn<MotorBike, String> MakeColumn1 = new TableColumn<>("Make");
        MakeColumn1.setMinWidth(100);
        MakeColumn1.setCellValueFactory(new PropertyValueFactory<>("make"));

        //Model column
        TableColumn<MotorBike, String> ModelColumn1 = new TableColumn<>("Model");
        ModelColumn1.setMinWidth(100);
        ModelColumn1.setCellValueFactory(new PropertyValueFactory<>("model"));

        //RentalPrice column
        TableColumn<MotorBike, String> RentalPriceColumn1 = new TableColumn<>("RentalPrice");
        RentalPriceColumn1.setMinWidth(100);
        RentalPriceColumn1.setCellValueFactory(new PropertyValueFactory<>("rentalPrice"));

        //EngineCapacity column
        TableColumn<MotorBike, String> EngineCapacityColumn1 = new TableColumn<>("EngineCapacity (cc)");
        EngineCapacityColumn1.setMinWidth(100);
        EngineCapacityColumn1.setCellValueFactory(new PropertyValueFactory<>("engineCapacity"));

        //HelmetAvailable column
        TableColumn<MotorBike, String> HelmetAvailableColumn = new TableColumn<>("HelmetAvailable");
        HelmetAvailableColumn.setMinWidth(100);
        HelmetAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("helmetAvailable"));

        //StorageAvailable column
        TableColumn<MotorBike, String> StorageAvailableColumn = new TableColumn<>("StorageAvailable");
        StorageAvailableColumn.setMinWidth(100);
        StorageAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("storageAvailable"));


        motorBikeInfoTable = new TableView<>();
        motorBikeInfoTable.setPrefWidth(450);
        motorBikeInfoTable.setPrefHeight(300);
        motorBikeInfoTable.setItems(getProductMotorBike());
        motorBikeInfoTable.getColumns().addAll(PlateNoColumn1 ,MakeColumn1,ModelColumn1,RentalPriceColumn1,EngineCapacityColumn1,HelmetAvailableColumn,StorageAvailableColumn);

        //Scene2 -> Checking Vehicle Availability and Reserving--------------------------------------

        // Label for the Scene topic
        Label label4 = new Label("Reservation Of Vehicle");
        label4.setTextFill(Color.BLACK);
        label4.setFont(Font.font("Calibri", FontWeight.BOLD, 27));

        //Layout
        HBox hbox3 = new HBox();
        hbox3.setAlignment(Pos.CENTER);
        hbox3.getChildren().add(label4);
        GridPane.setConstraints(hbox3, 9, 0);

        //Client Information -----------------------------------------------------
        // Client Info
        Label label5 = new Label("Client Info");
        label5.setTextFill(Color.BLACK);
        label5.setFont(Font.font("Calibri", FontWeight.BOLD, 17));
        GridPane.setConstraints(label5,8 , 1);

        //Name Label - constrains use (child, column, row)
        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 8, 2);

        //Name Input
        nameInput1 = new TextField();
        GridPane.setConstraints(nameInput1, 9, 2);

        //Contact No Label
        Label ContactLabel = new Label("Contact No");
        GridPane.setConstraints(ContactLabel, 8, 3);

        //Contact No Input
        nameInput2 = new TextField();
        GridPane.setConstraints(nameInput2, 9, 3);

        //Email Label
        Label EmailLabel = new Label("Email");
        GridPane.setConstraints(EmailLabel, 8, 4);

        //Email Input
        EmailInput = new TextField();
        EmailInput.setPromptText("apple@gamil.com");
        GridPane.setConstraints(EmailInput, 9, 4);

        //Address Label
        Label AddressLabel = new Label("Address");
        GridPane.setConstraints(AddressLabel, 8, 5);

        //Address Input
        AddressLabelInput = new TextField();
        GridPane.setConstraints(AddressLabelInput, 9, 5);

        //No of date to reserve label
        NoOfReservationDaysLabel= new Label("No of Reservation Days") ;
        GridPane.setConstraints(NoOfReservationDaysLabel, 8, 6);

        //No of date to reserve Input
        NoOfReservationDaysInput = new TextField();
        GridPane.setConstraints(NoOfReservationDaysInput, 9, 6);

        //PickUpDate label
        PickUpDate= new Label("PickUpDate") ;
        GridPane.setConstraints(PickUpDate, 8, 7);

        //PickUp Date Input
        date = new DatePicker();
        date.setPromptText("Pick up Date");
        date.setMaxWidth(300);
        GridPane.setConstraints(date, 9, 7);

        //Reserve Button
        ReserveButton = new Button("Reserve");
        ReserveButton.setOnAction(this);
        GridPane.setConstraints(ReserveButton,9, 8);


        //Vehicle Information ------------------------------------------------------
        // Vehicle Info
        Label label6 = new Label("Vehicle Info");
        label6.setTextFill(Color.BLACK);
        label6.setFont(Font.font("Calibri", FontWeight.BOLD, 17));
        GridPane.setConstraints(label6,0, 1);

        //Vehicle Type
        Label label7 = new Label("Vehicle Type");
        GridPane.setConstraints(label7,0, 2);

        //Drop down menu (choice box)
        choiceBox1 = new ChoiceBox<String>();

        //getItems returns the ObservableList object which you can add items to
        choiceBox1.getItems().add("Car");
        choiceBox1.getItems().add("MotorBike");
        GridPane.setConstraints(choiceBox1,1, 2);

        //Set a default value
        choiceBox1.setValue("Car");

        //Plate No
        Label label8 = new Label("Plate No");
        GridPane.setConstraints(label8,0, 3);

        // Plate No Input
        PlateNoInput = new TextField();
        GridPane.setConstraints(PlateNoInput,1, 3);

        //CheckAvailability Button
        CheckAvailabilityButton = new Button("CheckAvailability");
        CheckAvailabilityButton.setOnAction(this);
        GridPane.setConstraints(CheckAvailabilityButton,0, 4);

        //label for checkAvailability No
        label9 = new Label();
        GridPane.setConstraints(label9,0, 5 );

        //Clear Button
        clearButton1 = new Button("Clear");
        clearButton1.setOnAction(this);

        //Back Button
        backButton1 = new Button("Back");
        backButton1.setOnAction(this);

        // using hbox to align the Clear button and Back button
        HBox hbox1 = new HBox();
        hbox1.setSpacing(90);
        hbox1.getChildren().addAll(backButton1,clearButton1);
        GridPane.setConstraints(hbox1, 0, 12);


        //Scene3 -> ------------------------------------------------------------
        area = new TextArea();
        double height =500; //making a variable called height with a value 400
        area.setPrefHeight(height);  //sets height of the TextArea to 400 pixels
        area.setFont(Font.font("Calibri", FontWeight.BOLD, 17));

        //Close Button
        closeButton = new Button("Close");
        closeButton.setOnAction(this);

        //Back Button
        backButton2 = new Button("Back");
        backButton2.setOnAction(this);

        // using hbox to align the ok button and Back button
        HBox hbox4 = new HBox();
        hbox4.setSpacing(90);
        hbox4.getChildren().addAll(backButton2, closeButton );

        //Layouts-------------------------------------------------------------------------------------

        //layouts setup for Scene 1-----------------------
        // Vbox
        VBox vBox1 = new VBox(20);
        vBox1.setPadding(new Insets(25, 25, 25, 25));;
        vBox1.getChildren().addAll(hbox,label3, hbox2, label1,carInfoTable,label2,motorBikeInfoTable,nextButton);

        //Layouts setup for Scene 2------------------------
        //GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setVgap(20);
        grid.setHgap(10);
        //Add everything to grid
        grid.getChildren().addAll(hbox3,label5,nameLabel, nameInput1,ContactLabel,nameInput2, EmailLabel,EmailInput,AddressLabel,AddressLabelInput,NoOfReservationDaysLabel,NoOfReservationDaysInput,PickUpDate,date,label6,hbox1,label7,choiceBox1,label8,PlateNoInput,CheckAvailabilityButton,label9,ReserveButton);

        //layouts setup for Scene 3------------------------
        //vBox
        VBox vBox2 = new VBox(20);
        vBox2.setPadding(new Insets(25, 25, 25, 25));;
        vBox2.getChildren().addAll(area,hbox4);

        //using ID to Styling
        vBox1.setId("Scene1");
        grid.setId("Scene2");
        vBox2.setId("Scene3");

        //scene and stage setup--------------------------------------------
        scene1 = new Scene(vBox1, 900, 650);
        //Setting styling for the scene1
        scene1.getStylesheets().add(getClass().getClassLoader().getResource("com/farheen/View/GraphicalUserInterface.css").toExternalForm());
        scene2 = new Scene(grid,900, 900);
        //Setting styling for the scene2
        scene2.getStylesheets().add(getClass().getClassLoader().getResource("com/farheen/View/GraphicalUserInterface.css").toExternalForm());
        scene3 = new Scene(vBox2,900,900);
        //Setting styling for the scene3
        scene3.getStylesheets().add(getClass().getClassLoader().getResource("com/farheen/View/GraphicalUserInterface.css").toExternalForm());
        window.setScene(scene1);
        window.show();

    }

    //Get all of the Car Products
    public ObservableList<Car> getProductCar()
    {
        //Creating a database class object
        Database db = new Database();
        ObservableList<Car> carProduct = FXCollections.observableArrayList();
        List<Vehicle> VehicleProducts = new ArrayList<Vehicle>();
        VehicleProducts = db.getVehicle();
        for(Vehicle vehicleItem : VehicleProducts)
        {
            if(vehicleItem instanceof Car)
            {
                carProduct.add((Car) vehicleItem);
            }
        }
        return carProduct;
    }

    //Get all of the MotorBike Products
    public ObservableList<MotorBike> getProductMotorBike()
    {
        //Creating a database class object
        Database db = new Database();
        ObservableList<MotorBike> motorBikesProduct = FXCollections.observableArrayList();
        List<Vehicle> VehicleProducts = new ArrayList<Vehicle>();
        VehicleProducts = db.getVehicle();
        for(Vehicle vehicleItem : VehicleProducts)
        {
            if(vehicleItem instanceof MotorBike)
            {
                motorBikesProduct.add((MotorBike) vehicleItem);
            }
        }
        return motorBikesProduct;
    }


    //handle method which is use to handel the action takes place in JAVAFX components
    @Override
    public void handle(ActionEvent event)
    {
        Database db = new Database();
        if(event.getSource()== nextButton)
        {
            window.setScene(scene2);
        }

        if(event.getSource()== backButton1)
        {
            window.setScene(scene1);
        }

        if(event.getSource()== okButton)
        {
            window.setScene(scene3);
        }

        if(event.getSource()== goButton)
        {
            String filterParametersFromChoiceBox = choiceBox.getValue();
            String filterParametersFromTextBox = filtering.getText();

            System.out.println(filterParametersFromChoiceBox);
            System.out.println(filterParametersFromTextBox);

            ObservableList<Car> carProduct = FXCollections.observableArrayList();
            ObservableList<MotorBike> motorBikesProduct = FXCollections.observableArrayList();

            List<Vehicle> VehicleProducts;

            VehicleProducts = db.filter(filterParametersFromChoiceBox,filterParametersFromTextBox);

            for(Vehicle vehicleItem : VehicleProducts)
            {
                if(vehicleItem instanceof Car)
                {
                    carProduct.add((Car) vehicleItem);
                }
                if(vehicleItem instanceof MotorBike)
                {
                    motorBikesProduct.add((MotorBike) vehicleItem);
                }
            }

            carInfoTable.setItems(carProduct);
            motorBikeInfoTable.setItems(motorBikesProduct);

        }

        if(event.getSource()== clearButton)
        {
            filtering.clear();
            carInfoTable.setItems(getProductCar());
            motorBikeInfoTable.setItems(getProductMotorBike());
        }

        if(event.getSource()== clearButton1)
        {
            nameInput1.clear();
            AddressLabelInput.clear();
            nameInput2.clear();
            EmailInput.clear();
            PlateNoInput.clear();
            NoOfReservationDaysInput.clear();
            label9.setText("");
            date.setValue(null);
        }

        if(event.getSource()== CheckAvailabilityButton){
            window.setScene(scene2);
            try
            {
                System.out.println(PlateNoInput.getText());
                System.out.println(choiceBox1.getValue());
                System.out.println(db.findInStore(PlateNoInput.getText() ,choiceBox1.getValue()));
                label9.setText(db.findInStore(PlateNoInput.getText(),choiceBox1.getValue()));

            }catch (Exception e)
            {
                label9.setText(e.getMessage());
                System.out.println(e.getMessage());
            }
        }



        if(event.getSource() == closeButton)
        {
            window.close();

        }

        if(event.getSource()== ReserveButton)
        {
            window.setScene(scene3);
            Customer customer = new Customer(nameInput1.getText(),nameInput2.getText(),EmailInput.getText(),AddressLabelInput.getText());
            System.out.println(date.getValue());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date pickUpDate = null;
            Date dropOffDate = null;
            Calendar c = Calendar.getInstance();

            //Exception handelling
            try
            {
                //Validating if the user has not gone through the reservation
                try{
                    c.setTime(formatter.parse(date.getValue().toString()));
                    pickUpDate = formatter.parse(date.getValue().toString());
                }catch (Exception e){
                    area.clear();
                    area.setText(area.getText()+"Invalid Input Entered,please fill the form\n");
                    System.out.println("Fill the form");
                }


                //Validation for the user input
                boolean isValid = true;
                try{
                    c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(NoOfReservationDaysInput.getText()));
                }catch (Exception e){
                    System.out.println("Invalid Input Entered");
                    isValid = false;
                }

                String newDate = formatter.format(c.getTime());
                dropOffDate = formatter.parse(newDate);

                Reserve flag = null;
                if((!PlateNoInput.equals("") && isValid) == true){

                    flag  = db.addToReservation(customer,PlateNoInput.getText(),pickUpDate,dropOffDate);

                    area.setText("****************************************\n" );
                    area.setText(area.getText()+" Reserved Vehicle Report \n" );
                    area.setText(area.getText()+"****************************************\n" );
                    area.setText(area.getText()+"Name : "+ flag.getClient().getName()+"\n" );
                    area.setText(area.getText()+"Contact No : "+ flag.getClient().getContactNo()+"\n" );
                    area.setText(area.getText()+"Email : "+ flag.getClient().getEmail()+"\n" );
                    area.setText(area.getText()+"Address : "+ flag.getClient().getAddress()+"\n" );
                    area.setText(area.getText()+"Pick Up Date : "+ flag.getVehicle().getScheduleSlot().getPickUpDate()+"\n" );
                    area.setText(area.getText()+"Drop of Date : "+ flag.getVehicle().getScheduleSlot().getDropOffDate()+"\n" );
                    area.setText(area.getText()+"Rental price: Rs"+(Integer.parseInt(NoOfReservationDaysInput.getText()))*( Integer.parseInt(flag.getVehicle().getRentalPrice().toString()))+"\n\n" );
                    area.setText(area.getText()+"THANK YOU !!!!" );

                    System.out.println(flag.toString());

                }else {
                    area.setText(area.getText()+"Vehicle Availability is not Checked\n");
                    area.setText(area.getText()+"Invalid Input Entered\n");
                }
            } catch (ParseException e)
            {
                e.printStackTrace();

            }
        }

        if(event.getSource()== backButton2)
        {
            window.setScene(scene2);
        }

    }

}
