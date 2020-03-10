import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import org.bson.codecs.DocumentCodec;

import java.util.ArrayList;
import java.util.List;

public class GraphicalUserInterface extends Application implements EventHandler<ActionEvent> {

    // declaration of Component in the GUI
    Button searchButton,back;
    Button showItemsButton ,mainMenu;
    Label label;
    Label label1;
    Label wellabel;
    TextField textField;
    Stage window;
    Scene scene1,scene2,scene3;
    TableView<CD> tableForCD;
    TableView<Vinyl> tableForVinyl;

    public static void main(String[] args) {
        launch(args);
    }

    //overriding the start stage
    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("Westminster Music Store Management");
        VBox vBox = new VBox();


        //scene1-------------------------------------------------------------

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        wellabel = new Label("Welcome to the westminster music store");
        GridPane.setConstraints(wellabel,1,1);

        // label
        label = new Label("Enter the title name of item to search ");
        GridPane.setConstraints(label,0,5);

        //textfield
        textField = new TextField();
        GridPane.setConstraints(textField,1,5);

        //showItemsButton
        showItemsButton= new Button();
        showItemsButton.setText("Show items");
        GridPane.setConstraints(showItemsButton,0,9);
        showItemsButton.setOnAction(this);

        //search button
        searchButton = new Button();
        searchButton.setText("Search");
        GridPane.setConstraints(searchButton,2,9);
        searchButton.setOnAction(this);


        //scene2-----------------------------------------------------------------

        GridPane grid1 = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //label1
        label1 = new Label();
        GridPane.setConstraints(label,0,5);

        //mainMenu button
        mainMenu  = new Button();
        mainMenu.setText("Back to Main");
        GridPane.setConstraints(mainMenu,0,9);
        mainMenu.setOnAction(this);


        //scene3-Table1---------------------------------------------------------------

        //itemID column
        TableColumn<CD, String> ItemIDColumn = new TableColumn<>("ItemID");
        ItemIDColumn.setMinWidth(200);
        ItemIDColumn.setCellValueFactory(new PropertyValueFactory<>("itemID"));

        //title column
        TableColumn<CD, String> TitleColumn = new TableColumn<>("Title");
        TitleColumn.setMinWidth(100);
        TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        //genre column
        TableColumn<CD, String> GenreColumn = new TableColumn<>("Genre");
        GenreColumn.setMinWidth(100);
        GenreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        //releaseDate
        TableColumn<CD, String> ReleaseDateColumn = new TableColumn<>("ReleaseDate");
        ReleaseDateColumn.setMinWidth(100);
        ReleaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        //artist column
        TableColumn<CD, String> ArtistColumn = new TableColumn<>("Artist");
        ArtistColumn.setMinWidth(100);
        ArtistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));

        //price column
        TableColumn<CD, String> PriceColumn = new TableColumn<>("Price");
        PriceColumn.setMinWidth(100);
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


        //duration column
        TableColumn<CD, String> DurationColumn = new TableColumn<>("Duration");
        DurationColumn.setMinWidth(100);
        DurationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        tableForCD = new TableView<>();
        tableForCD.setItems(getProductCD());
        tableForCD.getColumns().addAll(ItemIDColumn ,TitleColumn,GenreColumn,ReleaseDateColumn,ArtistColumn,PriceColumn,DurationColumn );

        //scene3-Table2---------------------------------------------------------------

        //itemID column
        TableColumn<Vinyl, String> ItemIDColumn1 = new TableColumn<>("ItemID");
        ItemIDColumn1.setMinWidth(200);
        ItemIDColumn1.setCellValueFactory(new PropertyValueFactory<>("itemID"));

        //title column
        TableColumn<Vinyl, String> TitleColumn1 = new TableColumn<>("Title");
        TitleColumn1.setMinWidth(100);
        TitleColumn1.setCellValueFactory(new PropertyValueFactory<>("title"));

        //genre column
        TableColumn<Vinyl, String> GenreColumn1 = new TableColumn<>("Genre");
        GenreColumn1.setMinWidth(100);
        GenreColumn1.setCellValueFactory(new PropertyValueFactory<>("genre"));

        //releaseDate
        TableColumn<Vinyl, String> ReleaseDateColumn1 = new TableColumn<>("ReleaseDate");
        ReleaseDateColumn1.setMinWidth(100);
        ReleaseDateColumn1.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        //artist column
        TableColumn<Vinyl, String> ArtistColumn1 = new TableColumn<>("Artist");
        ArtistColumn1.setMinWidth(100);
        ArtistColumn1.setCellValueFactory(new PropertyValueFactory<>("artist"));

        //price column
        TableColumn<Vinyl, String> PriceColumn1 = new TableColumn<>("Price");
        PriceColumn1.setMinWidth(100);
        PriceColumn1.setCellValueFactory(new PropertyValueFactory<>("price"));

        //speed column
        TableColumn<Vinyl, String> SpeedColumn = new TableColumn<>("Speed");
        SpeedColumn.setMinWidth(100);
        SpeedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));

        //diameter column
        TableColumn<Vinyl, String> DiameterColumn = new TableColumn<>("Diameter");
        DiameterColumn.setMinWidth(100);
        DiameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));

        tableForVinyl = new TableView<>();
        tableForVinyl.setItems(getProductVinyl());
        tableForVinyl.getColumns().addAll(ItemIDColumn1 ,TitleColumn1,GenreColumn1,ReleaseDateColumn1,ArtistColumn1,PriceColumn1,SpeedColumn,DiameterColumn );

        //back button
        back  = new Button();
        back.setText("Back to Main");
        GridPane.setConstraints(back,0,9);
        back.setOnAction(this);

        //layout-------------------------------------------------

        vBox.getChildren().addAll(tableForCD,tableForVinyl,back);
        grid.getChildren().addAll(wellabel,label,textField,searchButton,showItemsButton);
        grid1.getChildren().addAll(label1,mainMenu);

        //scene objects --------------------------------------------

        scene1 = new Scene(grid,300,200) ;
        scene2 = new Scene(grid1,300,200) ;
        scene3 = new Scene(vBox) ;
        window.setScene((scene1));
        window.show();

    }

    //Creating a observable list for CD
    public ObservableList<CD> getProductCD(){
        Database db = new Database();
        ObservableList<CD> CDs = FXCollections.observableArrayList();
        List<MusicItem> musicItems = new ArrayList<MusicItem>();
        musicItems = db.getItems();
        for (MusicItem item :musicItems)
        {
            if(item instanceof CD){
                CDs.add((CD) item);
            }
        }
        return CDs;
    }

    //Creating a observable list for Vinyl
    public ObservableList<Vinyl> getProductVinyl(){
        Database db = new Database();
        ObservableList<Vinyl> Vinyls = FXCollections.observableArrayList();
        List<MusicItem> musicItems = new ArrayList<MusicItem>();
        musicItems = db.getItems();
        for (MusicItem item :musicItems)
        {
            if(item instanceof Vinyl){
                Vinyls.add((Vinyl) item);
            }
        }
        return Vinyls;
    }

    // overriding handle method to do actions
    @Override
    public void handle(ActionEvent event) {

        Database db = new Database();

            if(event.getSource()== searchButton){
                window.setScene(scene2);
                try
                {
                    System.out.println(db.findInStore(textField.getText()));
                    label1.setText(db.findInStore(textField.getText()));
                   // textField.setText(ni);

                }catch (Exception e)
                {
                    label1.setText(e.getMessage());
                    System.out.println(e.getMessage());
                }
            }

        if(event.getSource()== showItemsButton){
            try
            {
                db.ShowItems("notsort");

            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        if(event.getSource()== mainMenu){
            window.setScene(scene1);
            textField.setText("");
        }
        if(event.getSource()== back){
            window.setScene(scene1);
            textField.setText("");
        }
        if(event.getSource()== showItemsButton){
            window.setScene(scene3);
            textField.setText("");
        }
    }
}
