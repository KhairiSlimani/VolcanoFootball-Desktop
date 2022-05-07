/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.hebergement;

import Entities.Agence;
import Entities.Commande;
import Entities.Hebergement;
import Entities.Reservation;
import Gui.SessionManager;
import Gui.commande.EditCommandeController;
import Gui.reservation.AddReservationController;
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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class HebergementItem2Controller implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Text adresse;
    @FXML
    private Label nomH;
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
        Hebergement hebergement;
        Reservation reservation;

    public int id;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton editButton;
    @FXML
    private AnchorPane item;
    @FXML
    private Text agence;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         if(SessionManager.get().getRole().equals("Client"))
        {
            editButton.setVisible(false);
            deleteButton.setVisible(false);
        }
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
        AgenceCrud ac = new AgenceCrud();
        Agence a =ac.RecupererAgence(cm.getAgence_id());
        agence.setText(a.getNom());

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
  

   

  
}
