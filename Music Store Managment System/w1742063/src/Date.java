
public class Date {
    //declaring variable date,month,year
    private int date;
    private int month;
    private int year;

    // Using constructor to intialize instance variable
    public Date(int date, int month, int year) {
        //calling the set methods inside the constructor
        setDate(date);
        setMonth(month);
        setYear(year);
    }

    // set method for date
    public void setDate(int date) {
        if(date>0 && date<=31)
        { this.date = date;}
    }

    // set method for month
    public void setMonth(int month) {
        if(month>0 && month<=12)
        {  this.month = month;}
    }

    // set method for year
    public void setYear(int year) {
        if(year>0 && year<=5000)
        { this.year = year;}
    }


    // get method for Date
    public int getDate() {
        return date;
    }

    // get method for month
    public int getMonth() {
        return month;
    }

    // get method for year
    public int getYear() {
        return year;
    }

    //override toString method
    @Override
    public String toString() {
        return "Date{" +
                "date=" + date +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
