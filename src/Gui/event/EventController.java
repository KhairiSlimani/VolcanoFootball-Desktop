/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.event;

import Entities.Evennement;
import Gui.agence.AddAgenceController;
import Services.EventService;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import Gui.event.EventItemController;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class EventController implements Initializable {

    @FXML
    private JFXButton btnHeb;
    private FlowPane FlowPane;
    private StackPane container;
    @FXML
    private JFXDialog dialog;
    @FXML
    private ScrollPane ScrollPane;
    @FXML
    private FlowPane FlowPaneAg;
    @FXML
    private StackPane containerAg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListStades();
    }    

    @FXML
    private void ShowAddHebergement(ActionEvent event) {
          try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEvent.fxml"));
            AnchorPane ap = loader.load();
            AddEventController controller = loader.getController();
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
    public void ListStades() {
        
      EventService cs = new EventService();
        List<Evennement> list = cs.Afficher();
                                System.out.println("ouiii");

        try {
            for (int i = 0; i < list.size(); i++) {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EventItem.fxml"));
                Pane pane = loader.load();
                EventItemController controller = loader.getController();
                controller.ItemInfos(list.get(i), FlowPane, dialog , container);

                FlowPaneAg.getChildren().add(pane);
  
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
                
                
       
    }
}
