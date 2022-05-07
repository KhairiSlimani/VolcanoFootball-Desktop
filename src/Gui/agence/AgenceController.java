/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.agence;

import Entities.Agence;
import Entities.Commande;
import Gui.SessionManager;
import Gui.commande.AddCommandeController;
import Gui.commande.CommandeItemController;
import static Gui.print.printNode;
import Services.AgenceCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class AgenceController implements Initializable {

    @FXML
    private ScrollPane ScrollPaneAg;
    @FXML
    private FlowPane FlowPaneAg;
    @FXML
    private StackPane containerAg;
    @FXML
    private JFXDialog dialog;
    @FXML
    private AnchorPane AnO;
    @FXML
    private JFXButton print;
    @FXML
    private JFXButton btnAdd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ListAgences();
        } catch (SQLException ex) {
            Logger.getLogger(AgenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(SessionManager.get().getRole().equals("Client"))
        {
            btnAdd.setVisible(false);
        }
    }    
    public void ListAgences() throws SQLException {
        
        AgenceCrud cs = new AgenceCrud();
        List<Agence> list = cs.afficherAgence();
        try {
            for (int i = 0; i < list.size(); i++) {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AgenceItem.fxml"));
                Pane pane = loader.load();
                AgenceItemController controller = loader.getController();
                controller.ItemInfos(list.get(i), FlowPaneAg, dialog , containerAg);

                FlowPaneAg.getChildren().add(pane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 @FXML
    private void ShowAddAgence(ActionEvent event) {
        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAgence.fxml"));
            AnchorPane ap = loader.load();
            AddAgenceController controller = loader.getController();
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
    private void Print(ActionEvent event) {
            try {
            printNode(AnO);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(AgenceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AgenceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AgenceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(AgenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}
