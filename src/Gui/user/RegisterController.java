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
import java.util.regex.Pattern;
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
public class RegisterController implements Initializable {

    @FXML
    private JFXTextField tfUsername;
    @FXML
    private JFXTextField tfPassword;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXTextField tfNom;
    @FXML
    private JFXTextField tfPrenom;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OpenLogin(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void Register(ActionEvent event) throws IOException {
        
        boolean control = true;
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        String email = tfEmail.getText();
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        
        if(username.length()==0 || password.length() ==0 || email.length() == 0 || nom.length() == 0 || prenom.length() == 0){
            AlertsController.get().Alert(".","Erreur","Veuillez entrer toutes les informations nécessaires!");
            control = false;
        }
        else if(username.length()<4) {
            AlertsController.get().Alert(".","Erreur","Le username doit faire au moins 4 caractères!");            
            control = false;
        }
        else if(password.length()<4) {
            AlertsController.get().Alert(".","Erreur","Le mot de passe doit faire au moins 4 caractères!");            
            control = false;
        }
        else if (!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", email))){
            AlertsController.get().Alert(".","Erreur","L'email n'est pas valide!");            
            control = false;
        }
        
        if(control == true)
        {
            User u = new User(username, password, nom, prenom, email, "client", "token", 1);
            UserService us = new UserService();
            boolean test = us.Register(u);
            
            if(test == true)
            {
                tfUsername.clear();
                tfPassword.clear();
                tfEmail.clear();
                tfNom.clear();
                tfPrenom.clear();

                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            }
        }
        
        
    }
    
    
}
