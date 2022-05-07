/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.joueur;

import Entities.Joueur;
import Gui.SessionManager;
import Gui.commande.AddCommandeController;
import Services.JoueurService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author jrady
 */
public class JoueurController implements Initializable {

    @FXML
    private StackPane container;
    @FXML
    private FlowPane FlowPane;
    @FXML
    private JFXDialog dialog;
    @FXML
    private TableView<Joueur> tablejoueur;
    @FXML
    private TableColumn<Joueur, Integer> colid;
    @FXML
    private TableColumn<Joueur, String> colnom;
    @FXML
    private TableColumn<Joueur, String> colprenom;
    @FXML
    private TableColumn<Joueur, Integer> colage;
    @FXML
    private TableColumn<Joueur, String> colposition;
    @FXML
    private TableColumn<Joueur, String> coldesc;
    @FXML
    private TableColumn<Joueur, Integer> colequipe;
    @FXML
    private TableColumn<Joueur, String> colphoto;
    @FXML
    private Text tid;
    @FXML
    private JFXTextField tfnom;
    @FXML
    private JFXTextField tfprenom;
    @FXML
    private JFXTextField tfage;
    @FXML
    private JFXTextField tfposition;
    @FXML
    private JFXTextField tfdesc;
    @FXML
    private JFXTextField tfequipe;
    @FXML
    private JFXTextField tfphoto;
    @FXML
    private AnchorPane form;

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
        
         if(SessionManager.get().getRole().equals("Client"))
        {
            form.setVisible(false);
        }
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
                tid.setText("");

            }
        }

    }

    @FXML
    public void AjouterJoueur() {
        if ("".equals(tfnom.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez entrer un nom de joueur!!");
            alert.showAndWait();
        } else if ("".equals(tfprenom.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez entrer un prénom de joueur!!");
            alert.showAndWait();
        } else if ("".equals(tfage.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez entrer l'age de joueur!!");
            alert.showAndWait();
        } else if ("".equals(tfposition.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez entrer la position de joueur!!");
            alert.showAndWait();

        } else if ("".equals(tfphoto.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez entrer une photo de joueur!!");
            alert.showAndWait();

        } else if ("".equals(tfequipe.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez entrer l'equipe de joueur!!");
            alert.showAndWait();

        } else if ("".equals(tfdesc.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez entrer une description de joueur!!");
            alert.showAndWait();

        } else {

            JoueurService j = new JoueurService();
            ObservableList<Joueur> list = j.afficherJoueurs();
            System.out.println(list);
            Joueur joueur = new Joueur(tfnom.getText(), tfprenom.getText(), Integer.parseInt(tfage.getText()), tfposition.getText(), tfphoto.getText(), Integer.parseInt(tfequipe.getText()), tfdesc.getText());
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
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Attention");
                    alert1.setContentText("Veuillez entrer un nom de joueur!!");
                    alert1.showAndWait();
                } else if ("".equals(tfprenom.getText())) {
                    Alert alert2 = new Alert(Alert.AlertType.WARNING);
                    alert2.setTitle("Attention");
                    alert2.setContentText("Veuillez entrer un prénom de joueur!!");
                    alert2.showAndWait();
                } else if ("".equals(tfage.getText())) {
                    Alert alert3 = new Alert(Alert.AlertType.WARNING);
                    alert3.setTitle("Attention");
                    alert3.setContentText("Veuillez entrer l'age de joueur!!");
                    alert3.showAndWait();
                } else if ("".equals(tfposition.getText())) {
                    Alert alert4 = new Alert(Alert.AlertType.WARNING);
                    alert4.setTitle("Attention");
                    alert4.setContentText("Veuillez entrer la position de joueur!!");
                    alert4.showAndWait();

                } else if ("".equals(tfphoto.getText())) {
                    Alert alert5 = new Alert(Alert.AlertType.WARNING);
                    alert5.setTitle("Attention");
                    alert5.setContentText("Veuillez entrer une photo de joueur!!");
                    alert5.showAndWait();

                } else if ("".equals(tfequipe.getText())) {
                    Alert alert6 = new Alert(Alert.AlertType.WARNING);
                    alert6.setTitle("Attention");
                    alert6.setContentText("Veuillez entrer l'equipe de joueur!!");
                    alert6.showAndWait();

                } else if ("".equals(tfdesc.getText())) {
                    Alert alert7 = new Alert(Alert.AlertType.WARNING);
                    alert7.setTitle("Attention");
                    alert7.setContentText("Veuillez entrer une description de joueur!!");
                    alert7.showAndWait();

                } else {

                    ObservableList<Joueur> list = j.afficherJoueurs();
                    System.out.println(list);
                    Joueur joueur = new Joueur(tfnom.getText(), tfprenom.getText(), Integer.parseInt(tfage.getText()), tfposition.getText(), tfphoto.getText(), Integer.parseInt(tfequipe.getText()), tfdesc.getText());
                    System.out.println(joueur);
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

}
