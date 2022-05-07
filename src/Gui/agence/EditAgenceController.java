/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.agence;

import Entities.Agence;
import Entities.Commande;
import Entities.Produit;
import Entities.User;
import Gui.AlertsController;
import Gui.commande.CommandeItemController;
import Services.AgenceCrud;
import Services.CommandeService;
import Services.ProduitService;
import Services.UserService;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
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
 * @author HP USER
 */
public class EditAgenceController implements Initializable {

    @FXML
    private JFXTextField tfNom;
    @FXML
    private JFXTextField tfAdresse;
    @FXML
    private JFXTextField tfNum;
    @FXML
    private Button buttonClose;
    private int AgenceId;
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
    private void EditA(ActionEvent event) throws SQLException {
        
       boolean control = true;
        int numTel = Integer.parseInt(tfNum.getText());
        String adresse = tfAdresse.getText();
         String nom = tfNom.getText();

        if(adresse.length() ==0){
            AlertsController.get().Alert(".","Erreur","Veuillez entrer toutes les informations nécessaires!");
            control = false;
        }
        else if(adresse.length()<4) {
            AlertsController.get().Alert(".","Erreur","L'adresse doit faire au moins 4 caractères!");            
            control = false;
        }
        
        if(control == true)
        {

            Agence a = new Agence(AgenceId, numTel, adresse, nom ); 
            AgenceCrud cs = new AgenceCrud();
            boolean test = cs.modifierAgence(a);
            
            if(test == true)
            {
                tfNum.clear();
                tfNom.clear();
                tfAdresse.clear();
                flowPane.getChildren().clear();
                List<Agence> list = cs.afficherAgence();
                try {
                    for (int i = 0; i < list.size(); i++) {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AgenceItem.fxml"));
                        Pane pane = loader.load();
                        AgenceItemController controller = loader.getController();
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
    
    
    public void setInfos(Agence cm, FlowPane fp, JFXDialog d, StackPane c){
       
        dialog = d;
        container = c;
        flowPane=fp;
        AgenceId = cm.getId();
        tfAdresse.setText(cm.getAdresse());      
        tfNom.setText(cm.getNom());      

    }


    
}

