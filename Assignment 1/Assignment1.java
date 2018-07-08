package assignment.pkg1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment1 {
    static Scanner scnr = new Scanner(System.in);
    
    public static void main(String[] args) {
        String[] names = new String[20];
        String[] statuses = new String[20];
        double[] salaries = new double[20];
        double[] taxes = new double[20];
        
        String filename = getFileName();
        File file = new File(filename);
        
        try {
            int i = populateData(file, names, statuses, salaries);
            
            for(int j=0; j<i; j++) {
                taxes[j] = calculateTaxes(statuses[j], salaries[j]);
            }
            
            printChart(names, statuses, salaries, taxes, i);
        } catch(FileNotFoundException e) {
            System.out.printf("File %s was not found.", filename);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error has occured.");
        }
        
    }

    public static int populateData(File file, String[] names, String[] statuses, double[] salaries) throws FileNotFoundException {
        Scanner file_scanner = new Scanner(file);
        int i = 0;
        while(file_scanner.hasNext()) {
            names[i] = file_scanner.nextLine();
            statuses[i] = file_scanner.next();
            salaries[i] = file_scanner.nextDouble();
            if (file_scanner.hasNext())
                file_scanner.nextLine();
            i++;
        }
        file_scanner.close();
        return i;
    }
    
    public static String getFileName() {
        System.out.print("Enter a file name: ");
        String filename = scnr.next();
        return filename;
    }
    
    public static double calculateTaxes(String status, double sal) {
        if (status.equalsIgnoreCase("s")) {
            if (sal > 28790) {
                return 1449.60 + ((sal-28790)*0.11);
            } else if (sal > 20930) {
                return 742.40 + ((sal-20930)*0.08);
            } else if (sal > 1710) {
                return 87 + ((sal-1710)*0.03);
            } else if (sal > 0) {
                return 0;
            }
        } else if (status.equalsIgnoreCase("j")) {
            if (sal > 57580) {
                return 2899.20 + ((sal-57580)*0.11);
            } else if (sal > 47120) {
                return 1905.40 + ((sal-47120)*0.09);
            } else if (sal > 3420) {
                return 330.00 + ((sal-3420)*0.04);
            } else if (sal > 0) {
                return 0;
            }
        }
        return -1;
    }
    
    public static void printChart(String[] names, String[] statuses, double[] salaries, double[] taxes, int num) {
    	boolean calcAvgs = true;
    	double[] netSalaries = new double[salaries.length];
    	
        String format = "%-20s%-10s%-15s%-15s%-10s\n";
        System.out.printf(format, "Names", "Status", "Gross Salary", "Taxes", "Net Salary");
        System.out.println("======================================================================");
        for(int i = 0; i < num; i++) {
            System.out.printf("%-20s", names[i]);
            if (statuses[i].equalsIgnoreCase("s") || statuses[i].equalsIgnoreCase("j")){
                System.out.printf("%-10s", statuses[i].equalsIgnoreCase("j") ? "Joint" : "Single");
            } else {
            	calcAvgs = false;
                System.out.printf("%-10s", "Invalid Status");
                System.out.println();
                continue;
            }
            if (salaries[i] >= 0) {
                System.out.printf("%-15.2f", salaries[i]);
            } else {
            	calcAvgs = false;
                System.out.printf("%-15s", "Negative salary entered.");
                System.out.println();
                continue;
            }
            System.out.printf("%-15.2f", taxes[i]);
            System.out.printf("%-10.2f", salaries[i]-taxes[i]);
            netSalaries[i] = salaries[i] - taxes[i];
            System.out.println();
        }

        System.out.println("======================================================================");
        System.out.printf("%-30s", "Averages");
        if(calcAvgs) {
        	System.out.printf("%-15.2f%-15.2f%-10.2f", calcAvg(salaries), calcAvg(taxes), calcAvg(netSalaries));
        } else {
        	System.out.printf("%-15s%-15s%-10s", "$XXXXXX.XX", "$XXXXXX.XX", "$XXXXXX.XX");
        }
    }
    
    public static double calcAvg(double[] arr) {
    	if (arr.length == 0) return 0;
    	double total = 0;
    	for(double item : arr) {
    		total += item;
    	}
    	return total/arr.length;
    }
}