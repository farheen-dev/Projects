import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;

public class Database  {

    //Connecting to the database
    MongoClient client = new MongoClient("localhost", 27017);
    //creating a database
    MongoDatabase database = client.getDatabase("WestminsterMusicStoreManager");
    MongoCollection Storecollection = database.getCollection("storeItem");
    MongoCollection boughtcollection = database.getCollection("boughtItem");
    Document document = new Document();
    Scanner input = new Scanner(System.in);

    //addToStore method to add items to the database
    public void addToStore( MusicItem item){
        if(item instanceof CD)
        {
            document.put("ItemId", item.getItemID());
            document.put("Title", item.getTitle());
            document.put("Genre", item.getGenre());
            document.put("ReleaseDate", item.getReleaseDate());
            document.put("Artist", item.getArtist());
            document.put("Price", item.getPrice().toString());
            document.put("Duration", ((CD) item).getDuration());
            document.put("type","CD");
        }
        if(item instanceof Vinyl)
        {
            document.put("ItemId", item.getItemID());
            document.put("Title", item.getTitle());
            document.put("Genre", item.getGenre());
            document.put("ReleaseDate", item.getReleaseDate());
            document.put("Artist", item.getArtist());
            document.put("Price", item.getPrice().toString());
            document.put("Speed", ((Vinyl) item).getSpeed());
            document.put("Diameter",((Vinyl) item).getDiameter());
            document.put("type","Vinyl");
        }

        Storecollection.insertOne(document);
        client.close();
        System.out.println();
    }

    //checkItemIsAvailable method to search items in the database
    public  boolean checkItemIsAvailable(String key){
        FindIterable<Document> findIterable = Storecollection.find(eq("ItemId",key ));

        if(findIterable.first() != null){
            return true;
        }
        else{
            return  false;
        }
    }

    //addToBoughtItems method to buy item in the database
    public void addToBoughtItems(String key,int quantity){

        FindIterable<Document> findIterable = Storecollection.find(eq("ItemId",key ));

            document  =   findIterable.first();
            String price = document.get("Price").toString();
            BigDecimal bd = new BigDecimal(price);
            document.put("Quantity",quantity);
            BigDecimal totalPrice = (bd.multiply(new BigDecimal(quantity)));
            System.out.println("Total Cost:"+totalPrice);
            document.put("TotalAmount",totalPrice.toString());

            boughtcollection.insertOne(document);

            client.close();
            System.out.println();



    }

    //getSizeOfStore Items method to get the no of item in the database
    public long getSizeOfStore()
    {
        return Storecollection.count();
    }

    //sortStore() method to srt items
    public void sortStore()
    {
        ShowItems("sort");

    }

    // findInStore method to search items
    public String findInStore(String key){

        FindIterable<Document> findIterable = Storecollection.find(eq("Title",key ));
        String item =" ";
        if(findIterable.first() == null){
            item = "There is no such item with that title";
        }else{
            //use try and catch to catch exception occurs
           try {
               if( findIterable.first().get("type").equals("CD"))
               {
                   item =  "ItemId :"+findIterable.first().get("ItemId").toString()+", "+
                           "Title :"+findIterable.first().get("Title").toString()+", "+
                           "Genre :"+findIterable.first().get("Genre").toString()+", "+
                           "ReleaseDate :"+findIterable.first().get("ReleaseDate").toString()+" "+
                           "Artist :"+findIterable.first().get("Artist").toString()+" "+
                           "Price :"+findIterable.first().get("Price").toString()+", "+
                           "Duration :"+findIterable.first().get("Duration").toString()+" ";
               }
               if(findIterable.first().get("type").equals("Vinyl")){

                   item =  "ItemId :"+findIterable.first().get("ItemId").toString()+", "+
                           "Title :"+findIterable.first().get("Title").toString()+", "+
                           "Genre :"+findIterable.first().get("Genre").toString()+", "+
                           "ReleaseDate :"+findIterable.first().get("ReleaseDate").toString()+" "+
                           "Artist :"+findIterable.first().get("Artist").toString()+" "+
                           "Price :"+findIterable.first().get("Price").toString()+", "+
                           "Speed :"+findIterable.first().get("Speed").toString()+", "+
                           "Diameter :"+findIterable.first().get("Diameter").toString();
               }

           }catch (Exception e){
               System.out.println(e.getMessage()+"Exception");
           }

        }
        return item;
    }

    //displayStore method to display items in the database
    public void displayStore(){
        List<Document> foundDocument = (List<Document>) Storecollection.find().into(new ArrayList<Document>());

        List<Document> cd = new ArrayList<Document>();
        List<Document> vinyl = new ArrayList<Document>();

        for (Document item:foundDocument) {
            if( item.get("type").equals("CD"))
            {
                cd.add(item);
            }

            if(item.get("type").equals("Vinyl")){
                vinyl.add(item);
            }
        }
        System.out.println("List of CD");
        for (Document cd_item:cd)
        {
            System.out.print("ItemId:"+cd_item.get("ItemId")+" ");
            System.out.print("Title:"+cd_item.get("Title"));
            System.out.println();
        }
        System.out.println();
        System.out.println("List of Vinyl");
        for (Document vinyl_item:vinyl)
        {
            System.out.print("ItemId:"+vinyl_item.get("ItemId")+" ");
            System.out.print("Title:"+vinyl_item.get("Title"));
            System.out.println();
        }
    }

    // getReport method to generate report
    public String getReport(){
        List<Document> foundDocument = (List<Document>) boughtcollection.find().into(new ArrayList<Document>());
        String report=" ";
        for (Document item:foundDocument) {
            report += " "+item.toString()+" "+System.lineSeparator();
        }

        return report;
    }

    // deleteItem method to delete item from the database
    public boolean deleteItem(String key){
        FindIterable<Document> findIterable = Storecollection.find(eq("ItemId",key ));
        System.out.println(findIterable.first().get("type").toString());
        Storecollection.deleteOne(new Document("ItemId", key));
        return true;
    }

    //ShowItems method to show items in database
    public void ShowItems(String flag){

        List<Document> foundDocument = new ArrayList<Document>();

        if(flag.equals("sort"))
        {
            foundDocument = (List<Document>) Storecollection.find().sort(ascending("Title")).into(new ArrayList<Document>());

        }else{
            foundDocument = (List<Document>) Storecollection.find().into(new ArrayList<Document>());

        }

        List<Document> cd = new ArrayList<Document>();
        List<Document> vinyl = new ArrayList<Document>();

        for (Document item:foundDocument) {
            if( item.get("type").equals("CD"))
            {
                cd.add(item);
            }

            if(item.get("type").equals("Vinyl")){
                vinyl.add(item);
            }
        }
        System.out.println("List of CD");
        for (Document cd_item:cd)
        {
            System.out.print("ItemId:"+cd_item.get("ItemId")+", ");
            System.out.print("Title:"+cd_item.get("Title")+", ");
            System.out.print("Genre:"+cd_item.get("Genre")+", ");
            System.out.print("ReleaseDate:"+cd_item.get("ReleaseDate")+", ");
            System.out.print("Artist:"+cd_item.get("Artist")+", ");
            System.out.print("Price:"+cd_item.get("Price")+", ");
            System.out.print("Duration:"+cd_item.get("Duration")+" ");
            System.out.println();
        }
        System.out.println();
        System.out.println("List of Vinyl");
        for (Document vinyl_item:vinyl)
        {
            System.out.print("ItemId:"+vinyl_item.get("ItemId")+", ");
            System.out.print("Title:"+vinyl_item.get("Title")+", ");
            System.out.print("Genre:"+vinyl_item.get("Genre")+", ");
            System.out.print("ReleaseDate:"+vinyl_item.get("ReleaseDate")+", ");
            System.out.print("Artist:"+vinyl_item.get("Artist")+", ");
            System.out.print("Price:"+vinyl_item.get("Price")+", ");
            System.out.print("Speed:"+vinyl_item.get("Speed")+", ");
            System.out.print("Diameter:"+vinyl_item.get("Diameter"));
            System.out.println();
        }
    }

    public List<MusicItem> getItems(){

            List<Document> foundDocument = (List<Document>) Storecollection.find().sort(ascending("Title")).into(new ArrayList<Document>());

        List<Document> cd = new ArrayList<Document>();
        List<Document> vinyl = new ArrayList<Document>();

        for (Document item:foundDocument) {
            if( item.get("type").equals("CD"))
            {
                cd.add(item);
            }

            if(item.get("type").equals("Vinyl")){
                vinyl.add(item);
            }
        }

        List<MusicItem> m = new ArrayList<MusicItem>();
        //System.out.println(cd);
       // System.out.println(vinyl);

        for (Document cd_item:cd)
        {
            if (cd_item == null) {
                continue; // or break, whatever is better in your case
            }
            try {
                CD c = new CD(cd_item.get("ItemId").toString(),cd_item.get("Title").toString(),cd_item.get("Genre").toString(),cd_item.get("ReleaseDate").toString(),cd_item.get("Artist").toString(),new BigDecimal(cd_item.get("Price").toString()),Integer.parseInt(cd_item.get("Duration").toString()));
                m.add(c);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

        for (Document vinyl_item:vinyl)

        {
            if (vinyl_item == null) {
                continue; // or break, whatever is better in your case
            }
            try {
                Vinyl v = new Vinyl(vinyl_item.get("ItemId").toString(),vinyl_item.get("Title").toString(),vinyl_item.get("Genre").toString(),vinyl_item.get("ReleaseDate").toString(),vinyl_item.get("Artist").toString(),new BigDecimal(vinyl_item.get("Price").toString()),Integer.parseInt(vinyl_item.get("Speed").toString()),Integer.parseInt(vinyl_item.get("Diameter").toString()));
                m.add(v);

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
      //  System.out.println(m);
        return m;
    }
}