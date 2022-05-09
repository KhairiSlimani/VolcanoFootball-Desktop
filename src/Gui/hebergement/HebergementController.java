/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.hebergement;

import Entities.Agence;
import Entities.Hebergement;
import Gui.SessionManager;
import Gui.agence.AgenceController;
import Gui.agence.AgenceItemController;
import Gui.commande.AddCommandeController;
import Services.AgenceCrud;
import Services.HebergementCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.input.KeyEvent;


/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class HebergementController implements Initializable {

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
    ObservableList list;
    @FXML
    private JFXButton btnHeb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ListHebergement();
        if(SessionManager.get().getRole().equals("Client"))
        {
            btnHeb.setVisible(false);
        }
     
    }    

     public void ListHebergement()  {
        
        HebergementCrud cs = new HebergementCrud();
        List<Hebergement> list = cs.afficherHebergement();
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
        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddHebergement.fxml"));
            AnchorPane ap = loader.load();
            AddHebergementController controller = loader.getController();
            controller.setInfos(FlowPaneAg, dialog , containerAg);
            controller.getCloseButton().setOnAction(ActionEvent -> {
                dialog.close();
            });

            dialog.getChildren().add(ap);
            dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
            dialog.setDialogContainer(containerAg);

            dialog.show();  
            
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

 

    @FXML
    private void searchedAvance(KeyEvent event) {
              System.out.println(recherche.getText());
         
        FlowPaneAg.getChildren().clear();

        HebergementCrud gs = new HebergementCrud();
        java.util.List<Hebergement> list = new ArrayList<>();

        list = gs.recherche(recherche.getText());
         System.out.println("test"+list);

      
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
   

}
