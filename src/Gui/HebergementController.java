/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Agence;
import entities.Hebergement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import services.AgenceCrud;
import services.HebergementCrud;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class HebergementController implements Initializable {

    @FXML
    private TableView<?> tvheb;
    @FXML
    private Button btnAjouter;
    @FXML
    private TableColumn<?, ?> colid;
    @FXML
    private TableColumn<?, ?> colnomH;
    @FXML
    private TableColumn<?, ?> coladresse;
    @FXML
    private TableColumn<?, ?> coltype;
    @FXML
    private TableColumn<?, ?> colagence;

            ObservableList list;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnmod;
    @FXML
    private TableColumn<?, ?> colagence1;
    @FXML
    private Button btnretour;
  @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
      //  System.out.println("You clicked me!");
       // label.setText("Hello World!");
        
        if(event.getSource()== btnAjouter){
            ajouter( event);
      
    }
          if(event.getSource()== btnsupp){
            supprimer( event);
      
    }
             if(event.getSource()== btnmod){
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
   try {
            HebergementCrud ps = new HebergementCrud();
            List<Hebergement> hebergement = ps.afficherHebergement();
            list = FXCollections.observableArrayList(hebergement);
            tvheb.setItems(list);
            colid.setCellValueFactory(new PropertyValueFactory<>("id"));

            colnomH.setCellValueFactory(new PropertyValueFactory<>("nomH"));
            coltype.setCellValueFactory(new PropertyValueFactory<>("type"));

            coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            colagence.setCellValueFactory(new PropertyValueFactory<>("agence_id"));


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    
    
    private void ajouter(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterHebergementFXML.fxml"));
            Parent root = loader.load();
            AjouterHebergementFXMLController controller = loader.getController();
         
         
          
          btnAjouter.getScene().setRoot(root);
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
      private void supprimer(ActionEvent event) throws SQLException {
         HebergementCrud us=new HebergementCrud();
        Hebergement u= (Hebergement) tvheb.getSelectionModel().getSelectedItem();
          boolean check=Suppression_Box("verification", "vous etes sur");
          if(check){
                us.supprimerHebergement(u.getId());
    }
          Show();
  }
  public boolean Suppression_Box(String title, String message) {
        boolean sortie = false;
        Alert.AlertType Type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(Type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            sortie = true;
        } else if (result.get() == ButtonType.CANCEL) {
            sortie = false;
        }

        return sortie;

    } 
  private void Retour(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root = loader.load();
           HebergementController controller = loader.getController();
         
         
          
          btnretour.getScene().setRoot(root);
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
