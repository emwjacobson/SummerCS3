package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment2 {
	public static Scanner scnr;
	
	public static void main(String[] args) {
		scnr = new Scanner(System.in);
		System.out.print("Enter name of data file: ");
		String filename = scnr.next();
		File file = new File(filename);
		
		try {
			Scanner file_scanner = new Scanner(file);
			
			String last, first;
			String id;
			int num_courses;
			Course[] courses;
			
			last = file_scanner.next();
			last = last.substring(0, last.length()-1); // Removes the comma
			first = file_scanner.next();
			
			file_scanner.nextLine();
			
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
			
			Student s1 = new Student(first + " " + last, id, courses);
			file_scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		
	}
	
}
