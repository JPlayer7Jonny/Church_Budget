import java.io.Serializable;
import java.util.ArrayList;

public class Month implements Serializable {
    
    private int month;
    private ArrayList<Sabbath> serviceDays;

    public Month() {

        month = 0;
        serviceDays = new ArrayList<> (0);
    }

    public Month(int month) {

        this.month = month;
        serviceDays = new ArrayList<> (0);
        
        for (int i = 0; i < 4; i++) {

        	serviceDays.add(new Sabbath());
        }
    }

    public Month(int month, ArrayList<Sabbath> serviceDays) {

        this.month = month;
        this.serviceDays = new ArrayList<Sabbath> (0);

        for (int i = 0; i < serviceDays.size(); i++) {

            this.serviceDays.add(serviceDays.get(i));
        }
    }

    public Month(Month obj) {

        month = obj.month;
        serviceDays = new ArrayList<Sabbath> (0);

        for (int i = 0; i < obj.serviceDays.size(); i++) {

            serviceDays.add(obj.serviceDays.get(i));
        }
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setServiceDays(ArrayList<Sabbath> serviceDays) {

        serviceDays = new ArrayList<> (0);

        for (int i = 0; i < serviceDays.size(); i++) {

            this.serviceDays.add(serviceDays.get(i));
        }
    }

    public void addDay(Sabbath day) {
        serviceDays.add(day);
    }

    public void removeDay(int index) {
        serviceDays.remove(index);
    }

    public int getMonth() {
        return month;
    }

    public ArrayList<Sabbath> getServiceDays() {

        ArrayList<Sabbath> list = new ArrayList<Sabbath> (0);

        for (int i = 0; i < serviceDays.size(); i++) {

            list.add(serviceDays.get(i));
        }

        return list;
    }

    public double getMonthOfferings() {

        double amount = 0.0;

        for (int i = 0; i < serviceDays.size(); i++) {

            amount += serviceDays.get(i).getOfferings().getAmount();
        }

        return amount;
    }

    public double getMonthTithes() {

        double amount = 0.0;

        for (int i = 0; i < serviceDays.size(); i++) {

            amount += serviceDays.get(i).getTithes().getAmount();
        }

        return amount;
    }

    public boolean equalsOfferings(Month obj) {
        return (getMonthOfferings() == obj.getMonthOfferings());
    }

    public boolean greaterOfferings(Month obj) {
        return (getMonthOfferings() > obj.getMonthOfferings());
    }

    public boolean lesserOfferings(Month obj) {
        return (getMonthOfferings() < obj.getMonthOfferings());
    }

    public boolean equalsTithes(Month obj) {
        return (getMonthTithes() == obj.getMonthTithes());
    }

    public boolean greaterTithes(Month obj) {
        return (getMonthTithes() > obj.getMonthTithes());
    }

    public boolean lesserTithes(Month obj) {
        return (getMonthTithes() < obj.getMonthTithes());
    }

    public Month copy() {

        Month copy = new Month(month, serviceDays);

        return copy;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder("Month ");
        sb.append(month);
        sb.append("\n   Estimated Offerings: $");
        sb.append(Year.df.format(getMonthOfferings()));
        sb.append("\n   Estimated Tithes:    $");
        sb.append(Year.df.format(getMonthTithes()));

        return sb.toString();
    }

    public String detailedReport() {

        StringBuilder sb = new StringBuilder("  Month ");
        sb.append(month + "\n");

        for (int i = 0; i < serviceDays.size(); i++) {

            sb.append(serviceDays.get(i).detailedReport());
        }

//        sb.append("\n");
        sb.append("\n   Monthly Total Offerings: $");
        sb.append(Year.df.format(getMonthOfferings()));
        sb.append("\n   Monthly Total Tithes:    $");
        sb.append(Year.df.format(getMonthTithes()));
        sb.append("\n");

        return sb.toString();
    }
}
