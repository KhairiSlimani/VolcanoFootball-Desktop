/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.hebergement;

import Entities.Agence;
import Entities.Commande;
import Entities.Hebergement;
import Entities.Produit;
import Entities.User;
import Gui.AlertsController;
import Gui.SessionManager;
import Gui.commande.CommandeItemController;
import Services.AgenceCrud;
import Services.CommandeService;
import Services.HebergementCrud;
import Services.ProduitService;
import Services.UserService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.FileChooser;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class AddHebergementController implements Initializable {

    @FXML
    private JFXComboBox<Agence> tfAgence;
    @FXML
    private JFXComboBox<?> tfType;
    @FXML
    private JFXTextField tfNom;
    @FXML
    private JFXTextField tfAdresse;
    @FXML
    private Button buttonClose;
   private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
     private String fileName ,fcs;
    private JFXTextField image_name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            AgenceCrud crud = new AgenceCrud();
        List< Agence>  agences = null;
        try {
            agences = crud.afficherAgence();
        } catch (SQLException ex) {
            Logger.getLogger(AddHebergementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Callback<ListView<Agence>, ListCell<Agence>> factory = lv -> new ListCell<Agence>() {

            @Override
            protected void updateItem(Agence item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNom());
            }

        };
        tfAgence.setCellFactory(factory);
        tfAgence.setButtonCell(factory.call(null));
        tfAgence.setItems(FXCollections.observableArrayList(agences));

     

             ObservableList types = FXCollections.observableArrayList();
        types.add("Hotel");
        types.add("Cruise Ship");
        types.add("Villa");
       
        tfType.setItems(types);
        tfType.getSelectionModel().selectFirst();
        }
  
    @FXML
    private void AddH(ActionEvent event) throws SQLException {
        
        boolean control = true;
       Hebergement h = new Hebergement();
        h.setNomH(tfNom.getText());
        h.setAdresse(tfAdresse.getText());
        h.setAgence_id(tfAgence.getValue().getId());
       h.setType(tfType.getSelectionModel().getSelectedItem().toString());


        if(tfNom.getText().length() ==0){
            AlertsController.get().Alert(".","Erreur","Veuillez entrer toutes les informations nécessaires!");
            control = false;
        }
        
        else if(tfAdresse.getText().length()<4) {
            AlertsController.get().Alert(".","Erreur","L'adresse doit faire au moins 4 caractères!");            
            control = false;
        }
        
        if(control == true)
        {

                 HebergementCrud ac = new HebergementCrud();
                 boolean test = ac.ajouterHebergement2(h);
            
            
            
            if(test == true)
            {
                tfNom.clear();
                tfAdresse.clear();
                flowPane.getChildren().clear();
                List<Hebergement> list = ac.afficherHebergement();
                try {
                    for (int i = 0; i < list.size(); i++) {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("HebergementItem2.fxml"));
                        Pane pane = loader.load();
                        HebergementItem2Controller controller = loader.getController();
                        controller.ItemInfos(list.get(i), flowPane, dialog , container);
                        flowPane.getChildren().add(pane);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    public Button getCloseButton(){
        return this.buttonClose;
    }
    
    public void setInfos(FlowPane fp, JFXDialog d, StackPane c){
        dialog = d;
        container = c;
        flowPane=fp;
    }
    private void Importer(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File SelectedFile = fc.showOpenDialog(null);
         fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", ".jpg", ".png"));
        if (SelectedFile != null) {

            fcs = SelectedFile.toString();
            int index = fcs.lastIndexOf('\\');
            if (index > 0) 
            {
                fileName = fcs.substring(index + 1);
            }
        }
        fcs=SelectedFile.getAbsolutePath();
        File source = new File(fcs);
        File destination = new File(System.getProperty("user.dir") + "\\src\\image\\" + fileName);
        String url = "" + fileName;
         image_name.setText(url); 
    }
    
    
}
