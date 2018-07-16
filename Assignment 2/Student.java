package assignment2;

public class Student {
    private String name;
    private String id;
    private Course[] classes;
    private double average;
    private String grade;
    private int units_taken;
    private int units_completed;

    public Student() {
        this.setData(null, null, null);
    }

    public Student(String name, String id, Course[] classes) {
        this.setData(name, id, classes);
    }

    public void setData(String name, String id, Course[] classes) {
        this.name = name;
        this.id = id;
        this.classes = classes;
        calcAvg();
        calcGrade();
        calcUnits();
    }

    private void calcAvg() {
        if (this.classes == null || this.classes.length == 0) {
                this.average = 0;
                return;
        }
        double total = 0;
        for(int i = 0; i < this.classes.length; i++) {
            switch(classes[i].getGrade()) {
            case "A":
            case "a":
                total += 4;
                break;
            case "B":
            case "b":
                total += 3;
                break;
            case "C":
            case "c":
                total += 2;
                break;
            case "D":
            case "d":
                total += 1;
                break;
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
            if (classes[i].getGrade().equalsIgnoreCase("A") || classes[i].getGrade().equalsIgnoreCase("B") || classes[i].getGrade().equalsIgnoreCase("C")) {
                completed += classes[i].getNumUnits();
            }
        }
        this.units_taken = taken;
        this.units_completed = completed;
    }

    public String getName() { return this.name; }

    public String getId() { return this.id; }

    public double getAverage() { return this.average; }

    public String getGrade() { return this.grade; }

    public int getUnitsTaken() { return this.units_taken; }

    public int getUnitsCompleted() { return this.units_completed; }

    public String toString() {
        return String.format("%-20s%-15s%-15d%-20d%-15.2f", this.name, this.id, this.units_taken, this.units_completed, this.average);
    }

}
