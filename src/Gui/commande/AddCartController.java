/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.commande;

import Entities.Commande;
import Gui.AlertsController;
import Gui.SessionManager;
import Services.CommandeService;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;



/**
 * FXML Controller class
 *
 * @author Khairi
 */
public class AddCartController implements Initializable {

    @FXML
    private JFXTextField tfAdresse;
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    List<Commande> list = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddC(ActionEvent event) {
        
        boolean control = true;
        String adresse = tfAdresse.getText();
        
        if(adresse.length() ==0){
            AlertsController.get().Alert(".","Erreur","Veuillez entrer toutes les informations nécessaires!");
            control = false;
        }
        
        if(control == true)
        {
            Map<Integer, Integer> cart = SessionManager.get().getCart();
            CommandeService cs = new CommandeService();
            Commande c = new Commande();
            for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                //System.out.println(entry.getKey() + ":" + entry.getValue());
                c.setUser(SessionManager.get().getId());
                c.setProduit(entry.getKey());
                c.setQuantite(entry.getValue());
                c.setAdresse(tfAdresse.getText());
                cs.AjouterCommande(c);
            }
            SessionManager.get().DeleteCart();
        }    
    }
    
    public void setInfos(FlowPane fp, JFXDialog d, StackPane cn ){
        dialog = d;
        container = cn;
        flowPane=fp;
    }

    
}
