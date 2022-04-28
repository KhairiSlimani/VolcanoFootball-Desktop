/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.agence;

import Entities.Agence;
import Entities.Produit;
import Gui.AlertsController;
import Gui.produit.ProduitItemController;
import Services.AgenceCrud;
import Services.ProduitService;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class AddAgenceController implements Initializable {

    @FXML
    private JFXTextField tfNom;
    @FXML
    private JFXTextField tfAdresse;
    @FXML
    private JFXTextField tfNumTel;
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
    private void AddA(ActionEvent event) throws SQLException {
           boolean control = true;
        String nom = tfNom.getText();
        String adresse = tfAdresse.getText();
        Integer numTel = Integer.parseInt(tfNumTel.getText());
     
        if(nom.length()==0 || adresse.length() ==0  ){
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
            Agence p = new Agence(numTel , nom , adresse);
            AgenceCrud ps = new AgenceCrud();
            boolean test = ps.ajouterAgence2(p);
            
            if(test == true)
            {
                tfNom.clear();
                tfAdresse.clear();
                tfNumTel.clear();


                flowPane.getChildren().clear();
                List<Agence> list = ps.afficherAgence();
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
    
    public void setInfos(FlowPane fp, JFXDialog d, StackPane c){
        dialog = d;
        container = c;
        flowPane=fp;
    }

    
    }
    

