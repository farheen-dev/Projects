import java.math.BigDecimal;
import java.util.Objects;

public class Vinyl extends MusicItem {
    //declaring variable speed and diameter
    private  int speed;
    private  int diameter;

    // Using constructor to intialize instance variable
    public Vinyl(String itemID, String title, String genre, String releaseDate, String artist, BigDecimal price, int speed, int diameter) {
        super(itemID, title, genre, releaseDate, artist, price);
        this.speed = speed;
        this.diameter = diameter;
    }

    // get method for speed
    public int getSpeed() {
        return speed;
    }

    // get method for diameter
    public int getDiameter() {
        return diameter;
    }

    //override equal method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vinyl)) return false;
        if (!super.equals(o)) return false;
        Vinyl vinyl = (Vinyl) o;
        return speed == vinyl.speed &&
                diameter == vinyl.diameter;
    }

    //override hashcode method
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), speed, diameter);
    }

    //override toString method
    @Override
    public String toString() {
        return "Vinyl{" +
                "speed=" + speed +
                ", diameter=" + diameter +
                ", itemID='" + itemID + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", artist='" + artist + '\'' +
                ", price=" + price +
                '}';
    }
}
