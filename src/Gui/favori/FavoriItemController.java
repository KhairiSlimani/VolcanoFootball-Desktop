/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.favori;

import Entities.Favori;
import Entities.Produit;
import Entities.User;
import Services.FavoriService;
import Services.ProduitService;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Khairi
 */
public class FavoriItemController implements Initializable {

    @FXML
    private Text nom;
    @FXML
    private Text nomProduit;
    @FXML
    private Text prixProduit;
    @FXML
    private JFXButton deleteButton;
    
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    Favori favori;
    public int id;
    @FXML
    private Text username;
    @FXML
    private Pane item;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void DeleteFavori(ActionEvent event) {
        
        FavoriService fs = new FavoriService();
        boolean test = fs.SupprimerFavori(id);
        if(test)
        {
            flowPane.getChildren().remove(item);
        }
    }
    
    public void ItemInfos(Favori f, FlowPane fp, JFXDialog d, StackPane c){
        dialog = d;
        container = c;
        flowPane=fp;
        favori=f;
        id = f.getId();
        
        UserService us = new UserService();
        User u = us.RecupererUser(f.getIdUser());
            
        ProduitService ps = new ProduitService();
        Produit p = ps.RecupererProduit(f.getIdProduit());

        username.setText(u.getUsername());
        nomProduit.setText(p.getNom());
        prixProduit.setText(String.valueOf(p.getPrix()) );
    }

    
}
