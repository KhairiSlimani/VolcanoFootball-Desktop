/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volcanofootball;

import Entities.Joueur;
import Services.JoueurService;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jrady
 */
public class JoueurController implements Initializable {

    @FXML
    private Button btn_ajouterjoueur;
    @FXML
    private Button retourDashjoueur;
    @FXML
    private TableView<Joueur> tablejoueur;
    @FXML
    private TableColumn<Joueur, String> colnom;
    @FXML
    private TableColumn<Joueur, String> colprenom;
    @FXML
    private TableColumn<Joueur, Integer> colage;
    @FXML
    private TextField tfposition;
    @FXML
    private TextField tfdesc;
    @FXML
    private TextField tfequipe;
    @FXML
    private TextField tfphoto;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfage;
    @FXML
    private TableColumn<Joueur, String> colposition;
    @FXML
    private TableColumn<Joueur, String> coldesc;
    @FXML
    private TableColumn<Joueur, Integer> colequipe;
    @FXML
    private TableColumn<Joueur, String> colphoto;
    @FXML
    private TableColumn<Joueur, Integer> colid;
    @FXML
    private Button btn_modifierjoueur;
    @FXML
    private Button btn_supprimerjoueur;
    @FXML
    private Text tid;
    @FXML
    private Text tnom;
    @FXML
    private Text tprenom;
    @FXML
    private Text tage;
    @FXML
    private Text tposition;
    @FXML
    private Text tdesc;
    @FXML
    private Text tequipe;
    @FXML
    private Text tphoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showJoueur();
        JoueurService joueur = new JoueurService();
        ObservableList<Joueur> list = joueur.afficherJoueursByEquipe(1);
        System.out.println(list.size());

    }

    @FXML
    public void selectJoueur() {
        Joueur selectedJoueur = tablejoueur.getSelectionModel().getSelectedItem();
        if (tablejoueur.getSelectionModel().getSelectedItem() != null) {

            tid.setText(String.valueOf(selectedJoueur.getId()));
            tfnom.setText(selectedJoueur.getNom_joueur());
            tfprenom.setText(selectedJoueur.getPrenom_joueur());
            tfage.setText(String.valueOf(selectedJoueur.getAge()));
            tfposition.setText(selectedJoueur.getPosition());
            tfphoto.setText(selectedJoueur.getPhoto());
            tfequipe.setText(String.valueOf(selectedJoueur.getEquipe()));
            tfdesc.setText(selectedJoueur.getDescription());
            int id = selectedJoueur.getId();
            tablejoueur.getSelectionModel().clearSelection();

        }

        tablejoueur.getSelectionModel().clearSelection();

    }

    public void showJoueur() {
        /*  private int id;
        private String nom_joueur;
        private String prenom_joueur;
        private int age;
        private String position;
        private String photo;
        private int equipe;
        private String Description;*/
        JoueurService joueur = new JoueurService();
        ObservableList<Joueur> list = joueur.afficherJoueurs();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom_joueur"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom_joueur"));
        colage.setCellValueFactory(new PropertyValueFactory<>("age"));
        colposition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colphoto.setCellValueFactory(new PropertyValueFactory<>("photo"));
        colequipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("Description"));

        tablejoueur.setItems(list);

    }

    @FXML
    public void AjouterJoueur() {
        if ("".equals(tfnom.getText())) {
            Platform.runLater(() -> tnom.setText("Veuillez entrer un nom de joueur"));
            Platform.runLater(() -> tprenom.setText(""));
            Platform.runLater(() -> tage.setText(""));
            Platform.runLater(() -> tposition.setText(""));
            Platform.runLater(() -> tdesc.setText(""));
            Platform.runLater(() -> tequipe.setText(""));
            Platform.runLater(() -> tphoto.setText(""));
        } else if ("".equals(tfprenom.getText())) {
            Platform.runLater(() -> tprenom.setText("Veuillez entrer un prénom de joueur"));
            Platform.runLater(() -> tnom.setText(""));
            Platform.runLater(() -> tage.setText(""));
            Platform.runLater(() -> tposition.setText(""));
            Platform.runLater(() -> tdesc.setText(""));
            Platform.runLater(() -> tequipe.setText(""));
            Platform.runLater(() -> tphoto.setText(""));
        } else if ("".equals(tfage.getText())) {
            Platform.runLater(() -> tage.setText("Veuillez entrer l'age de joueur"));
            Platform.runLater(() -> tprenom.setText(""));
            Platform.runLater(() -> tnom.setText(""));
            Platform.runLater(() -> tposition.setText(""));
            Platform.runLater(() -> tdesc.setText(""));
            Platform.runLater(() -> tequipe.setText(""));
            Platform.runLater(() -> tphoto.setText(""));
        } else if ("".equals(tfposition.getText())) {
            Platform.runLater(() -> tposition.setText("Veuillez entrer la position de joueur"));
            Platform.runLater(() -> tprenom.setText(""));
            Platform.runLater(() -> tage.setText(""));
            Platform.runLater(() -> tnom.setText(""));
            Platform.runLater(() -> tdesc.setText(""));
            Platform.runLater(() -> tequipe.setText(""));
            Platform.runLater(() -> tphoto.setText(""));

        } else if ("".equals(tfphoto.getText())) {
            Platform.runLater(() -> tphoto.setText("Veuillez entrer une photo de joueur"));
            Platform.runLater(() -> tprenom.setText(""));
            Platform.runLater(() -> tage.setText(""));
            Platform.runLater(() -> tposition.setText(""));
            Platform.runLater(() -> tdesc.setText(""));
            Platform.runLater(() -> tequipe.setText(""));
            Platform.runLater(() -> tnom.setText(""));

        } else if ("".equals(tfequipe.getText())) {
            Platform.runLater(() -> tequipe.setText("Veuillez entrer l'équipe de joueur"));
            Platform.runLater(() -> tprenom.setText(""));
            Platform.runLater(() -> tage.setText(""));
            Platform.runLater(() -> tposition.setText(""));
            Platform.runLater(() -> tdesc.setText(""));
            Platform.runLater(() -> tnom.setText(""));
            Platform.runLater(() -> tphoto.setText(""));

        } else if ("".equals(tfdesc.getText())) {
            Platform.runLater(() -> tdesc.setText("Veuillez entrer une description de joueur"));
            Platform.runLater(() -> tprenom.setText(""));
            Platform.runLater(() -> tage.setText(""));
            Platform.runLater(() -> tposition.setText(""));
            Platform.runLater(() -> tnom.setText(""));
            Platform.runLater(() -> tequipe.setText(""));
            Platform.runLater(() -> tphoto.setText(""));

        } else {

            JoueurService j = new JoueurService();
            ObservableList<Joueur> list = j.afficherJoueurs();
            System.out.println(list);
            Joueur joueur = new Joueur(Integer.parseInt(tid.getText()), tfnom.getText(), tfprenom.getText(), Integer.parseInt(tfage.getText()), tfposition.getText(), tfphoto.getText(), Integer.parseInt(tfequipe.getText()), tfdesc.getText());
            System.out.println(joueur);
            if (!list.contains(joueur)) {
                System.out.println(!list.contains(joueur));
                j.ajouterJoueur(joueur);
                showJoueur();
                tfnom.clear();
                tfprenom.clear();
                tfage.clear();
                tfposition.clear();
                tfphoto.clear();
                tfequipe.clear();
                tfdesc.clear();
                Platform.runLater(() -> tdesc.setText(""));
                Platform.runLater(() -> tprenom.setText(""));
                Platform.runLater(() -> tage.setText(""));
                Platform.runLater(() -> tposition.setText(""));
                Platform.runLater(() -> tnom.setText(""));
                Platform.runLater(() -> tequipe.setText(""));
                Platform.runLater(() -> tphoto.setText(""));
            } else {
                System.out.println("Account found");
            }

        }

    }

    @FXML
    public void ModifierJoueur() {
        if ("".equals(tid.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez selectionner un joueur!!");
            alert.showAndWait();
        } else {
            JoueurService j = new JoueurService();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation du suppression");
            alert.setContentText("voulez-vous vraiment modifier ce joueur ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                if ("".equals(tfnom.getText())) {
            Platform.runLater(() -> tnom.setText("Veuillez entrer un nom de joueur"));
            Platform.runLater(() -> tprenom.setText(""));
            Platform.runLater(() -> tage.setText(""));
            Platform.runLater(() -> tposition.setText(""));
            Platform.runLater(() -> tdesc.setText(""));
            Platform.runLater(() -> tequipe.setText(""));
            Platform.runLater(() -> tphoto.setText(""));
        } else if ("".equals(tfprenom.getText())) {
            Platform.runLater(() -> tprenom.setText("Veuillez entrer un prénom de joueur"));
            Platform.runLater(() -> tnom.setText(""));
            Platform.runLater(() -> tage.setText(""));
            Platform.runLater(() -> tposition.setText(""));
            Platform.runLater(() -> tdesc.setText(""));
            Platform.runLater(() -> tequipe.setText(""));
            Platform.runLater(() -> tphoto.setText(""));
        } else if ("".equals(tfage.getText())) {
            Platform.runLater(() -> tage.setText("Veuillez entrer l'age de joueur"));
            Platform.runLater(() -> tprenom.setText(""));
            Platform.runLater(() -> tnom.setText(""));
            Platform.runLater(() -> tposition.setText(""));
            Platform.runLater(() -> tdesc.setText(""));
            Platform.runLater(() -> tequipe.setText(""));
            Platform.runLater(() -> tphoto.setText(""));
        } else if ("".equals(tfposition.getText())) {
            Platform.runLater(() -> tposition.setText("Veuillez entrer la position de joueur"));
            Platform.runLater(() -> tprenom.setText(""));
            Platform.runLater(() -> tage.setText(""));
            Platform.runLater(() -> tnom.setText(""));
            Platform.runLater(() -> tdesc.setText(""));
            Platform.runLater(() -> tequipe.setText(""));
            Platform.runLater(() -> tphoto.setText(""));

        } else if ("".equals(tfphoto.getText())) {
            Platform.runLater(() -> tphoto.setText("Veuillez entrer une photo de joueur"));
            Platform.runLater(() -> tprenom.setText(""));
            Platform.runLater(() -> tage.setText(""));
            Platform.runLater(() -> tposition.setText(""));
            Platform.runLater(() -> tdesc.setText(""));
            Platform.runLater(() -> tequipe.setText(""));
            Platform.runLater(() -> tnom.setText(""));

        } else if ("".equals(tfequipe.getText())) {
            Platform.runLater(() -> tequipe.setText("Veuillez entrer l'équipe de joueur"));
            Platform.runLater(() -> tprenom.setText(""));
            Platform.runLater(() -> tage.setText(""));
            Platform.runLater(() -> tposition.setText(""));
            Platform.runLater(() -> tdesc.setText(""));
            Platform.runLater(() -> tnom.setText(""));
            Platform.runLater(() -> tphoto.setText(""));

        } else if ("".equals(tfdesc.getText())) {
            Platform.runLater(() -> tdesc.setText("Veuillez entrer une description de joueur"));
            Platform.runLater(() -> tprenom.setText(""));
            Platform.runLater(() -> tage.setText(""));
            Platform.runLater(() -> tposition.setText(""));
            Platform.runLater(() -> tnom.setText(""));
            Platform.runLater(() -> tequipe.setText(""));
            Platform.runLater(() -> tphoto.setText(""));

        } else {

  
            ObservableList<Joueur> list = j.afficherJoueurs();
            System.out.println(list);
            Joueur joueur = new Joueur(tfnom.getText(), tfprenom.getText(), Integer.parseInt(tfage.getText()), tfposition.getText(), tfphoto.getText(), Integer.parseInt(tfequipe.getText()), tfdesc.getText());
            System.out.println(joueur);
            if (!list.contains(joueur)) {
                System.out.println(!list.contains(joueur));
                j.modifierJoueur(joueur);
                showJoueur();
                tfnom.clear();
                tfprenom.clear();
                tfage.clear();
                tfposition.clear();
                tfphoto.clear();
                tfequipe.clear();
                tfdesc.clear();
                Platform.runLater(() -> tdesc.setText(""));
                Platform.runLater(() -> tprenom.setText(""));
                Platform.runLater(() -> tage.setText(""));
                Platform.runLater(() -> tposition.setText(""));
                Platform.runLater(() -> tnom.setText(""));
                Platform.runLater(() -> tequipe.setText(""));
                Platform.runLater(() -> tphoto.setText(""));
            } else {
                System.out.println("Account found");
            }

        }

            } else {
                System.out.println(tid.getText());
                tid.setText("");
                System.out.println(tid.getText());
                
                tfnom.clear();
                tfprenom.clear();
                tfage.clear();
                tfposition.clear();
                tfphoto.clear();
                tfequipe.clear();
                tfdesc.clear();

            }

        }

    }

    @FXML
    public void SupprimerJoueur() {

        JoueurService j = new JoueurService();
        if ("".equals(tid.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez selectionner un joueur!!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation du suppression");
            alert.setContentText("voulez-vous vraiment supprimer ce joueur ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                j.supprimerJoueur(Integer.parseInt(tid.getText()));
                showJoueur();
                tfnom.clear();
                tfprenom.clear();
                tfage.clear();
                tfposition.clear();
                tfphoto.clear();
                tfequipe.clear();
                tfdesc.clear();
            } else {
                tid.setText("");
                tfnom.clear();
                tfprenom.clear();
                tfage.clear();
                tfposition.clear();
                tfphoto.clear();
                tfequipe.clear();
                tfdesc.clear();
            }
        }

    }

    @FXML
    private void dashboard(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
