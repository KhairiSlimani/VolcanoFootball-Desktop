/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Agence;
import entities.Hebergement;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.AgenceCrud;
import services.HebergementCrud;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class AjouterHebergementFXMLController implements Initializable {

   
    @FXML
    private TextField tfnomH;
    @FXML
    private TextField tfadresse;
    @FXML
    private ChoiceBox<String> cltype;
    private String[] Type={"Hotels", "Cruise Ship", "Villas"};
    @FXML
     private ComboBox<Agence> clagence;

  
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRetour;
    @FXML
    private Text tftitre;

    
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, IOException {
      //  System.out.println("You clicked me!");
       // label.setText("Hello World!");
        
    
         if(event.getSource()== btnAjouter){
            Ajouter( event);
      }  
         
         if(event.getSource()== btnRetour){
            Retour( event);
      }  
    }
    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        Show();
        
    }    
    public void Show() {
    cltype.getItems().addAll(Type);
    tfnomH.getText();
      tfadresse.getText();
       
        // TODO
        AgenceCrud crud = new AgenceCrud();
        List< Agence>  agences = null;
        try {
            agences = crud.afficherAgence();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterHebergementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Callback<ListView<Agence>, ListCell<Agence>> factory = lv -> new ListCell<Agence>() {

            @Override
            protected void updateItem(Agence item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNom());
            }

        };
        clagence.setCellFactory(factory);
        clagence.setButtonCell(factory.call(null));
        clagence.setItems(FXCollections.observableArrayList(agences));

     
    }    
    private void Ajouter(ActionEvent event) throws IOException {
        Hebergement h = new Hebergement();
        h.setNomH(tfnomH.getText());
        h.setAdresse(tfadresse.getText());
        h.setAgence_id(clagence.getValue().getId());
        h.setType(cltype.getValue());
        String photo = "";


        HebergementCrud ac = new HebergementCrud();
        try {
            ac.ajouterHebergement2(h);
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Hebergement ajoutée");
            alert.setContentText("Hebergement ajoutée aves success");
            alert.show();
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    /* FXMLLoader loader = new FXMLLoader(getClass().getResource("HebergementFXML.fxml"));
            Parent root = loader.load();
          HebergementController controller = loader.getController();
*/
    }
  
      private void Retour(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Hebergement.fxml"));
            Parent root = loader.load();
           HebergementController controller = loader.getController();
         
         
          
          btnRetour.getScene().setRoot(root);
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
