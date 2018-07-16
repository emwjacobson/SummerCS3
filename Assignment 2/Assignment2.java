package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Assignment2 {
    /*
     * TODO:
     * Add error checking on reading from file? What should happen if invalid?
     * Add error checking inside of student and course?
     */

    public static Scanner scnr;

    public static void main(String[] args) {
        scnr = new Scanner(System.in);

        try {
            Student s1 = studentFromFile();
            Student s2 = studentFromInput();
            printStudents(s1, s2);
            writeStudents(s1, s2);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        catch (Exception e) {
            System.out.println("An unexpected error occurred: ");
            e.printStackTrace();
        }

    }

    private static Student studentFromFile() throws FileNotFoundException {
        System.out.print("Enter name of data file: ");
        String filename = scnr.nextLine();
        File file = new File(filename);


        String first, last;
        String id;
        Course[] courses;
        try (Scanner file_scanner = new Scanner(file)) {
            String name_unformatted;
            int num_courses;
            name_unformatted = file_scanner.nextLine();
            first = name_unformatted.substring(name_unformatted.indexOf(", ") + 2, name_unformatted.length());
            last = name_unformatted.substring(0, name_unformatted.indexOf(","));
            id = file_scanner.next();
            num_courses = file_scanner.nextInt();
            file_scanner.nextLine();
            courses = new Course[num_courses];
            String course_name;
            String course_grade;
            int course_num_units;
            for(int i=0;i<num_courses;i++) {
                course_name = file_scanner.nextLine();
                course_grade = file_scanner.next();
                course_num_units = file_scanner.nextInt();
                if (file_scanner.hasNext())
                    file_scanner.nextLine();
                courses[i] = new Course(course_name, course_grade, course_num_units);
            }
        }
        return new Student(first + " " + last, id, courses);
    }

    private static Student studentFromInput() {
        Student tempS = new Student();
        System.out.print("Enter student name: ");
        String name = scnr.nextLine();
        System.out.print("Enter student ID(###-##-####): ");
        String id = scnr.next();

        int num_classes;
        do {
            System.out.print("Number of classes: ");
            num_classes = scnr.nextInt();
            if (num_classes < 0)
                System.out.println("Please enter a positive amount of classes.");
        } while(num_classes < 0);

        Course[] classes = new Course[num_classes];
        for(int i=0; i<num_classes;i++) {
            System.out.print("Name of course " + (i+1) + ": ");
            String cname = scnr.next();

            String cgrade;
            do {
                System.out.print("Grade in course " + (i+1) + ": ");
                cgrade = scnr.next();
                if (!isValidGrade(cgrade))
                    System.out.println("Please enter a valid grade.");
            } while(!isValidGrade(cgrade));

            int cunits;
            do {
                System.out.print("Course " + (i+1) + " units: ");
                cunits = scnr.nextInt();
                if (cunits < 0)
                    System.out.println("Please enter positive course units.");
            } while(cunits < 0);

            classes[i] = new Course(cname, cgrade, cunits);
        }

        tempS.setData(name, id, classes);
        return tempS;
    }

    public static boolean isValidGrade(String grade) {
        String grades = "ABCDFabcdf";
        return grades.contains(grade);
    }

    private static void printStudents(Student ...students) throws FileNotFoundException {
        System.out.printf("%-20s%-15s%-15s%-20s%-15s\n", "Name", "ID", "Units Taken", "Units Completed", "Average");
        System.out.println("================================================================================");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void writeStudents(Student ...students) throws FileNotFoundException {
        System.out.print("Output file name: ");
        String out_file_name = scnr.next();
        try (PrintWriter pw = new PrintWriter(new File(out_file_name))) {
            pw.printf("%-20s%-15s%-15s%-20s%-15s%n", "Name", "ID", "Units Taken", "Units Completed", "Average");
            pw.println("================================================================================");
            for (Student student : students) {
                pw.println(student);
            }
        }
    }
}
