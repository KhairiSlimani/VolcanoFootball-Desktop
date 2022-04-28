/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.commande;

import Entities.Commande;
import Entities.Produit;
import Entities.User;
import Gui.SessionManager;
import Gui.produit.EditProduitController;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Khairi
 */
public class CommandeItemController implements Initializable {

    @FXML
    private Text nom;
    @FXML
    private Text user;
    @FXML
    private Text produit;
    @FXML
    private Text quantite;
    @FXML
    private Text adresse;
    
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    
    Commande commande;
    public int id;
    @FXML
    private Pane item;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton editButton;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*
        if(SessionManager.get().getRole().equals("Client"))
        {
            editButton.setVisible(false);
            deleteButton.setVisible(false);
        }
        */

    }    

    @FXML
    private void DeleteCommande(ActionEvent event) {
        
        CommandeService us = new CommandeService();
        boolean test = us.SupprimerCommande(id);
        if(test)
        {
            flowPane.getChildren().remove(item);
        }

    }

    @FXML
    private void EditCommande(ActionEvent event) throws IOException {
        
        CommandeService cs = new CommandeService();
        Commande commande = cs.RecupererCommande(id);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditCommande.fxml"));
        AnchorPane ap = loader.load();
        EditCommandeController controller = loader.getController();
        controller.setInfos(commande, flowPane, dialog , container);
        controller.getCloseButton().setOnAction(actionEvent -> {
            dialog.close();
        });

        dialog.getChildren().add(ap);

        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.setDialogContainer(container);

        dialog.show();

    }
    
    public void ItemInfos(Commande cm, FlowPane fp, JFXDialog d, StackPane c){
        
        dialog = d;
        container = c;
        flowPane=fp;
        commande = cm;
        id = cm.getId();
        quantite.setText(String.valueOf(cm.getQuantite()));
        adresse.setText(cm.getAdresse());
        
        UserService us = new UserService();
        User u = us.RecupererUser(cm.getUser());
            
        ProduitService ps = new ProduitService();
        Produit p = ps.RecupererProduit(cm.getProduit());

        user.setText(u.getUsername());
        produit.setText(p.getNom());
    }

    
}
