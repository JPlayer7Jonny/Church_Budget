import java.io.Serializable;
import java.util.ArrayList;

public class Quarter implements Serializable {

    private int quarter;
    private ArrayList<Month> months = new ArrayList<Month>(0);

    public Quarter() {

        quarter = 1;

        for (int i = 0; i < 3; i++) {

            months.add(new Month());
        }
    }

    public Quarter(int quarter, ArrayList<Month> months) {

        this.quarter = quarter;

        for (int i = 0; i < 3; i++) {

            this.months.add(months.get(i));
        }
    }

    public Quarter(Quarter obj) {

        quarter = obj.quarter;

        for (int i = 0; i < 3; i++) {

            months.add(obj.months.get(i));
        }
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public void setMonths(ArrayList<Month> months) {

        for (int i = 0; i < 3; i++) {

            this.months.set(i, months.get(i));
        }
    }

    public void editMonth(int index, Month obj) {

        months.set(index, obj);
    }

    public int getQuarter() {
        return quarter;
    }

    public ArrayList<Month> getMonths() {

        ArrayList<Month> list = new ArrayList<Month>(0);

        for (int i = 0; i < 3; i++) {

            list.add(months.get(i));
        }

        return list;
    }

    public double getQuarterOfferings() {

        double amount = 0.0;

        for (int i = 0; i < 3; i ++) {

            amount += months.get(i).getMonthOfferings();
        }

        return amount;
    }

    public double getQuarterTithes() {

        double amount = 0.0;

        for (int i = 0; i < 3; i ++) {

            amount += months.get(i).getMonthTithes();
        }

        return amount;
    }

    public boolean equalsOfferings(Quarter obj) {
        return (getQuarterOfferings() == obj.getQuarterOfferings());
    }

    public boolean greaterOfferings(Quarter obj) {
        return (getQuarterOfferings() > obj.getQuarterOfferings());
    }

    public boolean lesserOfferings(Quarter obj) {
        return (getQuarterOfferings() < obj.getQuarterOfferings());
    }

    public boolean equalsTithes(Quarter obj) {
        return (getQuarterTithes() == obj.getQuarterTithes());
    }

    public boolean greaterTithes(Quarter obj) {
        return (getQuarterTithes() > obj.getQuarterTithes());
    }

    public boolean lesserTithes(Quarter obj) {
        return (getQuarterTithes() < obj.getQuarterTithes());
    }

    public Quarter copy() {

        Quarter copy = new Quarter(quarter, months);

        return copy;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder("Quarter ");
        sb.append(quarter);
        sb.append("\n   Estimated Offerings: $");
        sb.append(Year.df.format(getQuarterOfferings()));
        sb.append("\n   Estimated Tithes:    $");
        sb.append(Year.df.format(getQuarterTithes()));

        return sb.toString();
    }

    public String detailedReport() {

        StringBuilder sb = new StringBuilder("Quarter ");
        sb.append((quarter + 1) + "\n");

        for (int i = 0; i < 3; i++) {

            sb.append("\n" + months.get(i).detailedReport());
        }

        sb.append("\n Quarterly Total Offerings: $");
        sb.append(Year.df.format(getQuarterOfferings()));
        sb.append("\n Quarterly Total Tithes:    $");
        sb.append(Year.df.format(getQuarterTithes()));
        sb.append("\n");

        return sb.toString();
    }
}
