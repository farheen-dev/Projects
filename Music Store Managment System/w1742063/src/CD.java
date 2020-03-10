import java.math.BigDecimal;
import java.util.Objects;

public class CD extends MusicItem {
    //declaring variable duration
    private int duration;

    // Using constructor to intialize instance variable
    public CD(String itemID, String title, String genre, String releaseDate, String artist, BigDecimal price, int duration) {
        super(itemID, title, genre, releaseDate, artist, price);
        this.duration = duration;
    }

    // get method for duration
    public int getDuration() {
        return duration;
    }

    //override equal method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CD)) return false;
        if (!super.equals(o)) return false;
        CD cd = (CD) o;
        return duration == cd.duration;
    }

    //override hashcode method
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), duration);
    }

    //override toString method
    @Override
    public String toString() {
        return "CD{" +
                "duration=" + duration +
                ", itemID='" + itemID + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", artist='" + artist + '\'' +
                ", price=" + price +
                '}';

    }
}
