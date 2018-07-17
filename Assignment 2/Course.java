package assignment2;

public class Course {
    private String name;
    private String grade;
    private int num_units;

    public Course(String name, String grade, int num_units) {
        this.setData(name, grade, num_units);
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
        // TODO: Error checking
        this.name = name == null ? "" : name;
        this.grade = isGradeValid(grade) ? "" : grade;
        this.num_units = num_units < 0 ? 0 : num_units;
    }
    
    private boolean isGradeValid(String g) {
    	if (g.length() != 1) return false;
    	String validGrades = "ABCDFabcdf";
    	return validGrades.contains(g);
    }
}
