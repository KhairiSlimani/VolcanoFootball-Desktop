/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.event;

import Entities.Agence;
import Entities.Evennement;
import Gui.agence.AgenceItemController;
import Services.AgenceCrud;
import Services.EventService;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class AddEventController implements Initializable {

    @FXML
    private JFXTextField tfNom;
    @FXML
    private JFXTextField tfstade;
    @FXML
    private DatePicker date;
    @FXML
    private Button buttonClose;
private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddA(ActionEvent event) {
           boolean control = true;
        String nom = tfNom.getText();
        String adresse = tfstade.getText();
             Date datee= Date.valueOf(date.getValue());   

       
      
         // controle de saisie nom et adresse

         if (nom.length() == 0) {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Il faut remplir le champ nom");
            alert.show();
            return;
        }
            if (adresse.length() == 0) {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Il faut remplir le champ stade");
            alert.show();
            return;
        }

        
        if(control == true)
        {
            Evennement p = new Evennement( nom , adresse, datee);
            EventService ps = new EventService();
            boolean test = ps.ajouter(p);
            
            if(test == true)
            {
                tfNom.clear();
                tfstade.clear();


                flowPane.getChildren().clear();
                List<Evennement> list = ps.Afficher();
                try {
                    for (int i = 0; i < list.size(); i++) {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventItem.fxml"));
                        Pane pane = loader.load();
                        EventItemController controller = loader.getController();
                        controller.ItemInfos(list.get(i), flowPane, dialog , container);
                        flowPane.getChildren().add(pane);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }


            }
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
    

