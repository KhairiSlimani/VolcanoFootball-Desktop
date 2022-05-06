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
import Gui.stade.StadeItemController;
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
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 * FXML Controller class
 *
 * @author Khairi
 */
public class AddKiosqueController implements Initializable {

    
    
    @FXML
    private Button buttonClose;
    
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    @FXML
    private JFXTextField tfNom;
    
    @FXML
    private JFXComboBox<String> tfType;
    private String[] Type={"Africain", "Brezilien", "Italien"};
    @FXML
    private JFXComboBox<stade> tfStade;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       tfType.getItems().addAll(Type);
        StadeService crud = new StadeService();
        List< stade >  agences = null;
        agences = crud.AfficherStade();
        Callback<ListView<stade>, ListCell<stade>> factory = lv -> new ListCell<stade>() {

            @Override
            protected void updateItem(stade item, boolean empty) {
        
        super.updateItem(item, empty);
                setText(empty ? "" : item.getNom());
            }

        };
        tfStade.setCellFactory(factory);
        tfStade.setButtonCell(factory.call(null));
        tfStade.setItems(FXCollections.observableArrayList(agences));
    }  
    

    
    
    public Button getCloseButton(){
        return this.buttonClose;
    }
    
    public void setInfos(FlowPane fp, JFXDialog d, StackPane c){
        dialog = d;
        container = c;
        flowPane=fp;
    }

    @FXML
    private void AddK(ActionEvent event) {
       boolean control = true;
       
       kiosque h = new kiosque();
        h.setNom(tfNom.getText());
        h.setStade_id(tfStade.getValue().getId());
        h.setType(tfType.getSelectionModel().getSelectedItem().toString());
        
        
        
        
        if(tfNom.getText().length() ==0){
            AlertsController.get().Alert(".","Erreur","Veuillez entrer toutes les informations nécessaires!");
            control = false;
        }
        
        else if(tfNom.getText().length()<4) {
            AlertsController.get().Alert(".","Erreur","L'adresse doit faire au moins 4 caractères!");            
            control = false;
        }
        
        if(control == true)
        {
            
            
            StadeService ps = new StadeService();
            
            
        
            
           
            KiosqueService cs = new KiosqueService();
            boolean test = cs.AjouterKiosque(h);
            
            
            
            if(test == true)
            {
                
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

    
}}
