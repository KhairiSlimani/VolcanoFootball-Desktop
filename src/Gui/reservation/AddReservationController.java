/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.reservation;

import Entities.Agence;
import Entities.Hebergement;
import Entities.Reservation;
import Entities.User;
import Gui.AlertsController;
import Gui.SendEmail;
import Gui.SessionManager;
import Gui.hebergement.AddHebergementController;
import Services.AgenceCrud;
import Services.HebergementCrud;
import Services.ReservationCrud;
import Services.UserService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class AddReservationController implements Initializable {

    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private Button buttonClose;
    @FXML
    private JFXComboBox<Hebergement> heb;
   private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    @FXML
    private JFXComboBox<?> tfUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      UserService us = new UserService();
        
        if(SessionManager.get().getRole().equals("Admin"))
        {
            ObservableList users = FXCollections.observableArrayList();
            List<User> list = us.AfficherUsers();
            for (int i = 0; i < list.size(); i++) {
                users.add(list.get(i).getUsername());
            }
            tfUser.setItems(users);
            tfUser.getSelectionModel().selectFirst();

        }
        else
        {
            User u = us.RecupererUser(SessionManager.get().getUsername());
            ObservableList user = FXCollections.observableArrayList();
            user.add(u.getUsername());
            tfUser.setItems(user);
            tfUser.getSelectionModel().selectFirst();
            
        }
        
        HebergementCrud crud = new HebergementCrud();
        List< Hebergement>  agences = null;
        agences = crud.afficherHebergement();
        Callback<ListView<Hebergement>, ListCell<Hebergement>> factory = lv -> new ListCell<Hebergement>() {

            @Override
            protected void updateItem(Hebergement item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNomH());
            }

        };
        heb.setCellFactory(factory);
        heb.setButtonCell(factory.call(null));
        heb.setItems(FXCollections.observableArrayList(agences));    }    

    @FXML
    private void AddR(ActionEvent event) throws SQLException {
        
            boolean control = true;
       Hebergement h = new Hebergement();
        String user = tfUser.getSelectionModel().getSelectedItem().toString();
        int hebergement =heb.getValue().getId(); 
        Date dateD = Date.valueOf(dateDebut.getValue());
        Date dateF =Date.valueOf(dateFin.getValue());
       
        UserService us = new UserService();
            User u = us.RecupererUser(user);
            
            System.out.println(user);
            System.out.println(u.getId());
                     Reservation p = new Reservation(u.getId(),hebergement,dateD,dateF);

                 ReservationCrud ac = new ReservationCrud();
                 boolean test = ac.ajouterReservation(p);
            
            
            
            if(test == true)
            {
           String title = "Félicitation ! Réservation enregistrée";
        String message = "Veuiller Consulter votre boite Mail"
                + "";
         try {
             SendEmail.sendMail();
                } catch (Exception ex) {
                }
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait(); 

                flowPane.getChildren().clear();
                List<Reservation> list = ac.afficher();
               
              
            }
        }
    
    
    public Button getCloseButton(){
        return this.buttonClose;
    }
    
    public void setInfos(FlowPane fp, JFXDialog d, StackPane c){
        dialog = d;
        container = c;
        flowPane=fp;
    }
  
    }

    

