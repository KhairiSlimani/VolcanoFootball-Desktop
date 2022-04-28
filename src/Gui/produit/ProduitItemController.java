/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.produit;

import Entities.Produit;
import Entities.User;
import Gui.SessionManager;
import Gui.user.EditUserController;
import Services.ProduitService;
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
public class ProduitItemController implements Initializable {

    @FXML
    private Text nom;
    @FXML
    private Text type;
    @FXML
    private Text taille;
    @FXML
    private Text couleur;
    @FXML
    private Text prix;
    
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    Produit produit;
    public int id;
    @FXML
    private Pane item;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton editButton;
    @FXML
    private JFXButton favButton;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(SessionManager.get().getRole().equals("Client"))
        {
            editButton.setVisible(false);
            deleteButton.setVisible(false);
        }
        
    }    

    @FXML
    private void DeleteProduit(ActionEvent event) {
        
        ProduitService us = new ProduitService();
        boolean test = us.SupprimerProduit(id);
        if(test)
        {
            flowPane.getChildren().remove(item);
        }

    }

    @FXML
    private void EditProduit(ActionEvent event) throws IOException {
        
        ProduitService ps = new ProduitService();
        Produit produit = ps.RecupererProduit(id);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProduit.fxml"));
        AnchorPane ap = loader.load();
        EditProduitController controller = loader.getController();
        controller.setInfos(produit, flowPane, dialog , container);
        controller.getCloseButton().setOnAction(actionEvent -> {
            dialog.close();
        });

        dialog.getChildren().add(ap);

        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.setDialogContainer(container);

        dialog.show();

    }
    
    public void ItemInfos(Produit p, FlowPane fp, JFXDialog d, StackPane c){
        dialog = d;
        container = c;
        flowPane=fp;
        produit=p;
        id = p.getId();
        nom.setText(p.getNom());
        type.setText(p.getType());
        couleur.setText(p.getCouleur());
        prix.setText(String.valueOf(p.getPrix()));
    }

    @FXML
    private void addFav(ActionEvent event) {
        
    }

    
}
