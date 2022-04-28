/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.hebergement;

import Entities.Agence;
import Entities.Commande;
import Entities.Hebergement;
import Gui.ItemController;
import Gui.commande.EditCommandeController;
import Services.AgenceCrud;
import Services.CommandeService;
import Services.HebergementCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class HebergementItem2Controller implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label adresse;
    @FXML
    private Label nomH;
 private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
        Hebergement hebergement;
    public int id;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton editButton;
    @FXML
    private JFXButton favButton;
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
        HebergementCrud ps = new HebergementCrud();
        dialog = d;
        container= c;
        flowPane=fp;
        hebergement = cm;
        id = cm.getId();
        nomH.setText(cm.getNomH());
        adresse.setText(cm.getAdresse());
//        img.setImage(new Image(cm.getPhoto_h().toString()));
      
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
    private void EditH(ActionEvent event) throws IOException {
    
        
        HebergementCrud ps = new HebergementCrud();
        Hebergement hebergement = ps.RecupererHebergement(id);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditHebergement.fxml"));
        AnchorPane ap = loader.load();
        EditHebergementController controller = loader.getController();
        controller.setInfos(hebergement, flowPane, dialog , container);
        controller.getCloseButton().setOnAction(actionEvent -> {
            dialog.close();
        });

        dialog.getChildren().add(ap);

        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.setDialogContainer(container);

        dialog.show();

    }
  

    @FXML
    private void addFav(ActionEvent event) {
    }

  
}
