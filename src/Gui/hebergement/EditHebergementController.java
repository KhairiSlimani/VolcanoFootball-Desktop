/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.hebergement;

import Entities.Agence;
import Entities.Commande;
import Entities.Hebergement;
import Entities.Produit;
import Entities.User;
import Gui.AlertsController;
import Gui.commande.CommandeItemController;
import Services.AgenceCrud;
import Services.CommandeService;
import Services.HebergementCrud;
import Services.ProduitService;
import Services.UserService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class EditHebergementController implements Initializable {

    @FXML
    private JFXComboBox<Agence> tfAgence;
    @FXML
    private JFXComboBox<?> tfType;
    @FXML
    private JFXTextField tfNom;
    @FXML
    private JFXTextField tfAdresse;
    @FXML
    private Button buttonClose;
    
private int HebergementId;
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AgenceCrud crud = new AgenceCrud();
        List< Agence>  agences = null;
        try {
            agences = crud.afficherAgence();
        } catch (SQLException ex) {
            Logger.getLogger(AddHebergementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Callback<ListView<Agence>, ListCell<Agence>> factory = lv -> new ListCell<Agence>() {

            @Override
            protected void updateItem(Agence item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNom());
            }

        };
        tfAgence.setCellFactory(factory);
        tfAgence.setButtonCell(factory.call(null));
        tfAgence.setItems(FXCollections.observableArrayList(agences));
        
        
        ObservableList types = FXCollections.observableArrayList();
        types.add("Hotel");
        types.add("Cruise Ship");
        types.add("Villa");
       
        tfType.setItems(types);
        tfType.getSelectionModel().selectFirst();
        }
  
        @FXML
    private void EditH(ActionEvent event) {
                boolean control = true;

     String nomH = tfNom.getText();
          String adresse = tfAdresse.getText();

        String type = tfType.getSelectionModel().getSelectedItem().toString();
        int agence = tfAgence.getValue().getId();

        if(adresse.length() ==0){
            AlertsController.get().Alert(".","Erreur","Veuillez entrer toutes les informations nécessaires!");
            control = false;
        }
        else if(nomH.length()<4) {
            AlertsController.get().Alert(".","Erreur","L'adresse doit faire au moins 4 caractères!");            
            control = false;
        }
        
        if(control == true)
        {
            
            

            System.out.println(HebergementId);
                     System.out.println(tfAdresse);
           

            System.out.println(tfNom);
                        Hebergement h = new Hebergement(HebergementId, type, nomH,adresse,agence);

            HebergementCrud ac = new HebergementCrud();
                 boolean test = ac.modifierHebergement(h);
            
            if(test == true)
            {
                tfNom.clear();
                tfAdresse.clear();
                flowPane.getChildren().clear();
                 List<Hebergement> list = ac.afficherHebergement();
                try {
                    for (int i = 0; i < list.size(); i++) {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("HebergementItem2.fxml"));
                        Pane pane = loader.load();
                        HebergementItem2Controller controller = loader.getController();
                        controller.ItemInfos(list.get(i), flowPane, dialog , container);
                        flowPane.getChildren().add(pane);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
   public void setInfos(Hebergement cm, FlowPane fp, JFXDialog d, StackPane c){
        HebergementId = cm.getId();
        dialog = d;
        container = c;
        flowPane=fp;
        tfAdresse.setText(cm.getAdresse());      
        tfNom.setText(cm.getNomH());      

    }  

   public Button getCloseButton(){
        return this.buttonClose;
    }

 
}
