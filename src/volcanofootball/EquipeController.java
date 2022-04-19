/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volcanofootball;

import Entities.Equipe;
import Services.EquipeService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
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
public class EquipeController implements Initializable {

    @FXML
    private Button btn_ajouterequipe;
    @FXML
    private Button retourDash;
    @FXML
    private TableView<Equipe> tableequipe;
    @FXML
    private TableColumn<Equipe, String> colequipe;
    @FXML
    private TableColumn<Equipe, Date> coldate;
    @FXML
    private TableColumn<Equipe, String> colnomentre;
    @FXML
    private TableColumn<Equipe, String> coldrapeau;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfnomequipe;
    @FXML
    private TextField tfnomentre;
    @FXML
    private TextField tfdrapeau;
    @FXML
    private Text tid;
    @FXML
    private Button btn_modifierequipe;
    @FXML
    private Button btn_supprimerequipe;
    @FXML
    private Text tnom;
    @FXML
    private Text tdate;
    @FXML
    private Text tentre;
    @FXML
    private Text tdrapeau;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showEquipe();
    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @FXML
    public void selectEquipe() {
        Equipe selectedEquipe = tableequipe.getSelectionModel().getSelectedItem();
        if (tableequipe.getSelectionModel().getSelectedItem() != null) {

            tid.setText(String.valueOf(selectedEquipe.getId()));
            tfnomequipe.setText(selectedEquipe.getNom_equipe());
            tfnomentre.setText(selectedEquipe.getNom_entreneur());
            tfdrapeau.setText(selectedEquipe.getDrapeau_equipe());
            tfdate.setValue(LOCAL_DATE(selectedEquipe.getDate_creation().toString()));

            tableequipe.getSelectionModel().clearSelection();

        }

        tableequipe.getSelectionModel().clearSelection();

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
            Platform.runLater(() -> tnom.setText("Veuillez entrer un nom d'equipe"));
            Platform.runLater(() -> tdrapeau.setText(""));
            Platform.runLater(() -> tdate.setText(""));
            Platform.runLater(() -> tentre.setText(""));
        } else if ("".equals(tfnomentre.getText())) {
            Platform.runLater(() -> tentre.setText("Veuillez entrer un nom d'entreneur"));
            Platform.runLater(() -> tnom.setText(""));
            Platform.runLater(() -> tdate.setText(""));
            Platform.runLater(() -> tdrapeau.setText(""));
        } else if (tfdate == null) {
            Platform.runLater(() -> tdate.setText("Veuillez entrer une date"));
            Platform.runLater(() -> tnom.setText(""));
            Platform.runLater(() -> tdrapeau.setText(""));
            Platform.runLater(() -> tentre.setText(""));
        } else if ("".equals(tfdrapeau.getText())) {
            Platform.runLater(() -> tdrapeau.setText("Veuillez entrer un drapeau"));
            Platform.runLater(() -> tnom.setText(""));
            Platform.runLater(() -> tdate.setText(""));
            Platform.runLater(() -> tentre.setText(""));

        } else {

            Equipe equipe = new Equipe(tfnomequipe.getText(), gettedDatePickerDate, tfnomentre.getText(), tfdrapeau.getText());

            EquipeService es = new EquipeService();
            ObservableList<Equipe> list = es.afficherEquipe();
            if (!list.contains(equipe)) {
                es.ajouterEquipe(equipe);
                showEquipe();
                tfnomequipe.clear();
                tfdrapeau.clear();
                tfnomentre.clear();
                Platform.runLater(() -> tdrapeau.setText(""));
                Platform.runLater(() -> tnom.setText(""));
                Platform.runLater(() -> tdate.setText(""));
                Platform.runLater(() -> tentre.setText(""));
            }

        }

    }

    @FXML
    public void ModifierEquipe() {
        if ("".equals(tid.getText())) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez selectionner une equipe!!");
            alert.showAndWait();
        } else {

            EquipeService e = new EquipeService();
            java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(tfdate.getValue());
            Equipe equipe = new Equipe(Integer.parseInt(tid.getText()), tfnomequipe.getText(), gettedDatePickerDate, tfnomentre.getText(), tfdrapeau.getText());
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation du suppression");
            alert.setContentText("voulez-vous vraiment modifier cette equipe ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                String pattern = "yyyy-MM-dd";
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

                //dateFormatter.format(tfdate.getValue());
                if ("".equals(tfnomequipe.getText())) {
                    Platform.runLater(() -> tnom.setText("Veuillez entrer un nom d'equipe"));
                    Platform.runLater(() -> tdrapeau.setText(""));
                    Platform.runLater(() -> tdate.setText(""));
                    Platform.runLater(() -> tentre.setText(""));
                } else if ("".equals(tfnomentre.getText())) {
                    Platform.runLater(() -> tentre.setText("Veuillez entrer un nom d'entreneur"));
                    Platform.runLater(() -> tnom.setText(""));
                    Platform.runLater(() -> tdate.setText(""));
                    Platform.runLater(() -> tdrapeau.setText(""));
                } else if (tfdate == null) {
                    Platform.runLater(() -> tdate.setText("Veuillez entrer une date"));
                    Platform.runLater(() -> tnom.setText(""));
                    Platform.runLater(() -> tdrapeau.setText(""));
                    Platform.runLater(() -> tentre.setText(""));
                } else if ("".equals(tfdrapeau.getText())) {
                    Platform.runLater(() -> tdrapeau.setText("Veuillez entrer un drapeau"));
                    Platform.runLater(() -> tnom.setText(""));
                    Platform.runLater(() -> tdate.setText(""));
                    Platform.runLater(() -> tentre.setText(""));

                } else {

                    EquipeService es = new EquipeService();
                    ObservableList<Equipe> list = es.afficherEquipe();
                    if (!list.contains(equipe)) {
                        es.modifierEquipe(equipe);
                        showEquipe();
                        tfnomequipe.clear();
                        tfdrapeau.clear();
                        tfnomentre.clear();
                        Platform.runLater(() -> tdrapeau.setText(""));
                        Platform.runLater(() -> tnom.setText(""));
                        Platform.runLater(() -> tdate.setText(""));
                        Platform.runLater(() -> tentre.setText(""));
                    }

                }

            } else {
                tid.setText("");
                tfnomequipe.clear();
                tfdrapeau.clear();
                tfnomentre.clear();
            }
        }

    }

    @FXML
    public void SupprimerEquipe() {
        if ("".equals(tid.getText())) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Veuillez selectionner une equipe!!");
            alert.showAndWait();
        } else {

            EquipeService e = new EquipeService();

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation du suppression");
            alert.setContentText("voulez-vous vraiment supprimer cette equipe ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                e.supprimerEquipe(Integer.parseInt(tid.getText()));
                showEquipe();
                tfnomequipe.clear();
                tfdrapeau.clear();
                tfnomentre.clear();

            } else {
                tid.setText("");
                tfnomequipe.clear();
                tfdrapeau.clear();
                tfnomentre.clear();
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
