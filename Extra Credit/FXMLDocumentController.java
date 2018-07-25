/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author emerson_jacobson
 */
public class FXMLDocumentController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        student_list = new ArrayList<>();
    }
    
    // Dont directly add to this array, use methods ment for them.
    private ArrayList<Student> student_list;
    
    FileChooser fileChooser = new FileChooser();
    
    @FXML
    private AnchorPane apane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Label label_num_loaded;
    @FXML
    private TextArea textarea_student_list;
    @FXML
    public void openMenuButton(ActionEvent e) {
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(apane.getScene().getWindow());
        if (file == null)
            return;
        
        try {
            this.readStudentsFromFile(file);
        } catch (FileNotFoundException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("File Not Found");
            alert.setHeaderText("Error, that file was not found.");

            alert.show();
        } catch (ArrayIndexOutOfBoundsException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Bad File");
            alert.setHeaderText("Error, the file is improperly formatted.");

            alert.show();
        }
        
    }
    @FXML
    public void saveMenuButton(ActionEvent e) {
        File file = fileChooser.showSaveDialog(apane.getScene().getWindow());
        System.out.println(file.getAbsolutePath());
        try {
            this.saveStudentsToFile(file);
        } catch (IOException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An IOException has occured.");

            alert.show();
        }
    }
    
    public void addStudent(Student s) {
        this.student_list.add(s);
        this.label_num_loaded.setText(String.valueOf(this.student_list.size()));
    }
    
    public void updateTextArea() {
        String final_text = "";
        int num_students = 0, cunits = 0, tunits = 0;
        
        final_text += String.format("%-20s%-15s%-15s%-20s%-15s%n", "Name", "ID", "Units Taken", "Units Completed", "GPA");
        final_text += "=====================================================================================\n";
        
        for(Student s : this.student_list) {
            final_text += s.toString() + "\n";
            num_students++;
            cunits += s.getUnitsCompleted();
            tunits += s.getUnitsTaken();
        }
        
        final_text += "=====================================================================================\n";

        final_text += "Total Number of Students: " + num_students + "\n";
        final_text += "Total Units Completed: " + cunits + "\n";
        final_text += "Total Units Taken: " + tunits + "\n";
        
        this.textarea_student_list.setText(final_text);
        System.out.println("dsa");
    }
    
    public void readStudentsFromFile(File file) throws FileNotFoundException, ArrayIndexOutOfBoundsException {
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
            this.addStudent(new Student(fname, lname, id, student_classes));
        }
        this.updateTextArea();
        file_scanner.close();
    }
    
    public void saveStudentsToFile(File outfile) throws IOException {
        int num_students = 0, cunits = 0, tunits = 0;
        
        PrintWriter pw = new PrintWriter(outfile);

        pw.printf("%-20s%-15s%-15s%-20s%-15s%n", "Name", "ID", "Units Taken", "Units Completed", "GPA");
        pw.println("=====================================================================================");

        for (Student s : this.student_list) {
            pw.println(s);

            num_students++;
            cunits += s.getUnitsCompleted();
            tunits += s.getUnitsTaken();
        }
        pw.println("=====================================================================================");

        pw.println("Total Number of Students: " + num_students);
        pw.println("Total Units Completed: " + cunits);
        pw.println("Total Units Taken: " + tunits);
        
        pw.close();
    }
}
