/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.produit;

import Entities.Produit;
import Gui.user.AddUserController;
import Services.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Khairi
 */
public class ProduitsController implements Initializable {

    @FXML
    private StackPane container;
    @FXML
    private JFXDialog dialog;
    @FXML
    private FlowPane FlowPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListProduits();
    }    
    
    public void ListProduits() {
        
        ProduitService us = new ProduitService();
        List<Produit> list = us.AfficherProduits();
        try {
            for (int i = 0; i < list.size(); i++) {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitItem.fxml"));
                Pane pane = loader.load();
                ProduitItemController controller = loader.getController();
                controller.ItemInfos(list.get(i), FlowPane, dialog , container);

                FlowPane.getChildren().add(pane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ShowAddProduit(ActionEvent event) {
        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduit.fxml"));
            AnchorPane ap = loader.load();
            AddProduitController controller = loader.getController();
            controller.setInfos(FlowPane, dialog , container);
            controller.getCloseButton().setOnAction(ActionEvent -> {
                dialog.close();
            });

            dialog.getChildren().add(ap);
            dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
            dialog.setDialogContainer(container);

            dialog.show();  
            
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    
}
