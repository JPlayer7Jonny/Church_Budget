import java.io.Serializable;

public class Money implements Serializable {

    private int bills100;
    private int bills50;
    private int bills20;
    private int bills10;
    private int bills5;
    private int bills1;
    private double coins;

    public Money() {

        bills100 = 0;
        bills50 = 0;
        bills20 = 0;
        bills10 = 0;
        bills5 = 0;
        bills1 = 0;
        coins = 0;
    }

    public Money(int bills100, int bills50, int bills20, int bills10, int bills5, int bills1, double coins) {

        this.bills100 = bills100;
        this.bills50 = bills50;
        this.bills20 = bills20;
        this.bills10 = bills10;
        this.bills5 = bills5;
        this.bills1 = bills1;
        this.coins = coins;
    }

    public Money(Money obj) {

        bills100 = obj.bills100;
        bills50 = obj.bills50;
        bills20 = obj.bills20;
        bills10 = obj.bills10;
        bills5 = obj.bills5;
        bills1 = obj.bills1;
        coins = obj.coins;
    }

    public void set100(int bills100) {
        this.bills100 = bills100;
    }

    public void set50(int bills50) {
        this.bills50 = bills50;
    }

    public void set20(int bills20) {
        this.bills20 = bills20;
    }

    public void set10(int bills10) {
        this.bills10 = bills10;
    }

    public void set5(int bills5) {
        this.bills5 = bills5;
    }

    public void set1(int bills1) {
        this.bills1 = bills1;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public int get100() {
        return bills100;
    }

    public int get50() {
        return bills50;
    }

    public int get20() {
        return bills20;
    }

    public int get10() {
        return bills10;
    }

    public int get5() {
        return bills5;
    }

    public int get1() {
        return bills1;
    }

    public double getCoins() {
        return coins;
    }

    public double getAmount() {

        return (double) (100 * bills100) + (50 * bills50) + (20 * bills20) + (10 * bills10) + (5 * bills5) + bills1
        + coins;
    }

    public boolean equals(Money obj) {
        return (getAmount() == obj.getAmount());
    }

    public boolean greater(Money obj) {
        return (getAmount() > obj.getAmount());
    }

    public boolean lesser(Money obj) {
        return (getAmount() < obj.getAmount());
    }

    public Money copy() {

        Money copy = new Money(bills100, bills50, bills20, bills10, bills5, bills1, coins);

        return copy;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder("Amount: $ ");
        sb.append(Year.df.format(getAmount()));

        return sb.toString();
    }
}
