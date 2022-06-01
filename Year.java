import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Year implements Serializable {
    
    private int year;
    private ArrayList <Month> monthly = new ArrayList <Month>();
    private ArrayList <Quarter> quarterly = new ArrayList<Quarter>();
    
    private void convertQuarter() {
    	
    	ArrayList <Month> mo = new ArrayList<Month>(0);
        
        for (int i = 0; i < 3; i++) {
        	
        	mo.add(monthly.get(i));
        }
        
        Quarter q = new Quarter(1, mo);
        quarterly.add(q);
        mo.clear();
        
        for (int i = 3; i < 6; i++) {
        	
        	mo.add(monthly.get(i));
        }
        
        q = new Quarter(2, mo);
        quarterly.add(q);
        mo.clear();
        
        for (int i = 6; i < 9; i++) {
        	
        	mo.add(monthly.get(i));
        }
        
        q = new Quarter(3, mo);
        quarterly.add(q);
        mo.clear();
        
        for (int i = 9; i < 12; i++) {
        	
        	mo.add(monthly.get(i));
        }
        
        q = new Quarter(4, mo);
        quarterly.add(q);
        
    }


    public Year() {

        year = 2022;

        for (int i = 0; i < 12; i++) {
        	
        	monthly.add(new Month(i + 1));
        }
        
        convertQuarter();
    }

    public Year(int year, ArrayList <Month> monthly) {

        this.year = year;

        for (int i = 0; i < 12; i++) {
        	
        	this.monthly.add(monthly.get(i));
        }
        
        convertQuarter();
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void updateQuarterly() {

        int counter = 0;

        for (int i = 0; i < 4; i++) {

            ArrayList <Month> q = new ArrayList <Month> (0);

            for (int j = 0; j < 3; j++) {

                q.add(monthly.get(counter));

                ++counter;
            }

            quarterly.set(i, new Quarter(i, q));
        }
    }

    public void setMonthly(ArrayList<Month> monthly) {

        int counter = 0;

        for (int i = 0; i < 4; i++) {

            ArrayList <Month> q = new ArrayList <Month> (0);

            for (int j = 0; j < 3; j++) {

                this.monthly.set(counter, monthly.get(counter));

                q.add(monthly.get(counter));

                ++counter;
            }

            quarterly.set(i, new Quarter(i, q));
        }
    }

    public int getYear() {
        return year;
    }

    public ArrayList<Month> getMonthly() {

        ArrayList<Month> list = new ArrayList<Month>(0);

        for (int i = 0; i < 12; i++) {

            list.add(monthly.get(i));
        }

        return list;
    }

    public ArrayList<Quarter> getQuarterly() {

        ArrayList<Quarter> list = new ArrayList<Quarter>(0);

        for (int i = 0; i < 4; i++) {

            list.add(quarterly.get(i));
        }

        return list;
    }

    public double getYearOfferings() {

        double amount = 0.0;

        for (int i = 0; i < 12; i ++) {

            amount += monthly.get(i).getMonthOfferings();
        }

        return amount;
    }

    public double getYearTithes() {

        double amount = 0.0;

        for (int i = 0; i < 12; i ++) {

            amount += monthly.get(i).getMonthTithes();
        }

        return amount;
    }
    
    public Year copy() { return new Year(year, monthly); }

    public String toString() {

        StringBuilder sb = new StringBuilder("Year ");
        sb.append(year);
        sb.append("\n   Estimated Offerings: $");
        sb.append(Year.df.format(getYearOfferings()));
        sb.append("\n   Estimated Tithes:    $");
        sb.append(Year.df.format(getYearTithes()));

        return sb.toString();
    }

    public String detailedReport() {

        StringBuilder sb = new StringBuilder("Year ");
        sb.append(year + "\n");

        for (int i = 0; i < 4; i++) {

            sb.append("\n" + quarterly.get(i).detailedReport());
        }

        sb.append("\n");

        sb.append("\nEstimated Offerings: $");
        sb.append(Year.df.format(getYearOfferings()));
        sb.append("\nEstimated Tithes:    $");
        sb.append(Year.df.format(getYearTithes()));

        return sb.toString();
    }
    
    public static final DecimalFormat df = new DecimalFormat("0.00");
}
