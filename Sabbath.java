import java.io.Serializable;

public class Sabbath implements Serializable {

    private Money offerings;
    private Money tithes;
    private String date;

    public Sabbath() {

        offerings = new Money();
        tithes = new Money();
        date = "N/A";
    }

    public Sabbath(Money offerings, Money tithes, String date) {

        this.offerings = offerings;
        this.tithes = tithes;
        this.date = date;
    }

    public Sabbath(Sabbath obj) {

        offerings = obj.offerings;
        tithes = obj.tithes;
        date = obj.date;
    }

    public void setOfferings(Money offerings) {
        this.offerings = offerings;
    }

    public void setTithes(Money tithes) {
        this.tithes = tithes;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Money getOfferings() {
        return offerings;
    }

    public Money getTithes() {
        return tithes;
    }

    public String getDate() {
        return date;
    }

    public Sabbath copy() {

        Sabbath copy = new Sabbath(offerings, tithes, date);

        return copy;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder(date);
        sb.append("\n   Estimated Offerings: $");
        sb.append(Year.df.format(offerings.getAmount()));
        sb.append("\n   Estimated Tithes: $");
        sb.append(Year.df.format(tithes.getAmount()));

        return sb.toString();
    }

    public String detailedReport() {

        StringBuilder sb = new StringBuilder("\n   " + date);
        sb.append("\n   Estimated Offerings:");
        sb.append("\n       100 dollar bills: ");
        sb.append(offerings.get100());
        sb.append("\n       50 dollar bills:  ");
        sb.append(offerings.get50());
        sb.append("\n       20 dollar bills:  ");
        sb.append(offerings.get20());
        sb.append("\n       10 dollar bills:  ");
        sb.append(offerings.get10());
        sb.append("\n       5 dollar bills:   ");
        sb.append(offerings.get5());
        sb.append("\n       1 dollar bills:   ");
        sb.append(offerings.get1());
        sb.append("\n       coins amount:   $");
        sb.append(Year.df.format(offerings.getCoins()));
        sb.append("\n           Subtotal:  $");
        sb.append(Year.df.format(offerings.getAmount()));

        sb.append("\n   Estimated Tithes:");
        sb.append("\n       100 dollar bills: ");
        sb.append(tithes.get100());
        sb.append("\n       50 dollar bills:  ");
        sb.append(tithes.get50());
        sb.append("\n       20 dollar bills:  ");
        sb.append(tithes.get20());
        sb.append("\n       10 dollar bills:  ");
        sb.append(tithes.get10());
        sb.append("\n       5 dollar bills:   ");
        sb.append(tithes.get5());
        sb.append("\n       1 dollar bills:   ");
        sb.append(tithes.get1());
        sb.append("\n       coins amount:   $");
        sb.append(Year.df.format(tithes.getCoins()));
        sb.append("\n           Subtotal:  $");
        sb.append(Year.df.format(tithes.getAmount()) + "\n");

        return sb.toString();
    }
}
