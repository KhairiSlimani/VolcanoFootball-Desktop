/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.kiosque;


import Entities.kiosque;
import Gui.SessionManager;
import Gui.agence.AgenceController;
import Services.CommandeService;
import Services.KiosqueService;
import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Khairi
 */
public class KiosquesController implements Initializable {

    @FXML
    private ScrollPane ScrollPane;
    @FXML
    private FlowPane FlowPane;
    @FXML
    private StackPane container;
    @FXML
    private JFXDialog dialog;
    @FXML
    private JFXButton btnAdd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(SessionManager.get().getRole().equals("Client"))
        {
            btnAdd.setVisible(false);
        }
        ListKiosques();

    }    

   
    
    public void ListKiosques() {
        
        KiosqueService cs = new KiosqueService();
        List<kiosque> list = new ArrayList();
        
        list = cs.AfficherKiosque();
        
        
        try {
            for (int i = 0; i < list.size(); i++) {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("KiosqueItem.fxml"));
                Pane pane = loader.load();
                KiosqueItemController controller = loader.getController();
                controller.ItemInfos(list.get(i), FlowPane, dialog , container);

                FlowPane.getChildren().add(pane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ShowAddKiosque(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddKiosque.fxml"));
            AnchorPane ap = loader.load();
            AddKiosqueController controller = loader.getController();
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

    @FXML
    private void Map(ActionEvent event) {
        Stage stage = new Stage ();
         
        final WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("googleMaps.html").toString());
       
        // create scene
       // stage.getIcons().add(new Image("/Images/logo.png"));
        stage.setTitle("localisation");
        Scene scene = new Scene(webView,1000,700, Color.web("#666970"));
        stage.setScene(scene);
        // show stage
        stage.show();
    }
    }

    

