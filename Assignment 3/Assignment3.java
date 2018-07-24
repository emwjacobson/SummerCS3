/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author emerson_jacobson
 */
public class Assignment3 {

    static Scanner scnr = new Scanner(System.in);
    static ArrayList<Student> student_list = new ArrayList<>();

    /*
    TODO:
    Add more verbose errors for grades
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            student_list.addAll(readStudentsFromFile());
            boolean cont;
            do {
                cont = displayMenu();
            } while (cont);
        } catch (FileNotFoundException ex) {
            System.out.println("Error, file was not found.");
        } catch (IOException e) {
            System.out.println("Error, IO Exception has occured");
        } catch (Exception e) {
            System.out.println("An unexpected error has occured.");
            e.printStackTrace();
        }

    }

    public static boolean displayMenu() throws IOException {
        System.out.println("\n====================================");
        System.out.println("1) Add student");
        System.out.println("2) Remove student");
        System.out.println("3) Sort list alphabetically by last name");
        System.out.println("4) Print and save list");
        System.out.println("5) Exit");

        System.out.print("What is your choice (1-5): ");

        int user_choice = scnr.nextInt();
        scnr.nextLine();
        switch (user_choice) {
            case 1:
                addStudent();
                break;
            case 2:
                removeStudent();
                break;
            case 3:
                sortStudents();
                break;
            case 4:
                printSaveStudents();
                break;
            case 5:
            default:
                return false;
        }
        return true;
    }

    public static void sortStudents() {
        student_list.sort((Student t1, Student t2) -> {
            String[] t1_name = t1.getLastName().split(" ");
            String[] t2_name = t2.getLastName().split(" ");
            return t1_name[t1_name.length - 1].compareToIgnoreCase(t2_name[t2_name.length - 1]);
        });
    }

    public static void removeStudent() {
        String remove;
        System.out.print("Enter id of student to remove: ");
        remove = scnr.nextLine();

        for (int i = 0; i < student_list.size(); i++) {
            if (student_list.get(i).getId().equalsIgnoreCase(remove)) {
                System.out.println("Removed student: " + student_list.get(i).getFirstName());
                student_list.remove(i);
                return;
            }
        }
        System.out.println("Could not find student to remove.");
    }

    public static void addStudent() {
        String fname, lname, id;
        System.out.print("Enter student name: ");
        fname = scnr.next();
        lname = scnr.nextLine();
        fname = fname.trim();
        lname = lname.trim();

        System.out.print("Enter student ID: ");
        id = scnr.next();
        scnr.nextLine();

        for (Student s : student_list) {
            if (s.getFirstName().equalsIgnoreCase(fname) && s.getLastName().equalsIgnoreCase(lname) && s.getId().equalsIgnoreCase(id)) {
                System.out.println("That student already exists. Not adding.");
                return;
            }
        }

        int num_classes;
        System.out.print("Enter number of classes: ");
        num_classes = scnr.nextInt();
        scnr.nextLine();

        Course[] student_classes = new Course[num_classes];

        for (int i = 0; i < num_classes; i++) {
            String class_name, class_grade;
            int class_units;
            System.out.print("Enter name of class: ");
            class_name = scnr.nextLine();

            System.out.print("Enter class grade: ");
            class_grade = scnr.next();

            System.out.print("Enter class units: ");
            class_units = scnr.nextInt();

            student_classes[i] = new Course(class_name, class_grade, class_units);
        }

        student_list.add(new Student(fname, lname, id, student_classes));
    }

    public static void printSaveStudents() throws IOException {
        System.out.print("Print or save? (P/S): ");
        String in = scnr.next();
        
        
        int num_students = 0, cunits = 0, tunits = 0;
        
        switch(in) {
            case "P":
            case "p":
                System.out.printf("%-20s%-15s%-15s%-20s%-15s%n", "Name", "ID", "Units Taken", "Units Completed", "GPA");
                System.out.println("=====================================================================================");
                for (Student s : student_list) {
                    System.out.println(s);

                    num_students++;
                    cunits += s.getUnitsCompleted();
                    tunits += s.getUnitsTaken();
                }

                System.out.println("Total Number of Students: " + num_students);
                System.out.println("Total Units Completed: " + cunits);
                System.out.println("Total Units Taken: " + tunits);
                break;
            case "S":
            case "s":
                System.out.print("Enter output file name: ");
                String outname = scnr.next();
                File outfile = new File(outname);
                PrintWriter pw = new PrintWriter(outfile);
                
                pw.printf("%-20s%-15s%-15s%-20s%-15s%n", "Name", "ID", "Units Taken", "Units Completed", "GPA");
                pw.println("=====================================================================================");
                
                for (Student s : student_list) {
                    pw.println(s);

                    num_students++;
                    cunits += s.getUnitsCompleted();
                    tunits += s.getUnitsTaken();
                }
                
                pw.println("Total Number of Students: " + num_students);
                pw.println("Total Units Completed: " + cunits);
                pw.println("Total Units Taken: " + tunits);
                break;
            default:
                System.out.println("Invalid option " + in);
        }
    }

    public static ArrayList<Student> readStudentsFromFile() throws FileNotFoundException {
        System.out.print("Enter filename: ");
        String filename = scnr.nextLine();

        ArrayList<Student> slist = new ArrayList<>();

        File file = new File(filename);
        Scanner file_scanner = new Scanner(file);

        while (file_scanner.hasNext()) {
            String full_name, fname, lname, id;
            int num_classes;
            full_name = file_scanner.nextLine();
            lname = (full_name.split(",")[0]).trim();
            fname = (full_name.split(",")[1]).trim();
//            name = formatName(name);
            id = file_scanner.next();
            num_classes = file_scanner.nextInt();
            file_scanner.nextLine();
            Course[] student_classes = new Course[num_classes];
            for (int i = 0; i < num_classes; i++) {
                String class_name, grade;
                int num_units;
                class_name = file_scanner.nextLine();
                grade = file_scanner.next();
                num_units = file_scanner.nextInt();
                file_scanner.nextLine();
                student_classes[i] = new Course(class_name, grade, num_units);
            }
            slist.add(new Student(fname, lname, id, student_classes));
        }
        file_scanner.close();
        return slist;
    }

//    private static String formatName(String name) {
//        // First name                                               Last name
//        name = (name.substring(name.indexOf(",") + 1, name.length()) + " " + name.substring(0, name.indexOf(","))).trim();
//        return name;
//    }

}
