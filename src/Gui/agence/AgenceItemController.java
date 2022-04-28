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
import Gui.commande.EditCommandeController;
import Services.AgenceCrud;
import Services.CommandeService;
import Services.ProduitService;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class AgenceItemController implements Initializable {

    @FXML
    private Text nom;
    @FXML
    private Text numTel;
    @FXML
    private Text adresse;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton editButton;
 private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    
    Agence agence;
    public int id;
    @FXML
    private AnchorPane item;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML

    private void DeleteAgence(ActionEvent event) {
           AgenceCrud ps = new AgenceCrud();
        boolean test = ps.supprimerAgence(id);
        if(test)
        {
            flowPane.getChildren().remove(item);
        }
    }

    public void ItemInfos(Agence cm, FlowPane fp, JFXDialog d, StackPane c){
        
        dialog = d;
        container= c;
        flowPane=fp;
        agence = cm;
        id = cm.getId();
        nom.setText(cm.getNom());
        adresse.setText(cm.getAdresse());
        numTel.setText(String.valueOf(cm.getNumTel()));

        
    }

   

    @FXML
    private void EditAgence(ActionEvent event) throws IOException {
         AgenceCrud cs = new AgenceCrud();
         Agence  agence = cs.RecupererAgence(id);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditAgence.fxml"));
        AnchorPane ap = loader.load();
        EditAgenceController controller = loader.getController();
        controller.setInfos(agence, flowPane, dialog , container);
        controller.getCloseButton().setOnAction(actionEvent -> {
            dialog.close();
        });

        dialog.getChildren().add(ap);

        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.setDialogContainer(container);

        dialog.show();
    }

}
