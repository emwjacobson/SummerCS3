/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author emerson_jacobson
 */
public class FXMLDocumentController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
    @FXML
    private AnchorPane apane;
    @FXML
    private MenuBar menuBar;
    @FXML
    public void openMenuButton(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        
        File file = fileChooser.showOpenDialog(apane.getScene().getWindow());
        System.out.println(file.getAbsolutePath());
    }
    @FXML
    public void saveMenuButton(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Resource File");
        
        File file = fileChooser.showOpenDialog(apane.getScene().getWindow());
        System.out.println(file.getAbsolutePath());
    }
}
