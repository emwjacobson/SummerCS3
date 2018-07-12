package assignment2;

public class Student {
	private String name;
	private String id;
	private Course[] classes;
	private double average;
//	private String grade;
	private int units_taken;
	private int units_completed;
	
	public Student(String name, String id, Course[] classes) {
		this.name = name;
		this.id = id;
		this.classes = classes;
//		calcAvg();
//		calcGrade();
//		units_taken = ???;
//		units_completed = ???;
	}
	
	public void setData(String name, String id, Course[] classes) {
		this.name = name;
		this.id = id;
		this.classes = classes;
//		calcAvg();
//		calcGrade();
//		units_taken = ???;
//		units_completed = ???;
	}
}
