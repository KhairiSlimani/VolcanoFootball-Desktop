/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.stade;


import java.sql.Date;
import Entities.stade;
import Gui.AlertsController;

import Services.StadeService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Jasser
 */
public class EditStadeController implements Initializable {

    
    @FXML
    private JFXTextField tfNom;
   
    
    private int id;
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    @FXML
    private JFXTextField tfAdresse;
    @FXML
    private JFXTextField tfCapacite;
    
    @FXML
    private Button buttonClose;
    
    @FXML
    private JFXDatePicker tfdate;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void EditS(ActionEvent event) {
        boolean control = true;
        String nom = (tfNom.getText());
        String adresse =(tfAdresse.getText());
//        Date dateouverture = Date.valueOf(tfdate.getValue());   
        Float capacite = (Float.parseFloat(tfCapacite.getText()));
        
        
        if(nom.length()==0 || adresse.length() ==0 ){
            AlertsController.get().Alert(".","Erreur","Veuillez entrer toutes les informations nécessaires!");
            control = false;
        }
        else if(nom.length()<4) {
            AlertsController.get().Alert(".","Erreur","Le nom doit faire au moins 4 caractères!");            
            control = false;
        }
        else if(adresse.length()<4) {
            AlertsController.get().Alert(".","Erreur","Le type doit faire au moins 4 caractères!");            
            control = false;
        }
        

        
        if(control == true)
        {
            
            stade s = new stade(id, nom, adresse, capacite);
            StadeService ps = new StadeService();
            boolean test = ps.ModifierStade(s);
            
            if(test == true)
            {
                tfNom.clear();
                tfAdresse.clear();
       
                tfCapacite.clear();
            
                flowPane.getChildren().clear();
                List<stade> list = ps.AfficherStade();
                try {
                    for (int i = 0; i < list.size(); i++) {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("StadeItem.fxml"));
                        Pane pane = loader.load();
                        StadeItemController controller = loader.getController();
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
    
    public void setInfos(stade s, FlowPane fp, JFXDialog d, StackPane c){
        dialog = d;
        container = c;
        flowPane=fp;
        id = s.getId();
        tfNom.setText(s.getNom());
        tfAdresse.setText(s.getAdresse());
        
        tfCapacite.setText(String.valueOf(s.getCapacite()));
       

    }


    
}
