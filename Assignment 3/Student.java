package assignment3;

public class Student {
    private String fname;
    private String lname;
    private String id;
    private Course[] classes;
    private double average;
    private String grade;
    private int units_taken;
    private int units_completed;

    public Student() {
        this.setData(null, null, null, null);
    }

    public Student(String fname, String lname, String id, Course[] classes) {
        this.setData(fname, lname, id, classes);
    }
    
    public Student(Student other) {
        this.setData(other.fname, other.lname, other.id, other.classes);
    }

    public void setData(String fname, String lname, String id, Course[] classes) {
        this.fname = fname == null ? "" : fname;
        this.lname = lname == null ? "" : lname;
        this.id = id == null ? "" : id;
        this.classes = classes == null ? new Course[0] : classes.clone();
        calcAvg();
        calcGrade();
        calcUnits();
    }

    /**
     * Calculated the students average score.
     */
    private void calcAvg() {
        if (this.classes == null || this.classes.length == 0) {
                this.average = 0;
                return;
        }
        double total = 0;
        for (Course classe : this.classes) {
            switch (classe.getGrade()) {
                case "A":
                case "a":
                    total++;
                case "B":
                case "b":
                    total++;
                case "C":
                case "c":
                    total++;
                case "D":
                case "d":
                    total++;
            }
        }
        this.average = total/this.classes.length;
    }

    private void calcGrade() {
        this.calcAvg();
        if (this.average > 3) // 3-4+
            this.grade = "A";
        else if (this.average > 2) // 2-3
            this.grade = "B";
        else if (this.average > 1) // 1-2
            this.grade = "C";
        else if (this.average > 0) // 0-1
            this.grade = "D";
    }

    /**
     * Calculated the number of units taken and completed.
     */
    private void calcUnits() {
        if (this.classes == null || this.classes.length == 0) {
            this.units_taken = 0;
            this.units_completed = 0;
            return;
        }
        int taken = 0;
        int completed = 0;
        for(int i = 0;i<this.classes.length;i++) {
            taken += classes[i].getNumUnits();
            if (classes[i].getGrade().equalsIgnoreCase("A") || classes[i].getGrade().equalsIgnoreCase("B") || classes[i].getGrade().equalsIgnoreCase("C") || classes[i].getGrade().equalsIgnoreCase("D")) {
                completed += classes[i].getNumUnits();
            }
        }
        this.units_taken = taken;
        this.units_completed = completed;
    }

    public String getFirstName() { return this.fname; }
    
    public String getLastName() { return this.lname; }

    public String getId() { return this.id; }

    public double getAverage() { return this.average; }

    public String getGrade() { return this.grade; }

    public int getUnitsTaken() { return this.units_taken; }

    public int getUnitsCompleted() { return this.units_completed; }

    @Override
    public String toString() {
        return String.format("%-20s%-15s%-15d%-20d%-15.2f", this.fname + " " + this.lname, this.id, this.units_taken, this.units_completed, this.average);
    }
    
    public boolean equals(Student other) {
        if (other == this) return true;
        return other.fname.equals(fname) && other.lname.equals(lname) && other.id.equals(id) && other.average == average && other.grade.equals(grade) && other.units_taken == units_taken && other.units_completed == units_completed;
    }

}
