/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.commande;

import Entities.Commande;
import Entities.Produit;
import Entities.User;
import Gui.AlertsController;
import Gui.SessionManager;
import Services.CommandeService;
import Services.ProduitService;
import Services.UserService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
 * @author Khairi
 */
public class AddCommandeController implements Initializable {

    @FXML
    private JFXComboBox<?> tfUser;
    @FXML
    private JFXComboBox<?> tfProduit;
    @FXML
    private JFXTextField tfQuantite;
    @FXML
    private JFXTextField tfAdresse;
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
        
        
        UserService us = new UserService();
        
        if(SessionManager.get().getRole().equals("Admin"))
        {
            ObservableList users = FXCollections.observableArrayList();
            List<User> list = us.AfficherUsers();
            for (int i = 0; i < list.size(); i++) {
                users.add(list.get(i).getUsername());
            }
            tfUser.setItems(users);
            tfUser.getSelectionModel().selectFirst();
        }
        else
        {
            User u = us.RecupererUser(SessionManager.get().getUsername());
            ObservableList user = FXCollections.observableArrayList();
            user.add(u.getUsername());
            tfUser.setItems(user);
            tfUser.getSelectionModel().selectFirst();  
        }
        
        ObservableList produits = FXCollections.observableArrayList();
        ProduitService ps = new ProduitService();
        List<Produit> listP = ps.AfficherProduits();

        for (int i = 0; i < listP.size(); i++) {
           produits.add(listP.get(i).getNom());

        }
        tfProduit.setItems(produits);
        tfProduit.getSelectionModel().selectFirst();

    }    

    @FXML
    private void AddC(ActionEvent event) {
        
        boolean control = true;
        String user = tfUser.getSelectionModel().getSelectedItem().toString();
        String produit = tfProduit.getSelectionModel().getSelectedItem().toString();
        int quantite = Integer.parseInt(tfQuantite.getText());
        String adresse = tfAdresse.getText();
        
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
            UserService us = new UserService();
            User u = us.RecupererUser(user);
            
            System.out.println(user);
            System.out.println(u.getId());
            
            ProduitService ps = new ProduitService();
            Produit p = ps.RecupererProduit(produit);
            
            System.out.println(produit);
            System.out.println(p.getId());

            Commande c = new Commande(u.getId(), p.getId(), quantite, adresse);
            
            CommandeService cs = new CommandeService();
            boolean test = cs.AjouterCommande(c);
            
            
            
            if(test == true)
            {
                tfQuantite.clear();
                tfAdresse.clear();
                flowPane.getChildren().clear();
                List<Commande> list = cs.AfficherCommandes();
                try {
                    for (int i = 0; i < list.size(); i++) {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("CommandeItem.fxml"));
                        Pane pane = loader.load();
                        CommandeItemController controller = loader.getController();
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
