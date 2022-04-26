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
import javafx.event.ActionEvent;
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
public class ActivateAccountController implements Initializable {

    @FXML
    private JFXTextField tfUsername;
    @FXML
    private JFXTextField tfToken;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Activate(ActionEvent event) throws IOException {
        User u = new User();
        UserService us = new UserService();
        u = us.RecupererUser(tfUsername.getText());
        if(u.getUsername() != null)
        {
            if(u.getToken().equals(tfToken.getText()))
            {
                u.setActivated(1);
                us.ModifierUser(u);
                AlertsController.get().Alert("information","Succès","Compte activé!");
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
            else
            {
                AlertsController.get().Alert(".","Erreur","Le code d'activation est erroné!");
            }
        }
        else
        {
          AlertsController.get().Alert(".","Erreur","User n'existe pas!");            
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

    @FXML
    private void OpenLogin(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
