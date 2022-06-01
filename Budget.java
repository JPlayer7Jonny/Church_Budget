import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
*
* @author Jonathas_Pavani
*/

public class Budget implements Serializable {
	
//	private static int current_year = 2022;
	
	public static void writeBinaryYear(Year obj, String fileName) throws IOException {
		
		// Create the binary output objects.
		FileOutputStream outStream =
	               new FileOutputStream(fileName);
		ObjectOutputStream outputFile =
	               new ObjectOutputStream(outStream);
	    
	      System.out.println("Writing the objects to the file...");
	    	  
	      outputFile.writeObject(obj);
	    
	      System.out.println("Done.\n");
	      
	      // Close the file.
	      outputFile.close(); 
	}
	

	public static Year readBinaryYear(String fileName) throws IOException, ClassNotFoundException {
		
		Year binaryInput;
		
		FileInputStream fstream = new FileInputStream(fileName);
		ObjectInputStream inputFile = new ObjectInputStream(fstream);
		
		System.out.println("Reading objects from the file...");
			
		try {
			
			binaryInput = ((Year) inputFile.readObject());
			System.out.println("Done.\n");
			
			return binaryInput;
	        }
		
		catch (Exception e) {
		
			System.out.println(e.getMessage());
			inputFile.close();
			return new Year();
		}
	}
	
	public static void modify(Year year) throws NullPointerException {
		
		if (year == null)
			throw new NullPointerException("ERROR! Given object is null.");
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("\nPlease, enter the month you want to change (from 1 to 12): ");
		int _month = input.nextInt();
		
		while (_month < 1 || _month > 12) {
			
			System.out.print("\nThe month you have entered is invalid. Please, enter a number from 1 to 12: ");
			_month = input.nextInt();
		}
		
		--_month;
		
		Month month = year.getMonthly().get(_month);
		
		System.out.printf("\nPlease, enter the week you want to change (from 1 to %d): ", month.getServiceDays().size());
		int _week = input.nextInt();
		
		while (_week < 1 || _week > month.getServiceDays().size()) {
			
			System.out.printf("\nThe week you have entered is invalid. Please, enter a number from 1 to  %d: ", month.getServiceDays().size());
			_week = input.nextInt();
		}
		
		--_week;
		
		Sabbath sabbath = month.getServiceDays().get(_week);
		
		System.out.println("\n\n" + sabbath.getDate());
	    
	    sabbath.setOfferings(recordBills("Offerings", input));
	    sabbath.setTithes(recordBills("Tithes", input));
	    
	    ArrayList<Sabbath> mo = month.getServiceDays();
	    mo.set(_week, sabbath);
	    month.setServiceDays(mo);
	    
	    ArrayList<Month> m = year.getMonthly();
	    m.set(_month, month);
	    year.setMonthly(m);
	    
	    input.close();
	}
	
	protected static Money recordBills(String str, Scanner input) {
		
		System.out.println("\n" + str);
	    
	    System.out.print("\nPlease, enter the amount of $100 bills: ");
	    int bills_100 = input.nextInt();
	    
	    System.out.print("\nPlease, enter the amount of $50 bills: ");
	    int bills_50 = input.nextInt();
	    
	    System.out.print("\nPlease, enter the amount of $20 bills: ");
	    int bills_20 = input.nextInt();
	    
	    System.out.print("\nPlease, enter the amount of $10 bills: ");
	    int bills_10 = input.nextInt();
	    
	    System.out.print("\nPlease, enter the amount of $5 bills: ");
	    int bills_5 = input.nextInt();
	    
	    System.out.print("\nPlease, enter the amount of $1 bills: ");
	    int bills_1 = input.nextInt();
	    
	    System.out.print("\nPlease, enter the dollar valor in coins: ");
	    double coins = input.nextDouble();
	    
	    return new Money(bills_100, bills_50, bills_20, bills_10, bills_5, bills_1, coins);
	}
	
	public static void print(Year year) throws NullPointerException {
		
		if (year == null)
			throw new NullPointerException("ERROR! Given object is null.");
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\nPlease, choose one of the options: (Enter a different number to print the whole year)\n 1) Print Sabbath\n 2) Print Month\n 3) Print Quater\n 4) Print Custom Section\n");
		int option = input.nextInt();
		
		System.out.print("\nDo you want to print a detailed report? (Enter 1 to detailed summary or a different number to overview summary) ");
		int detail = input.nextInt();
		
		System.out.println("\n");
		
		switch (option) {
        case 1:
        	
        	System.out.print("\nPlease, enter the month you want to print (from 1 to 12): ");
    		int _month = input.nextInt();
    		
    		while (_month < 1 || _month > 12) {
    			
    			System.out.print("\nThe month you have entered is invalid. Please, enter a number from 1 to 12: ");
    			_month = input.nextInt();
    		}
    		
    		--_month;
    		
    		System.out.printf("\nPlease, enter the week you want to print (from 1 to %d): ", year.getMonthly().get(_month).getServiceDays().size());
    		int _week = input.nextInt();
    		
    		while (_week < 1 || _week > year.getMonthly().get(_month).getServiceDays().size()) {
    			
    			System.out.printf("\nThe week you have entered is invalid. Please, enter a number from 1 to  %d: ", year.getMonthly().get(_month).getServiceDays().size());
    			_week = input.nextInt();
    		}
    		
    		if (detail == 1)
        		System.out.println(year.getMonthly().get(_month).getServiceDays().get(_week).detailedReport());
        	else
        		System.out.println(year.getMonthly().get(_month).getServiceDays().get(_week));
    		
            break;
                 
        case 2:
        	
        	System.out.print("\nPlease, enter the month you want to print (from 1 to 12): ");
    		_month = input.nextInt();
    		
    		while (_month < 1 || _month > 12) {
    			
    			System.out.print("\nThe month you have entered is invalid. Please, enter a number from 1 to 12: ");
    			_month = input.nextInt();
    		}
    		
    		--_month;
    		
    		if (detail == 1)
        		System.out.println(year.getMonthly().get(_month).detailedReport());
        	else
        		System.out.println(year.getMonthly().get(_month));
        	
            break;
                 
        case 3:
        	
        	System.out.print("\nPlease, enter the quarter you want to print (from 1 to 4): ");
    		int _quarter = input.nextInt();
    		
    		while (_quarter < 1 || _quarter > 4) {
    			
    			System.out.print("\nThe month you have entered is invalid. Please, enter a number from 1 to 12: ");
    			_month = input.nextInt();
    		}
    		
    		--_quarter;
    		
    		if (detail == 1)
        		System.out.println(year.getQuarterly().get(_quarter).detailedReport());
        	else
        		System.out.println(year.getQuarterly().get(_quarter));
        	
            break;
                 
        case 4:
        	
        	System.out.print("\nPlease, enter the month you want to start printing (from 1 to 12): ");
        	int _monthS = input.nextInt();
    		
    		while (_monthS < 1 || _monthS > 12) {
    			
    			System.out.print("\nThe month you have entered is invalid. Please, enter a number from 1 to 12: ");
    			_monthS = input.nextInt();
    		}
    		
    		--_monthS;
        	
    		System.out.printf("\nPlease, enter the week you want to start printing (from 1 to %d): ", year.getMonthly().get(_monthS).getServiceDays().size());
    		int _weekS = input.nextInt();
    		
    		while (_weekS < 1 || _weekS > 12) {
    			
    			System.out.printf("\nThe week you have entered is invalid. Please, enter a number from 1 to  %d: ", year.getMonthly().get(_monthS).getServiceDays().size());
    			_weekS = input.nextInt();
    		}
    		
    		--_weekS;
    		
    		System.out.print("\nPlease, enter the month you want to start printing (from 1 to 12): ");
        	int _monthF = input.nextInt();
    		
    		while (_monthF < 1 || _monthF > 12) {
    			
    			System.out.print("\nThe month you have entered is invalid. Please, enter a number from 1 to 12: ");
    			_monthF = input.nextInt();
    		}
    		
    		--_monthF;
        	
    		System.out.printf("\nPlease, enter the week you want to start printing (from 1 to %d): ", year.getMonthly().get(_monthF).getServiceDays().size());
    		int _weekF = input.nextInt();
    		
    		while (_weekF < 1 || _weekF > 12) {
    			
    			System.out.printf("\nThe week you have entered is invalid. Please, enter a number from 1 to  %d: ", year.getMonthly().get(_monthF).getServiceDays().size());
    			_weekF = input.nextInt();
    		}
    		
    		--_weekF;
    		
    		System.out.println("Year " + year.getYear());
    		
    		System.out.println("\nMonth " + _monthS);
    		
    		double tithes = 0;
    		double offerings = 0;
    		
    		for (int i = _weekS; i < year.getMonthly().get(_monthS).getServiceDays().size(); i++) {
    			
    			if (detail == 1)
    				System.out.println(year.getMonthly().get(_monthS).getServiceDays().get(i).detailedReport());
    			
    			tithes += year.getMonthly().get(_monthS).getServiceDays().get(i).getTithes().getAmount();
    			offerings += year.getMonthly().get(_monthS).getServiceDays().get(i).getOfferings().getAmount();
    		}
    		
    		for (int i = _monthS + 1; i < _monthF; i++) {
    			
    			if (detail == 1)
    				System.out.println(year.getMonthly().get(i).detailedReport());
    			
    			tithes += year.getMonthly().get(i).getMonthTithes();
    			offerings += year.getMonthly().get(i).getMonthOfferings();
    		}
    		
    		System.out.println("\nMonth " + _monthS);
    		
    		for (int i = 0; i < _weekF + 1; i++) {
    			
    			if (detail == 1)
    				System.out.println(year.getMonthly().get(_monthF).getServiceDays().get(i).detailedReport());
    			
    			tithes += year.getMonthly().get(_monthF).getServiceDays().get(i).getTithes().getAmount();
    			offerings += year.getMonthly().get(_monthF).getServiceDays().get(i).getOfferings().getAmount();
    		}
    		
    		System.out.printf("\nEstimated Offerings: $%f", offerings);
            System.out.printf("\nEstimated Tithes:    $%f\n\n", tithes);
    		
        	break;
                 
        default: 
        	
        	if (detail == 1)
        		System.out.println(year.detailedReport());
        	else
        		System.out.println(year);
        	
        	break;
    }
		
		input.close();
	}

	public static void main(String[] args) {
		
		Year year = new Year();
		
		try {
			
			year = readBinaryYear("_2022.dat");
		}
		
		catch (Exception e) {
			
			System.err.println(e.getMessage());
		}
		
		Budget.print(year);

				
//		Scanner input = new Scanner(System.in);
//		
//		
//		Money offerings = new Money();
//		Money tithes = new Money();
//		Sabbath week1 = new Sabbath(offerings, tithes, "JAN 01, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		Sabbath week2 = new Sabbath(offerings, tithes, "JAN 08, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		Sabbath week3 = new Sabbath(offerings, tithes, "JAN 15, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		Sabbath week4 = new Sabbath(offerings, tithes, "JAN 22, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		Sabbath week5 = new Sabbath(offerings, tithes, "JAN 29, 2022");
//		
//		ArrayList <Sabbath> Month_List = new ArrayList<Sabbath>(0);
//		Month_List.add(week1);
//		Month_List.add(week2);
//		Month_List.add(week3);
//		Month_List.add(week4);
//		Month_List.add(week5);
//		Month month = new Month(1, Month_List);	
//		ArrayList <Month> _2022 = year.getMonthly();
//		_2022.set(0, month);
//		year.setMonthly(_2022);
//		
//		offerings = new Money();
//		tithes = new Money();
//		week1 = new Sabbath(offerings, tithes, "FEB 05, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week2 = new Sabbath(offerings, tithes, "FEB 12, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week3 = new Sabbath(offerings, tithes, "FEB 19, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week4 = new Sabbath(offerings, tithes, "FEB 26, 2022");
//		
//		Month_List.clear();
//		Month_List.add(week1);
//		Month_List.add(week2);
//		Month_List.add(week3);
//		Month_List.add(week4);
//		month = new Month(2, Month_List);
//		_2022 = year.getMonthly();
//		_2022.set(1, month);
//		year.setMonthly(_2022);
//		
//		offerings = new Money(0, 0, 4, 7, 6, 6, 2.85);
//		tithes = new Money();
//		week1 = new Sabbath(offerings, tithes, "MAR 05, 2022");
//		
//		offerings = new Money(0, 0, 1, 1, 2, 2, 0);
//		tithes = new Money(18, 1, 1, 1, 0, 0, 0);
//		week2 = new Sabbath(offerings, tithes, "MAR 12, 2022");
//		
//		offerings = new Money(0, 0, 5, 1, 3, 5, 0.78);
//		tithes = new Money(9, 1, 0, 1, 1, 0, 0);
//		week3 = new Sabbath(offerings, tithes, "MAR 19, 2022");
//		
//		offerings = new Money(3, 0, 1, 2, 2, 14, 0);
//		tithes = new Money(8, 1, 1, 1, 0, 0, 0);
//		week4 = new Sabbath(offerings, tithes, "MAR 26, 2022");
//		
//		Month_List.clear();
//		Month_List.add(week1);
//		Month_List.add(week2);
//		Month_List.add(week3);
//		Month_List.add(week4);
//		month = new Month(3, Month_List);
//		_2022 = year.getMonthly();
//		_2022.set(2, month);
//		year.setMonthly(_2022);
//		
//		
//		offerings = new Money(1, 1, 1, 0, 1, 9, 0.98);
//		tithes = new Money(11, 1, 1, 1, 1, 2, 0);
//		week1 = new Sabbath(offerings, tithes, "APR 02, 2022");
//		
//		offerings = new Money(1, 0, 2, 4, 3, 9, 1.26);
//		tithes = new Money(9, 1, 2, 0, 1, 2, 0);
//		week2 = new Sabbath(offerings, tithes, "APR 09, 2022");
//		
//		offerings = new Money(0, 1, 1, 0, 1, 2, 0.77);
//		tithes = new Money(13, 1, 1, 1, 0, 1, 0.50);
//		week3 = new Sabbath(offerings, tithes, "APR 16, 2022");
//		
//		offerings = new Money(1, 0, 2, 0, 7, 5, 0.53);
//		tithes = new Money(4, 0, 0, 0, 0, 0, 0);
//		week4 = new Sabbath(offerings, tithes, "APR 23, 2022");
//		
//		offerings = new Money(0, 0, 2, 0, 1, 15, 0);
//		tithes = new Money(4, 0, 0, 0, 0, 0, 0);
//		week5 = new Sabbath(offerings, tithes, "APR 30, 2022");
//		
//		Month_List.clear();
//		Month_List.add(week1);
//		Month_List.add(week2);
//		Month_List.add(week3);
//		Month_List.add(week4);
//		Month_List.add(week5);
//		month = new Month(4, Month_List);
//		_2022 = year.getMonthly();
//		_2022.set(3, month);
//		year.setMonthly(_2022);
//		
//		
//		offerings = new Money(4, 1, 1, 1, 1, 9, 0);
//		tithes = new Money(11, 1, 2, 0, 0, 3, 0);
//		week1 = new Sabbath(offerings, tithes, "MAY 07, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week2 = new Sabbath(offerings, tithes, "MAY 14, 2022");
//		
//		offerings = new Money(0, 0, 0, 1, 3, 6, 0);
//		tithes = new Money(10, 6, 1, 1, 0, 0, 0);
//		week3 = new Sabbath(offerings, tithes, "MAY 21, 2022");
//		
//		offerings = new Money(0, 0, 1, 0, 0, 3, 0.52);
//		tithes = new Money(3, 2, 5, 1, 2, 0, 0);
//		week4 = new Sabbath(offerings, tithes, "MAY 28, 2022");
//		
//		Month_List.clear();
//		Month_List.add(week1);
//		Month_List.add(week2);
//		Month_List.add(week3);
//		Month_List.add(week4);
//		month = new Month(5, Month_List);
//		_2022 = year.getMonthly();
//		_2022.set(4, month);
//		year.setMonthly(_2022);
//		
//		
//		offerings = new Money();
//		tithes = new Money();
//		week1 = new Sabbath(offerings, tithes, "JUN 04, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week2 = new Sabbath(offerings, tithes, "JUN 11, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week3 = new Sabbath(offerings, tithes, "JUN 18, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week4 = new Sabbath(offerings, tithes, "JUN 25, 2022");
//		
//		Month_List.clear();
//		Month_List.add(week1);
//		Month_List.add(week2);
//		Month_List.add(week3);
//		Month_List.add(week4);
//		month = new Month(6, Month_List);
//		_2022 = year.getMonthly();
//		_2022.set(5, month);
//		year.setMonthly(_2022);		
//		
//		
//		offerings = new Money();
//		tithes = new Money();
//		week1 = new Sabbath(offerings, tithes, "JUL 02, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week2 = new Sabbath(offerings, tithes, "JUL 09, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week3 = new Sabbath(offerings, tithes, "JUL 16, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week4 = new Sabbath(offerings, tithes, "JUL 23, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week5 = new Sabbath(offerings, tithes, "JUL 30, 2022");
//		
//		Month_List.clear();
//		Month_List.add(week1);
//		Month_List.add(week2);
//		Month_List.add(week3);
//		Month_List.add(week4);
//		Month_List.add(week5);
//		month = new Month(7, Month_List);
//		_2022 = year.getMonthly();
//		_2022.set(6, month);
//		year.setMonthly(_2022);
//		
//		
//		
//		offerings = new Money();
//		tithes = new Money();
//		week1 = new Sabbath(offerings, tithes, "AUG 06, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week2 = new Sabbath(offerings, tithes, "AUG 13, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week3 = new Sabbath(offerings, tithes, "AUG 20, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week4 = new Sabbath(offerings, tithes, "AUG 27, 2022");
//		
//		Month_List.clear();
//		Month_List.add(week1);
//		Month_List.add(week2);
//		Month_List.add(week3);
//		Month_List.add(week4);
//		month = new Month(8, Month_List);
//		_2022 = year.getMonthly();
//		_2022.set(7, month);
//		year.setMonthly(_2022);
//		
//		
//		
//		offerings = new Money();
//		tithes = new Money();
//		week1 = new Sabbath(offerings, tithes, "SEPT 03, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week2 = new Sabbath(offerings, tithes, "SEPT 10, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week3 = new Sabbath(offerings, tithes, "SEPT 17, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week4 = new Sabbath(offerings, tithes, "SEPT 24, 2022");
//		
//		Month_List.clear();
//		Month_List.add(week1);
//		Month_List.add(week2);
//		Month_List.add(week3);
//		Month_List.add(week4);
//		month = new Month(9, Month_List);
//		_2022 = year.getMonthly();
//		_2022.set(8, month);
//		year.setMonthly(_2022);
//		
//		
//		
//		offerings = new Money();
//		tithes = new Money();
//		week1 = new Sabbath(offerings, tithes, "OCT 01, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week2 = new Sabbath(offerings, tithes, "OCT 08, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week3 = new Sabbath(offerings, tithes, "OCT 15, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week4 = new Sabbath(offerings, tithes, "OCT 22, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week5 = new Sabbath(offerings, tithes, "OCT 29, 2022");
//		
//		Month_List.clear();
//		Month_List.add(week1);
//		Month_List.add(week2);
//		Month_List.add(week3);
//		Month_List.add(week4);
//		Month_List.add(week5);
//		month = new Month(10, Month_List);
//		_2022 = year.getMonthly();
//		_2022.set(9, month);
//		year.setMonthly(_2022);
//		
//		
//		
//		offerings = new Money();
//		tithes = new Money();
//		week1 = new Sabbath(offerings, tithes, "NOV 05, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week2 = new Sabbath(offerings, tithes, "NOV 12, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week3 = new Sabbath(offerings, tithes, "NOV 19, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week4 = new Sabbath(offerings, tithes, "NOV 26, 2022");
//		
//		Month_List.clear();
//		Month_List.add(week1);
//		Month_List.add(week2);
//		Month_List.add(week3);
//		Month_List.add(week4);
//		month = new Month(11, Month_List);
//		_2022 = year.getMonthly();
//		_2022.set(10, month);
//		year.setMonthly(_2022);
//		
//		
//		
//		offerings = new Money();
//		tithes = new Money();
//		week1 = new Sabbath(offerings, tithes, "DEC 03, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week2 = new Sabbath(offerings, tithes, "DEC 10, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week3 = new Sabbath(offerings, tithes, "DEC 17, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week4 = new Sabbath(offerings, tithes, "DEC 24, 2022");
//		
//		offerings = new Money();
//		tithes = new Money();
//		week5 = new Sabbath(offerings, tithes, "DEC 31, 2022");
//		
//		Month_List.clear();
//		Month_List.add(week1);
//		Month_List.add(week2);
//		Month_List.add(week3);
//		Month_List.add(week4);
//		Month_List.add(week5);
//		month = new Month(12, Month_List);
//		_2022 = year.getMonthly();
//		_2022.set(11, month);
//		year.setMonthly(_2022);
//		
//		System.out.println(year.detailedReport());
//		
////		Year copy = year.copy();
////		
////		System.out.println(copy.detailedReport());
		
		try {
			
			writeBinaryYear(year, "_2022.dat");
		}
		
		catch (Exception e) {
			
			System.err.println(e.getMessage());
		}
		
//		try {
////			
////			System.out.println(readBinaryYear("_2022.dat").detailedReport());
//			
//			year = readBinaryYear("_2022.dat");
//		}
//		
//		catch (Exception e) {
//			
//			System.err.println(e.getMessage());
//		}
		
		
		
//		System.out.println(year.detailedReport());
		
	}
}