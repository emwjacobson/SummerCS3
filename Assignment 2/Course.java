package assignment2;

public class Course {
    private String name;
    private String grade;
    private int num_units;
    
    public Course() {
        this.setData(null, null, 0);
    }

    public Course(String name, String grade, int num_units) {
        this.setData(name, grade, num_units);
    }
    
    public Course(Course other) {
        this.setData(other.name, other.grade, other.num_units);
    }

    public String getName() {
        return this.name;
    }

    public String getGrade() {
        return this.grade;
    }

    public int getNumUnits() {
        return this.num_units;
    }

    public void setData(String name, String grade, int num_units) {
        this.name = name == null ? "" : name;
        this.grade = isGradeValid(grade) ? "" : grade;
        this.num_units = num_units < 0 ? 0 : num_units;
    }
    
    private boolean isGradeValid(String g) {
    	if (g.length() != 1) return false;
    	String validGrades = "ABCDFabcdf";
    	return validGrades.contains(g);
    }
    
    @Override
    public String toString() {
    	return String.format("Name: %s  Grade: %s  Units: %s", this.name, this.grade, this.num_units);
    }
    
    public boolean equals(Course other) {
        if (other == this) return true;
        return other.name.equals(name) && other.grade.equals(grade) && other.num_units == num_units;
    }
    
}
