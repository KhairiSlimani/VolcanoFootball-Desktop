/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.user;

import Entities.User;
import Gui.AlertsController;
import Gui.Mailer;
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
public class ResetPasswordController implements Initializable {

    @FXML
    private JFXTextField tfEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ResetPassword(ActionEvent event) {
        
        UserService us = new UserService();
        User u = new User();
        u = us.RecupererUserEmail(tfEmail.getText());
        
        if(u.getUsername() != null)
        {
            Mailer m = new Mailer();
            m.ResetPassword(tfEmail.getText(), u.getUsername(), u.getPassword());
        }
        else
        {
            AlertsController.get().Alert(".","Erreur","Il n'y a pas de compte avec ce mail!");
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
    private void OpenActivate(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ActivateAccount.fxml"));
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
