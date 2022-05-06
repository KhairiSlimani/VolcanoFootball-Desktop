/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.produit;

import Entities.Produit;
import Entities.User;
import Gui.AlertsController;
import Gui.user.UserItemController;
import Services.ProduitService;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
public class AddProduitController implements Initializable {

    @FXML
    private JFXTextField tfNom;
    @FXML
    private JFXTextField tfType;
    @FXML
    private Button buttonClose;
    @FXML
    private JFXComboBox<?> tfTaille;
    @FXML
    private JFXTextField tfCouleur;
    @FXML
    private JFXTextField tfPrix;
    @FXML
    private JFXTextField tfPhoto;
    
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList tailles = FXCollections.observableArrayList();
        tailles.add("XS");
        tailles.add("S");
        tailles.add("M");
        tailles.add("L");
        tailles.add("XL");
        tfTaille.setItems(tailles);
        tfTaille.getSelectionModel().selectFirst();

    }    

    @FXML
    private void AddP(ActionEvent event) {
        
        boolean control = true;
        String nom = tfNom.getText();
        String type = tfType.getText();
        String taille = tfTaille.getSelectionModel().getSelectedItem().toString();
        String couleur = tfCouleur.getText();
        float prix = Float.parseFloat(tfPrix.getText());
        String photo = tfPhoto.getText();
        
        if(nom.length()==0 || type.length() ==0 || couleur.length() == 0 || photo.length() == 0){
            AlertsController.get().Alert(".","Erreur","Veuillez entrer toutes les informations nécessaires!");
            control = false;
        }
        else if(nom.length()<4) {
            AlertsController.get().Alert(".","Erreur","Le nom doit faire au moins 4 caractères!");            
            control = false;
        }
        else if(type.length()<4) {
            AlertsController.get().Alert(".","Erreur","Le type doit faire au moins 4 caractères!");            
            control = false;
        }
        else if(couleur.length()<4) {
            AlertsController.get().Alert(".","Erreur","Le couleur doit faire au moins 4 caractères!");            
            control = false;
        }

        
        if(control == true)
        {
            Produit p = new Produit(nom, type, taille, couleur, prix, photo);
            ProduitService ps = new ProduitService();
            boolean test = ps.AjouterProduit(p);
            
            if(test == true)
            {
                tfNom.clear();
                tfType.clear();
                tfCouleur.clear();
                tfPrix.clear();
                tfPhoto.clear();
                flowPane.getChildren().clear();
                List<Produit> list = ps.AfficherProduits();
                try {
                    for (int i = 0; i < list.size(); i++) {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitItem.fxml"));
                        Pane pane = loader.load();
                        ProduitItemController controller = loader.getController();
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
