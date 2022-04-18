/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class AffichageFXMLController implements Initializable {

    @FXML
    private Button btnimg;
    @FXML
    private ImageView imgView;
    private FileChooser fileChooser;
    private File file;
    Stage stage;

    /**
     * Initializes the controller class.
     */
    
     private void handleButtonAction(ActionEvent event) throws SQLException {
      //  System.out.println("You clicked me!");
       // label.setText("Hello World!");
        
     
        }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
