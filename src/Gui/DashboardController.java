/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Khairi
 */
public class DashboardController implements Initializable {
    
    Pane view;
    
    @FXML
    private Text sessionName;
    @FXML
    private BorderPane borderPane;
    @FXML
    private HBox dashboardBox;
    @FXML
    private HBox usersBox;
    @FXML
    private HBox produitsBox;
    @FXML
    private HBox commandesBox;
    @FXML
    private VBox SideBar;
    @FXML
    private HBox AgencesBox1;
    @FXML
    private HBox HebergementsBox;
    @FXML
    private HBox RéservationsBox1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sessionName.setText(SessionManager.get().getUsername());
     if(SessionManager.get().getRole().equals("Client") || SessionManager.get().getRole().equals("client")){
            SideBar.getChildren().remove(usersBox);
        }
    }    

    @FXML
    private void Logout(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("user/login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void OpenUsers(MouseEvent event) throws IOException {
        
        view = new FXMLLoader().load(getClass().getResource("user/users.fxml"));
        borderPane.setCenter(view);
       
    }

    @FXML
    private void OpenProduits(MouseEvent event) throws IOException {
        view = new FXMLLoader().load(getClass().getResource("produit/produits.fxml"));
        borderPane.setCenter(view);

    }

    @FXML
    private void OpenCommandes(MouseEvent event) throws IOException {
        view = new FXMLLoader().load(getClass().getResource("commande/commandes.fxml"));
        borderPane.setCenter(view);

    }
    
 @FXML
    private void OpenAgences(MouseEvent event) throws IOException {
        view = new FXMLLoader().load(getClass().getResource("agence/Agence.fxml"));
        borderPane.setCenter(view);

    }
    @FXML
    private void OpenHebergements(MouseEvent event) throws IOException {
        view = new FXMLLoader().load(getClass().getResource("hebergement/Hebergement.fxml"));
        borderPane.setCenter(view);

    }
     @FXML
    private void OpenReservations(MouseEvent event) throws IOException {
        view = new FXMLLoader().load(getClass().getResource("reservation/Reservation.fxml"));
        borderPane.setCenter(view);

    }
}
