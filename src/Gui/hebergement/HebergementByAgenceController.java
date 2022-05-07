/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.hebergement;

import Entities.Agence;
import Entities.Hebergement;
import Services.HebergementCrud;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class HebergementByAgenceController implements Initializable {

    @FXML
    private ScrollPane ScrollPaneAg;
    @FXML
    private FlowPane FlowPaneAg;
    @FXML
    private StackPane containerAg;
    @FXML
    private JFXDialog dialog;
    @FXML
    private JFXTextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ListHebergementByAgence();
    }    
     public void ListHebergementByAgence()  {
        
        HebergementCrud cs = new HebergementCrud();
        Agence a = new Agence();
      List<Hebergement> list = cs.afficherHebergementByAgence(a.getId());
   

        try {
            for (int i = 0; i < list.size(); i++) {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("HebergementItem2.fxml"));
                Pane pane = loader.load();
                HebergementItem2Controller controller = loader.getController();
                controller.ItemInfos(list.get(i), FlowPaneAg, dialog , containerAg);

                FlowPaneAg.getChildren().add(pane);
  
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ShowAddHebergement(ActionEvent event) {
    }

    @FXML
    private void searchedAvance(KeyEvent event) {
    }
    
}
