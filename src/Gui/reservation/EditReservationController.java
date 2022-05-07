/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.reservation;

import Entities.Hebergement;
import Entities.Reservation;
import Gui.AlertsController;
import Gui.hebergement.HebergementItem2Controller;
import Services.HebergementCrud;
import Services.ReservationCrud;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class EditReservationController implements Initializable {

    @FXML
    private Button buttonClose;
    @FXML
    private JFXComboBox<Hebergement> heb;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    private int ReservationId;
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void EditR(ActionEvent event) throws SQLException {
        
    boolean control = true;

     

        int hebergement = heb.getValue().getId();
        Date dateD=Date.valueOf(dateDebut.getValue());
        Date dateF=Date.valueOf(dateFin.getValue());


       
        
        if(control == true)
        {
            
            

            System.out.println(ReservationId);
           

                        Reservation h = new Reservation(ReservationId,hebergement, dateD, dateF);

            ReservationCrud ac = new ReservationCrud();
                 boolean test = ac.modifierReservation(h);
            
            if(test == true)
            {
                
                flowPane.getChildren().clear();
                 List<Reservation> list = ac.afficher();
               
            }
        }
    }
    public void setInfos(Reservation cm, FlowPane fp, JFXDialog d, StackPane c){
        ReservationId = cm.getId();
        dialog = d;
        container = c;
        flowPane=fp;
           
    }  

   public Button getCloseButton(){
        return this.buttonClose;
    }


}
