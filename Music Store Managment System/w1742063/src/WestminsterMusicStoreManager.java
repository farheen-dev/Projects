import com.sun.jndi.toolkit.url.UrlUtil;
import javax.print.DocFlavor;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

public class WestminsterMusicStoreManager implements StoreManager {

    //Creating an object for Scanner method
    public static Scanner input = new Scanner(System.in);
    //assigning 1000 to MAX_COUNT which is constant
    private  static final long MAX_COUNT=1000;

    //override the add method
    @Override
    public void add(MusicItem item)
    {
        Database db = new Database();
        if (db.getSizeOfStore() < MAX_COUNT) {
            System.out.println("The space left is "+(MAX_COUNT-db.getSizeOfStore()));
            //using try catch to caught exceptions occur
            try {
                db.addToStore(item);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        } else {
            throw new IllegalArgumentException("Store is full. No place to add");
        }
    }

    //override the delete method
    @Override
    public boolean delete(String itemID)
    {
        boolean flag =false;
        // for handle exceptions
        try{
            Database db = new Database();
            if(db.getSizeOfStore() ==0)
            {
                throw new IllegalArgumentException("Store is empty. No items to delete");
            }

            flag = db.deleteItem(itemID);
            System.out.println("the number of free spaces left is "+(MAX_COUNT-db.getSizeOfStore()));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return flag;
    }

    //override the printList method
    @Override
    public void printList()
    {
        try {
            Database db = new Database();
            db.displayStore();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //override the sort method
    @Override
    public void sort()
    {
       try {
           Database db = new Database();
           db.sortStore();
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
    }

    //override the buy method
    @Override
    public void buy(String itemId)
    {
        try {
            Database db = new Database();

            while (!db.checkItemIsAvailable(itemId))
            {
                System.out.println("There is no such itemID Found!");
                System.out.print("Enter ItemID:");
                itemId = input.nextLine();
            }

            System.out.print("Enter Amount of Product you want to buy?");
            while (!input.hasNextInt()) {
                System.out.println("Invalid Input \n");
                System.out.print("Enter Amount of Product you want to buy?");
                input.next();
            }
            int quantity = input.nextInt();
           db.addToBoughtItems(itemId,quantity);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //override the generateReport of the bought item in ta file
    @Override
    public void generateReport() throws Exception
    {
        Random rd= new Random();
        Database db = new Database();
        int i = rd.nextInt();
        String report  = "Report"+i;
        File file = new File("E:\\"+report+".txt");

        //Create the file
        if (file.createNewFile())
        {
            System.out.println("Report is Generated!");
        } else {
            System.out.println("File already exists.");
        }

        //Write Content
        FileWriter writer = new FileWriter(file);
        writer.write(" Bought item info "+ System.lineSeparator()+db.getReport()
        );
        writer.close();

    }



}
