package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment2 {
	/*
	 * TODO:
	 * Check input grade to make sure its in range, if not then crash? reprompt?
	 *   "     "   units to make sure not negative, "   "   "     "       "
	 * Make sure num_classes is positive
	 */
	
	public static Scanner scnr;
	
	public static void main(String[] args) {
		scnr = new Scanner(System.in);
		
		try {
			Student s1 = studentFromFile();
			Student s2 = studentFromInput();
			printStudents(s1, s2);
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
		String filename = scnr.next();
		File file = new File(filename);
		
		Scanner file_scanner = new Scanner(file);
		
		String name_unformatted, first, last;
		String id;
		int num_courses;
		Course[] courses;
		
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
			file_scanner.nextLine();
			courses[i] = new Course(course_name, course_grade, course_num_units);
		}
		file_scanner.close();
		return new Student(first + " " + last, id, courses);
	}
	
	private static Student studentFromInput() {
		Student tempS = new Student();
		System.out.print("Enter student name: ");
		String name = scnr.next();
		System.out.print("Enter student ID(###-##-####): ");
		String id = scnr.next();
		System.out.print("Number of classes: ");
		int num_classes = scnr.nextInt();
		Course[] classes = new Course[num_classes];
		for(int i=0; i<num_classes;i++) {
			System.out.print("Name of course " + (i+1) + ": ");
			String cname = scnr.next();
			System.out.print("Grade in course " + (i+1) + "(A-F): ");
			String cgrade = scnr.next();
			System.out.print("Course " + (i+1) + " units: ");
			int cunits = scnr.nextInt();
			classes[i] = new Course(cname, cgrade, cunits);
		}
		
		tempS.setData(name, id, classes);
		return tempS;
	}

	private static void printStudents(Student ...students) {
		System.out.printf("%-20s%-15s%-15s%-20s%-15s\n", "Name", "ID", "Units Taken", "Units Completed", "Average");
		System.out.println("================================================================================");
		for(int i=0;i<students.length;i++) {
			System.out.println(students[i]);
		}
	}


}
