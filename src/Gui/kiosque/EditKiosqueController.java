/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.kiosque;

import Entities.Commande;
import Entities.Produit;
import Entities.User;
import Entities.kiosque;
import Entities.stade;
import Gui.AlertsController;
import Gui.SessionManager;
import Services.CommandeService;
import Services.KiosqueService;
import Services.ProduitService;
import Services.StadeService;
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
public class EditKiosqueController implements Initializable {

   
    
    @FXML
    private Button buttonClose;
    
    private int KiosqueId;
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    @FXML
    private JFXTextField tfNom;
    @FXML
    private JFXComboBox<?> tfStade;
    @FXML
    private JFXComboBox<String> tfType;
    private String[] Type={"Africain", "Brezilien", "Italien"};


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfType.getItems().addAll(Type);
        
        UserService us = new UserService();
        
        if(SessionManager.get().getRole().equals("Admin"))
        {
            ObservableList users = FXCollections.observableArrayList();
            List<User> list = us.AfficherUsers();
            for (int i = 0; i < list.size(); i++) {
                users.add(list.get(i).getUsername());
            }
           

        }
        else
        {
            User u = us.RecupererUser(SessionManager.get().getUsername());
            ObservableList user = FXCollections.observableArrayList();
            user.add(u.getUsername());
            
            
        }
        
        ObservableList stade = FXCollections.observableArrayList();
        StadeService ps = new StadeService();
        List<stade> listP = ps.AfficherStade();

        for (int i = 0; i < listP.size(); i++) {
           stade.add(listP.get(i).getNom());

        }
        tfStade.setItems(stade);
        tfStade.getSelectionModel().selectFirst();

    }    

 
    
    public Button getCloseButton(){
        return this.buttonClose;
    }
    
    
    public void setInfos(kiosque k, FlowPane fp, JFXDialog d, StackPane c){
        KiosqueId = k.getId();
        dialog = d;
        container = c;
        flowPane=fp;
        tfType.getValue();
        tfStade.getSelectionModel().getSelectedItem().toString();
        tfNom.setText(k.getNom());      

    }

    @FXML
    private void EditK(ActionEvent event) {
         boolean control = true;
       
        String stade = tfStade.getSelectionModel().getSelectedItem().toString();
        String type = tfType.getValue();
        String nom = tfNom.getText();
        
        if(nom.length() ==0){
            AlertsController.get().Alert(".","Erreur","Veuillez entrer toutes les informations nécessaires!");
            control = false;
        }
        else if(nom.length()<4) {
            AlertsController.get().Alert(".","Erreur","L'adresse doit faire au moins 4 caractères!");            
            control = false;
        }
        
        if(control == true)
        {
           
            
            StadeService ps = new StadeService();
            stade p = ps.RecupererStade(stade);

            kiosque k = new kiosque(KiosqueId, p.getId(), nom, type); 
            System.out.println(KiosqueId);
            
            System.out.println(p.getId());
            System.out.println(nom);
            System.out.println(type);
            KiosqueService cs = new KiosqueService();
            boolean test = cs.ModifierKiosque(k);
            
            if(test == true)
            {
                tfNom.clear();
                flowPane.getChildren().clear();
                List<kiosque> list = cs.AfficherKiosque();
                try {
                    for (int i = 0; i < list.size(); i++) {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("KiosqueItem.fxml"));
                        Pane pane = loader.load();
                        KiosqueItemController controller = loader.getController();
                        controller.ItemInfos(list.get(i), flowPane, dialog , container);
                        flowPane.getChildren().add(pane);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }


            }
        }

    }


    
}
