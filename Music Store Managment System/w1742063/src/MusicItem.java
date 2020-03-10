import java.math.BigDecimal;
import java.util.Objects;

public abstract class MusicItem implements Comparable<MusicItem> {

    //Declaring variable names
    protected String itemID;
    protected String title;
    protected String genre;
    protected String releaseDate;
    protected String artist;
    protected BigDecimal price;

    // Using constructor to intialize instance variable
    public MusicItem(String itemID, String title, String genre, String releaseDate, String artist, BigDecimal price) {
        this.itemID = itemID;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.price = price;
    }

    // get method for itemID
    public String getItemID() { return itemID; }

    // get method for title variable
    public String getTitle() {
        return title;
    }

    // get method for genre variable
    public String getGenre() {
        return genre;
    }

    // get method for releaseDate variable
    public String getReleaseDate() {
        return releaseDate;
    }

    // get method for artist variable
    public String getArtist() {
        return artist;
    }

    // get method for price variable
    public BigDecimal getPrice() {
        return price;
    }

    //override equal method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MusicItem)) return false;
        MusicItem musicItem = (MusicItem) o;
        return itemID.equals(musicItem.itemID) &&
                title.equals(musicItem.title) &&
                genre.equals(musicItem.genre) &&
                releaseDate.equals(musicItem.releaseDate) &&
                artist.equals(musicItem.artist) &&
                price.equals(musicItem.price);
    }

    //override hashcode method
    @Override
    public int hashCode() {
        return Objects.hash(itemID, title, genre, releaseDate, artist, price);
    }

    //override toString method
    @Override
    public String toString() {
        return "MusicItem{" +
                "itemID='" + itemID + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", artist='" + artist + '\'' +
                ", price=" + price +
                '}';
    }

    //override compareTO method
    @Override
    public int compareTo(MusicItem obj)
    {
        return this.title.compareTo(obj.getTitle());
    }

}
