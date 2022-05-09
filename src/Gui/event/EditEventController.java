/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.event;

import Entities.Agence;
import Entities.Evennement;
import Gui.AlertsController;
import Gui.event.EventItemController;
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
public class EditEventController implements Initializable {

    @FXML
    private JFXTextField tfNom;
    @FXML
    private JFXTextField tfStade;
    @FXML
    private DatePicker tfdate;
    @FXML
    private Button buttonClose;
    private int Eventid;
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
    private void EditA(ActionEvent event) {
         
       boolean control = true;
         String nom = tfNom.getText();
         String stade = tfStade.getText();
        Date date = Date.valueOf(tfdate.getValue());   

        if(nom.length() ==0){
            AlertsController.get().Alert(".","Erreur","Veuillez entrer toutes les informations nécessaires!");
            control = false;
        }
        else if(stade.length()<4) {
            AlertsController.get().Alert(".","Erreur","Le champ Stade doit faire au moins 4 caractères!");            
            control = false;
        }
        
        if(control == true)
        {

            Evennement a = new Evennement(Eventid, nom, stade, date ); 
            EventService cs = new EventService();
            boolean test = cs.modifier(a);
            
            if(test == true)
            {
                tfNom.clear();
                tfStade.clear();
                flowPane.getChildren().clear();
                List<Evennement> list = cs.Afficher();
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
    
    
    public void setInfos(Evennement cm, FlowPane fp, JFXDialog d, StackPane c){
       
        dialog = d;
        container = c;
        flowPane=fp;
        Eventid = cm.getId();
        tfStade.setText(cm.getStade());      
        tfNom.setText(cm.getNom());   
        tfdate.setAccessibleText((String.valueOf(cm.getDate())));


    }
}
