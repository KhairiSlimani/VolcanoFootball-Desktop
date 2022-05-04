/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.equipe;

import Entities.Equipe;
import Services.EquipeService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Gui.equipe.SenmailequipeController;
import Gui.produit.AddProduitController;
import Gui.user.AddUserController;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author jrady
 */
public class EquipeController implements Initializable {
    
    private Parent root;
    @FXML
    private FlowPane FlowPane;
    @FXML
    private TableView<Equipe> tableequipe;
    @FXML
    private TableColumn<Equipe, String> colequipe;
    @FXML
    private TableColumn<Equipe, Date> coldate;
    @FXML
    private TableColumn<Equipe, String> colnomentre;
    @FXML
    private TableColumn<Equipe, String> colmail;
    @FXML
    private TableColumn<Equipe, String> coldrapeau;
    @FXML
    private TableColumn<Equipe, Integer> colrang;
    @FXML
    private JFXTextField tfnomequipe;
    @FXML
    private JFXTextField tfnomentre;
    @FXML
    private JFXTextField tfmail;
    @FXML
    private JFXTextField tfdrapeau;
    @FXML
    private JFXTextField tfrang;
    @FXML
    private DatePicker tfdate;
    @FXML
    private Text tid;

    @FXML
    private JFXDialog dialog;
    @FXML
    private Text equipe1;
    @FXML
    private Text equipe2;
    @FXML
    private Text equipe3;
    @FXML
    private JFXTextField tfsearch;
    @FXML
    private StackPane sp;
    @FXML
    private StackPane container;
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showEquipe();
        RangEquipe();
        EquipeService es = new EquipeService();
        ObservableList<Equipe> list = es.afficherEquipe();
        tfsearch.textProperty().addListener((observable, oldValue, newValue)
                -> tableequipe.setItems(filterList(list, newValue))
        );
    }

    private boolean searchFindsEquipe(Equipe equipe, String searchText) {
        return (equipe.getNom_equipe().toLowerCase().contains(searchText.toLowerCase()))
                || (equipe.getEmail().toLowerCase().contains(searchText.toLowerCase()))
                || (equipe.getNom_entreneur().toLowerCase().contains(searchText.toLowerCase()))
                || Integer.valueOf(equipe.getId()).toString().equals(searchText.toLowerCase());

    }

    private Predicate<Equipe> createPredicate(String searchText) {
        return equipe -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            return searchFindsEquipe(equipe, searchText);
        };
    }

    private ObservableList<Equipe> filterList(List<Equipe> list, String searchText) {
        List<Equipe> filteredList = new ArrayList<>();
        for (Equipe equipe : list) {
            if (searchFindsEquipe(equipe, searchText)) {
                filteredList.add(equipe);
            }
        }
        return FXCollections.observableList(filteredList);
    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @FXML
    public void selectEquipe() {
        Equipe selectedEquipe = tableequipe.getSelectionModel().getSelectedItem();
        System.out.println(selectedEquipe.getEmail());
        if (tableequipe.getSelectionModel().getSelectedItem() != null) {

            tid.setText(String.valueOf(selectedEquipe.getId()));
            tfnomequipe.setText(selectedEquipe.getNom_equipe());
            tfnomentre.setText(selectedEquipe.getNom_entreneur());
            tfdrapeau.setText(selectedEquipe.getDrapeau_equipe());
            tfrang.setText(String.valueOf(selectedEquipe.getRang()));
            tfmail.setText(selectedEquipe.getEmail());
            tfdate.setValue(LOCAL_DATE(selectedEquipe.getDate_creation().toString()));
            tableequipe.getSelectionModel().clearSelection();

        }

        tableequipe.getSelectionModel().clearSelection();

    }

    public void RangEquipe() {

        EquipeService es = new EquipeService();
        ObservableList<Equipe> list = es.afficherEquipeOrderBy();
        System.out.println(list);
        int k = 0;
        for (Equipe e : list) {
            switch (k) {
                case 0:
                    equipe1.setText(e.getNom_equipe());
                    k++;
                    break;
                case 1:
                    equipe2.setText(e.getNom_equipe());
                    k++;
                    break;
                case 2:
                    equipe3.setText(e.getNom_equipe());
                    k++;
                    break;
                default:
                    break;
            }
        }
    }

    public void showEquipe() {
        EquipeService es = new EquipeService();
        ObservableList<Equipe> list = es.afficherEquipe();
        System.out.println(list.toString());
        System.out.println(list);
        colequipe.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        colnomentre.setCellValueFactory(new PropertyValueFactory<>("nom_entreneur"));
        coldrapeau.setCellValueFactory(new PropertyValueFactory<>("drapeau_equipe"));
        colmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colrang.setCellValueFactory(new PropertyValueFactory<>("rang"));
        tableequipe.setItems(list);

    }

    @FXML
    public void AjouterEquipe() {
        //Equipe( int joueur, String nom_equipe, Date date_creation,String nom_entreneur)

        String pattern = "yyyy-MM-dd";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(dateFormatter.format(tfdate.getValue()));
        //dateFormatter.format(tfdate.getValue());

        if ("".equals(tfnomequipe.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez entrer un nom d'equipe!!");
            alert.showAndWait();
        } else if ("".equals(tfnomentre.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez entrer un nom d'entreneur!!");
            alert.showAndWait();

        } else if (tfdate == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez entrer une date!!");
            alert.showAndWait();

        } else if ("".equals(tfdrapeau.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez entrer un drapeau!!");
            alert.showAndWait();

        } else if ("".equals(tfrang.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez entrer le rang d'equipe!!");
            alert.showAndWait();

        } else {
            EquipeService es = new EquipeService();
            Equipe equipe = new Equipe(tfnomequipe.getText(), gettedDatePickerDate, tfnomentre.getText(), tfdrapeau.getText(), tfmail.getText(), Integer.parseInt(tfrang.getText()));

            ObservableList<Equipe> list = es.afficherEquipe();
            if (!list.contains(equipe)) {
                es.ajouterEquipe(equipe);
                showEquipe();
                RangEquipe();
                tid.setText("");
                tfnomequipe.clear();
                tfdrapeau.clear();
                tfnomentre.clear();
                tfmail.clear();

            }

        }

    }

    @FXML
    public void ModifierEquipe() {
        if ("".equals(tid.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez selectionner une equipe!!");
            alert.showAndWait();
        } else {

            EquipeService e = new EquipeService();
            java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(tfdate.getValue());
            Equipe equipe = new Equipe(tfnomequipe.getText(), gettedDatePickerDate, tfnomentre.getText(), tfdrapeau.getText(), tfmail.getText(), Integer.parseInt(tfrang.getText()));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation du suppression");
            alert.setContentText("voulez-vous vraiment modifier cette equipe ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                String pattern = "yyyy-MM-dd";
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

                //dateFormatter.format(tfdate.getValue());
                if ("".equals(tfnomequipe.getText())) {
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Attention");
                    alert1.setContentText("Veuillez entrer un nom d'equipe!!");
                    alert1.showAndWait();
                } else if ("".equals(tfnomentre.getText())) {
                    Alert alert2 = new Alert(Alert.AlertType.WARNING);
                    alert2.setTitle("Attention");
                    alert2.setContentText("Veuillez entrer un nom d'entreneur!!");
                    alert2.showAndWait();
                } else if (tfdate == null) {
                    Alert alert3 = new Alert(Alert.AlertType.WARNING);
                    alert3.setTitle("Attention");
                    alert3.setContentText("Veuillez entrer une date!!");
                    alert3.showAndWait();
                } else if ("".equals(tfdrapeau.getText())) {
                    Alert alert4 = new Alert(Alert.AlertType.WARNING);
                    alert4.setTitle("Attention");
                    alert4.setContentText("Veuillez entrer un drapeau!!");
                    alert4.showAndWait();

                } else {

                    EquipeService es = new EquipeService();
                    ObservableList<Equipe> list = es.afficherEquipe();

                    es.modifierEquipe(equipe);
                    showEquipe();
                    RangEquipe();
                    tid.setText("");
                    tfnomequipe.clear();
                    tfdrapeau.clear();
                    tfnomentre.clear();

                }

            } else {
                tid.setText("");
                tfnomequipe.clear();
                tfdrapeau.clear();
                tfnomentre.clear();
                tfmail.clear();
            }
        }

    }

    @FXML
    public void SupprimerEquipe() {
        if ("".equals(tid.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez selectionner une equipe!!");
            alert.showAndWait();
        } else {

            EquipeService e = new EquipeService();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation du suppression");
            alert.setContentText("voulez-vous vraiment supprimer cette equipe ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                e.supprimerEquipe(Integer.parseInt(tid.getText()));
                showEquipe();
                RangEquipe();
                tid.setText("");
                tfnomequipe.clear();
                tfdrapeau.clear();
                tfnomentre.clear();
                tfmail.clear();

            } else {
                tid.setText("");
                tfnomequipe.clear();
                tfdrapeau.clear();
                tfnomentre.clear();
                tfmail.clear();
            }
        }

    }

    @FXML
    public void showEquipeTrier() {
        EquipeService es = new EquipeService();
        ObservableList<Equipe> list = es.afficherEquipeOrderBy();
        System.out.println(list.toString());
        System.out.println(list);
        colequipe.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        colnomentre.setCellValueFactory(new PropertyValueFactory<>("nom_entreneur"));
        coldrapeau.setCellValueFactory(new PropertyValueFactory<>("drapeau_equipe"));
        colmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colrang.setCellValueFactory(new PropertyValueFactory<>("rang"));
        tableequipe.setItems(list);
    }
     public void setInfos(BorderPane p){
        borderPane = p;
    }
    @FXML
    private void sendMail(MouseEvent event) throws IOException {
        if ("".equals(tid.getText())) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez selectionner une equipe!!");
            alert.showAndWait();
        } else {
            String mail = tfmail.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("senmailequipe.fxml"));
            root = loader.load();
            SenmailequipeController sendmailequipeController = loader.getController();
            sendmailequipeController.displayMail(mail);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

  /* @FXML
   public void ShowSendMail(ActionEvent event) {
        if ("".equals(tid.getText())) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez selectionner une equipe!!");
            alert.showAndWait();
        } else {
            String mail = tfmail.getText();
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("sendmailequipe.fxml"));
                AnchorPane ap = loader.load();
                SenmailequipeController sendmailequipeController = loader.getController();
                sendmailequipeController.displayMail(mail);
                sendmailequipeController.setInfos(dialog, sp);
                sendmailequipeController.getCloseButton().setOnAction(ActionEvent -> {
                    dialog.close();
                });
                dialog.getChildren().add(ap);
                dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
                dialog.setDialogContainer(sp);
                dialog.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

}
