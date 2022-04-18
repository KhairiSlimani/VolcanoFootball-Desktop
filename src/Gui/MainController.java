/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Agence;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import services.AgenceCrud;
import services.HebergementCrud;

/**
 *
 * @author HP USER
 */
public class MainController implements Initializable {
    
    private Label label;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfnumTel;
    @FXML
    private TableView<Agence> tvAgence;
    @FXML
    private TableColumn<Agence, Integer> colid;
    @FXML
    private TableColumn<Agence, String> colnom;
    @FXML
    private TableColumn<Agence, String> coladresse;
    @FXML
    private TableColumn<Agence, String> colnumTel;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    
        ObservableList list;
    @FXML
    private Button btnofre;

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, IOException {
      //  System.out.println("You clicked me!");
       // label.setText("Hello World!");
        
        if(event.getSource()== btnajouter){
            Ajout( event);
        }
         if(event.getSource()== btnsupprimer){
            supprimer( event);
        }
           if(event.getSource()== btnmodifier){
            Modifier( event);
        }
             if(event.getSource()== btnofre){
            afficherHebergementByAgence( event);
        }
    }
        @Override
    public void initialize(URL url, ResourceBundle rb) {
            Show();

    }    
      public void Show() {
   try {
            AgenceCrud ps = new AgenceCrud();
            List<Agence> agence = ps.afficherAgence();
            list = FXCollections.observableArrayList(agence);
            tvAgence.setItems(list);
            colid.setCellValueFactory(new PropertyValueFactory<>("id"));

            colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            colnumTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    
      
    private void Ajout(ActionEvent event) {
        Agence a = new Agence();
        a.setNom(tfnom.getText());
        a.setAdresse(tfadresse.getText());
        double numTel = 0;
 // controle de saisie numTel
        try {

            numTel = Double.parseDouble(tfnumTel.getText());

        } catch (Exception ex) {
            tfnumTel.setText("");
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Il faut ajouter un entier ");
            alert.show();
            return;

        }
        numTel = Double.parseDouble(tfnumTel.getText());
         // controle de saisie nom et adresse

         if (tfnom.getText().length() == 0) {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Il faut remplir le champ nom");
            alert.show();
            return;
        }
            if (tfadresse.getText().length() == 0) {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Il faut remplir le champ adresse");
            alert.show();
            return;
        }
         
        AgenceCrud ac = new AgenceCrud();
        try {
            ac.ajouterAgence2(a);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Agence ajoutée");
            alert.setContentText("Agence ajoutée aves success");
            alert.show();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          Show();

    }
     private void Modifier(ActionEvent event) {
        Agence a= tvAgence.getSelectionModel().getSelectedItem();
        a.setNom(tfnom.getText());
        a.setAdresse(tfadresse.getText());
        double numTel = 0;

 // controle de saisie numTel
        try {

            numTel = Double.parseDouble(tfnumTel.getText());

        } catch (Exception ex) {
            tfnumTel.setText("");
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Il faut ajouter un entier ");
            alert.show();
            return;

        }
        numTel = Double.parseDouble(tfnumTel.getText());
         // controle de saisie nom et adresse

         if (tfnom.getText().length() == 0) {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Il faut remplir le champ nom");
            alert.show();
            return;
        }
            if (tfadresse.getText().length() == 0) {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Il faut remplir le champ adresse");
            alert.show();
            return;
        }
         
        AgenceCrud ac = new AgenceCrud();
        try {
            ac.modifierAgence(a.getId(), a);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Agence Modifié");
            alert.setContentText("Agence Modifié aves success");
            alert.show();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          Show();

    }
   private void supprimer(ActionEvent event) throws SQLException {
         AgenceCrud us=new AgenceCrud();
        Agence u= tvAgence.getSelectionModel().getSelectedItem();
          boolean check=Suppression_Box("verification", "vous etes sur");
          if(check){
                us.supprimerAgence(u.getId());
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
  
   private void afficherHebergementByAgence(ActionEvent event) throws SQLException, IOException {
       //  HebergementCrud us=new HebergementCrud();
       // Agence u= tvAgence.getSelectionModel().getSelectedItem();
          //us.afficherHebergementByAgence(u.getId());
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Hebergement.fxml"));
            Parent root = loader.load();
            HebergementController controller = loader.getController();
         
         
          
          btnofre.getScene().setRoot(root);
        }
  }

