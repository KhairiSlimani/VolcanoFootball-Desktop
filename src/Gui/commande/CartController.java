/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.commande;

import Entities.Commande;
import Gui.SessionManager;
import Services.CommandeService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
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
 * @author Khairi
 */
public class CartController implements Initializable {

    @FXML
    private FlowPane FlowPane;
    @FXML
    private ScrollPane ScrollPane;
    @FXML
    private JFXButton commanderButton;
    @FXML
    private StackPane container;
    @FXML
    private JFXDialog dialog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListCommandes();
    }    

    public void ListCommandes() {
        Map<Integer, Integer> cart = SessionManager.get().getCart();
        try {
            
            for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                //System.out.println(entry.getKey() + ":" + entry.getValue());
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CommandeItem.fxml"));
                Pane pane = loader.load();
                CommandeItemController controller = loader.getController();
                controller.ItemInfos(entry.getKey(),entry.getValue(),FlowPane);
                
                FlowPane.getChildren().add(pane);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void Commander(ActionEvent event) throws IOException {
                
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCart.fxml"));
        AnchorPane ap = loader.load();
        AddCartController controller = loader.getController();
        controller.setInfos(FlowPane, dialog , container);
        dialog.getChildren().add(ap);
        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.setDialogContainer(container);
        dialog.show();  
    }
    
}
