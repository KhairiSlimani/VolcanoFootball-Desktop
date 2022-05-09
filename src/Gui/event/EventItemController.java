/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.event;

import Entities.Agence;
import Entities.Evennement;
import Entities.Hebergement;
import Services.AgenceCrud;
import Services.EventService;
import Services.HebergementCrud;
import Gui.event.EditEventController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class EventItemController implements Initializable {

    @FXML
    private Pane item;
    @FXML
    private Text nom;
    @FXML
    private Text stade;
    @FXML
    private Text date;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton editButton;
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    
    Evennement evennement;
    public int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void ItemInfos(Evennement cm, FlowPane fp, JFXDialog d, StackPane c){
        EventService ps = new EventService();
        dialog = d;
        container= c;
        flowPane=fp;
        evennement = cm;
        id = cm.getId();
        nom.setText(cm.getNom());
        stade.setText(cm.getStade());
        date.setText(String.valueOf(cm.getDate()));


       }

    @FXML
    private void Delete(ActionEvent event) {
        EventService ps = new EventService();
        boolean test = ps.Supprimer(id);
        if(test)
        {
            flowPane.getChildren().remove(item);
            
        }
    }

    @FXML
    private void Edit(ActionEvent event) throws IOException {
        EventService ps = new EventService();
        Evennement hebergement = ps.RecupererHebergement(id);
         System.out.println(hebergement);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditEvent.fxml"));
        AnchorPane ap = loader.load();
        EditEventController controller = loader.getController();
        controller.setInfos( hebergement, flowPane, dialog , container);
        controller.getCloseButton().setOnAction(actionEvent -> {
            dialog.close();
        });

        dialog.getChildren().add(ap);

        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.setDialogContainer(container);

        dialog.show();

    }
}
