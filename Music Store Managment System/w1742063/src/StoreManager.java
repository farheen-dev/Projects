
// Store manager inteface class
public interface StoreManager {

    // Method headers
    public void add(MusicItem item);
    public boolean delete(String itemID);
    public void printList();
    public void sort();
   // public void search(String title);
    public void buy(String itemId);
    public void generateReport() throws Exception;

}
