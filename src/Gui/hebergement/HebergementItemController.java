/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.hebergement;

import Entities.Agence;
import Entities.Hebergement;
import Gui.ItemController;
import Services.AgenceCrud;
import Services.HebergementCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class HebergementItemController implements Initializable {


 private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    
    Hebergement hebergement;
    public int id;
    @FXML
    private Text nomH;
    @FXML
    private Text agence;
    @FXML
    private Text adresse;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton editButton;
    @FXML
    private Text type;
    @FXML
    private AnchorPane item;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


      public void ItemInfos(Hebergement cm, FlowPane fp, JFXDialog d, StackPane c){
        AgenceCrud ps = new AgenceCrud();
        dialog = d;
        container= c;
        flowPane=fp;
        hebergement = cm;
        id = cm.getId();
        nomH.setText(cm.getNomH());
        adresse.setText(cm.getAdresse());
        type.setText(cm.getType());
      System.out.print(cm.getAgence_id());
        
        try {
            List<Agence> cats = ps.afficherAgence();
            for(int i=0;i<cats.size();i++){
                if(cats.get(i).getId()==cm.getAgence_id()){
                    agence.setText(cats.get(i).getNom());
                }
                
            }
         
        } catch (SQLException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void DeleteH(ActionEvent event) {
        HebergementCrud ps = new HebergementCrud();
        boolean test = ps.supprimerHebergement(id);
        if(test)
        {
            flowPane.getChildren().remove(item);
        }
    }

    @FXML
    private void EditCommande(ActionEvent event) {
    }

   
}
