/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.user;

import Entities.User;
import Gui.AlertsController;
import Services.UserService;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Khairi
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField tfUsername;
    @FXML
    private JFXTextField tfPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Login(MouseEvent event) throws IOException {
        
        boolean control = true;
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        
        if(username.length()==0 || password.length() ==0){
            AlertsController.get().Alert(".","Erreur","Veuillez entrer toutes les informations nécessaires!");
            control = false;
        }
        
        if(control == true)
        {
            UserService us = new UserService();
            boolean test = us.Login(username,password);
            
            if(test == true)
            {
                Parent root = FXMLLoader.load(getClass().getResource("../dashboard.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            }
        }

        
        
    }

    @FXML
    private void OpenRegister(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
