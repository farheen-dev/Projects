import java.math.BigDecimal;
import java.util.*;

public class Main  {

    //using Scanner object to take input from the keyboard
    public static Scanner input = new Scanner(System.in);
    public static WestminsterMusicStoreManager storeManager = new WestminsterMusicStoreManager();

    public static void display(String[] args) throws Exception {

        int option = 0;

        while(option != 8){
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

                //Using switch-case for the option 1,2,3,4,5,6,7,8
                switch (option)
                {
                    case 1:
                        GraphicalUserInterface.main(args);
                        break;
                    case 2:
                        System.out.println("Select the type of Music item you need to Add:");
                        System.out.println("1- CD");
                        System.out.println("2-Vinyl");
                        System.out.print("Select an item type:");

                        //User Validations for
                        while (!input.hasNextInt()) {
                            System.out.println("Invalid option entered \n");
                            System.out.print("Select an item type:");
                            input.next();
                        }
                        int opt = input.nextInt();

                        if(opt == 1 ){
                           addItemForCD();
                        }else if(opt == 2){
                            addItemForVinyl();
                        }else{
                            System.out.println("Invalid option entered ");
                        }
                        break;
                    case 3:
                        Database db = new Database();
                        System.out.print("Enter the itemID:");
                        input.nextLine();
                        String itemID = input.nextLine();
                        if(db.checkItemIsAvailable(itemID)){
                            storeManager.delete(itemID);
                        }else {
                            System.out.println("No such itemID found");
                        }

                        break;
                    case 4:
                        storeManager.printList();
                        break;
                    case 5:
                        storeManager.sort();
                        break;
                    case 6:
                        try{
                            Database d = new Database();
                            d.ShowItems("notsort");
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        System.out.println();
                        System.out.print("Enter ItemID:");
                        input.nextLine();
                        String ItemId = input.nextLine();
                        storeManager.buy(ItemId);
                        break;
                    case 7:
                          storeManager.generateReport();
                        break;
                    case 8:
                        System.out.println("Exiting the Program");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("The option entered should be in the 1-8 \n");
                }
            } while (option < 1 || option > 8);
        }

    }

    // addItemForCD method for add CD item to the store
    public static void addItemForCD()
    {

        System.out.print("Enter the ItemID:");
        input.nextLine();
        String ItemID =  input.nextLine();

        System.out.print("Enter the title:");
        String title =  input.nextLine();

        System.out.print("Enter the genre:");
        String genre = input.nextLine();

        System.out.print("Enter the releaseDate:");
        String releaseDate = input.nextLine();

        System.out.print("Enter the artist:");
        String artist = input.nextLine();

        System.out.print("Enter the price:");
        //User input validation
        while (!input.hasNextBigDecimal()) {
            System.out.println("Invalid type entered \n");
            System.out.print("Enter the price:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }
        BigDecimal price = input.nextBigDecimal();

        System.out.print("Enter the duration:");
        while (!input.hasNextInt()) {
            //User input validation
            System.out.println("Invalid type entered \n");
            System.out.print("Enter the duration:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }
        int duration = input.nextInt();

        CD cd = new CD(ItemID,title,genre,releaseDate,artist,price,duration);
        System.out.println(cd.toString());
        storeManager.add(cd);

    }

    // addItemForVinyl method for add vinyl item to the store
    public static void addItemForVinyl()
    {

        System.out.print("Enter the ItemID:");
        input.nextLine();
        String ItemID =  input.nextLine();

        System.out.print("Enter the title:");
        String title =  input.nextLine();

        System.out.print("Enter the genre:");
        String genre = input.nextLine();

        System.out.print("Enter the releaseDate:");
        String releaseDate = input.nextLine();

        System.out.print("Enter the artist:");
        String artist = input.nextLine();

        System.out.print("Enter the price:");
        //User input validation
        while (!input.hasNextBigDecimal()) {
            System.out.println("Invalid type entered \n");
            System.out.print("Enter the price:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }
        BigDecimal price = input.nextBigDecimal();

        System.out.print("Enter the speed:");
        //User input validation
        while (!input.hasNextInt()) {
            System.out.println("Invalid type entered \n");
            System.out.print("Enter the speed:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }
        int speed = input.nextInt();

        System.out.print("Enter the diameter:");
        //User input validation
        while (!input.hasNextInt()) {
            System.out.println("Invalid type entered \n");
            System.out.print("Enter the diameter:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }
        int diameter = input.nextInt();

        Vinyl vinyl = new Vinyl(ItemID, title, genre,releaseDate,artist,price,speed,diameter);
        storeManager.add(vinyl);
    }

    // displayMenu method for display the menu
    public static void displayMenu()
    {
        System.out.println("\n Menu to manage the music stock");
        System.out.println(">>");
        System.out.println("1)Search item");
        System.out.println("2)Add a new item");
        System.out.println("3)Delete an item");
        System.out.println("4)Print the list of the items ");
        System.out.println("5)Sort the item ");
        System.out.println("6)Buy an item");
        System.out.println("7)Generate a report");
        System.out.println("8)Exist \n");
        System.out.print("Enter an option:");

    }

    // main method where the compile start execution
    public static void main(String[] args) throws Exception {
       display(args);
       // Database d = new Database();
       // System.out.println(d.findInStore("ds));;

    }
}
