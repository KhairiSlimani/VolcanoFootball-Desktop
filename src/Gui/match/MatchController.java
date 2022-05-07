/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.match;

import Entities.Match;
import Gui.SessionManager;
import Gui.agence.AgenceController;
import static Gui.print.printNode;
import Services.ServiceMatch;
import com.jfoenix.controls.JFXDialog;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author DeLL
 */
public class MatchController implements Initializable {

    @FXML
    private ScrollPane ScrollPane;
    @FXML
    private FlowPane FlowPane;
    @FXML
    private StackPane container;
    @FXML
    private JFXDialog dialog;
    @FXML
    private TableView<Match> tableview;

    @FXML
    private TableColumn<Match, Integer> col_id;

    @FXML
    private TableColumn<Match, String> col_NomM;

    @FXML
    private TableColumn<Match, String> col_NomA;

    @FXML
    private TableColumn<Match, String> col_Stade;

    @FXML
    private TableColumn<Match, String> col_Tour;

    @FXML
    private TableColumn<Match, String> col_Date;

    ObservableList<Match> obl = FXCollections.observableArrayList();
    ObservableList<Match> listM;
    ObservableList<Match> dataList;
    int index = -1;
    Connection cnx = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private TextField TFId;
    @FXML
    private TextField TFNomMatch;
    @FXML
    private TextField TFNomArbitre;
    @FXML
    private TextField TFStade;
    @FXML
    private TextField TFTour;
    private Button btn_quitter;
    @FXML
    private DatePicker TFDate;
    @FXML
    private Button btn_météo;
    @FXML
    private TextField TFFilter;
    @FXML
    private ComboBox<?> triBox;
    @FXML
    private Button print;
    @FXML
    private AnchorPane form;

    /**
     * Initializes the controller class.
     */
    

   @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
        index = tableview.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        TFId.setText(col_id.getCellData(index).toString());
        TFNomMatch.setText(col_NomM.getCellData(index).toString());
        TFNomArbitre.setText(col_NomA.getCellData(index).toString());
        TFStade.setText(col_Stade.getCellData(index).toString());
        TFTour.setText(col_Tour.getCellData(index).toString());
    }
     
   
 
    public void updateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Match, Integer>("id"));
        col_NomM.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_match"));
        col_NomA.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_arbitre"));
        col_Stade.setCellValueFactory(new PropertyValueFactory<Match, String>("stade"));
        col_Tour.setCellValueFactory(new PropertyValueFactory<Match, String>("tour"));

        col_Date.setCellValueFactory(new PropertyValueFactory<Match, String>("date"));

        try {
            listM = ServiceMatch.getInstance().getAll();
            tableview.setItems(listM);
        } catch (SQLException ex) {
            Logger.getLogger(MatchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
 if(SessionManager.get().getRole().equals("Client"))
        {
            form.setVisible(false);
        }
        updateTable();

    }

    @FXML
    void add(ActionEvent event) {
        ServiceMatch sc = new ServiceMatch();
        Match C = new Match();
        C.setNom_match(TFNomMatch.getText());
        C.setNom_arbitre(TFNomArbitre.getText());
        C.setStade(TFStade.getText());
        C.setTour(TFTour.getText());
        C.setDate(String.valueOf(TFDate.getValue().toString()));
        // C.setDate(TFDate.getText());

        if (TFNomMatch.getText().length() == 0) {
            TFNomMatch.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
            new animatefx.animation.Shake(TFNomMatch).play();
        } else {
            TFNomMatch.setStyle(null);
        }

        if (TFNomArbitre.getText().length() == 0) {
            TFNomArbitre.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
            new animatefx.animation.Shake(TFNomArbitre).play();
        } else {
            TFNomArbitre.setStyle(null);
        }
        if (TFStade.getText().length() == 0) {
            TFStade.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
            new animatefx.animation.Shake(TFStade).play();
        } else {
            TFStade.setStyle(null);
        }
        if (TFTour.getText().length() == 0) {
            TFTour.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
            new animatefx.animation.Shake(TFTour).play();
        } else {
            TFTour.setStyle(null);
        }

        if (TFNomMatch.getText().isEmpty() || TFNomArbitre.getText().isEmpty() || TFStade.getText().isEmpty() || TFTour.getText().isEmpty() || TFDate.getValue().equals(null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {

            try {
                System.out.println(TFNomMatch.getText());
                System.out.println(TFNomArbitre.getText());
                System.out.println(TFStade.getText());
                System.out.println(TFTour.getText());
                System.out.println(String.valueOf(TFDate.getValue().toString()));
                sc.add(C);
                updateTable();

                JOptionPane.showMessageDialog(null, "match a ete ajouter avec succes");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            TFNomMatch.setText("");
            TFNomArbitre.setText(" ");
            TFStade.setText(" ");
            TFTour.setText("");

            // TFDate.setText(" ");
            
            
        }
         // Image img = new Image("/ball.png") {};
            Notifications notificationBuilder = Notifications.create()
                    .title("Ajout avec succée")
                    .text("match ajouter avec succée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_LEFT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override
            public void handle(ActionEvent event) {
                System.out.println("Clicked on notification"); 
            }
        
        });
        notificationBuilder.darkStyle();
        notificationBuilder.showInformation();

    }

    @FXML
    private void Edit(ActionEvent event) {
        ServiceMatch SC = new ServiceMatch();
        Match C = new Match();
        C.setNom_match(TFNomMatch.getText());
        C.setNom_arbitre(TFNomArbitre.getText());

        C.setStade(TFStade.getText());
        C.setTour(TFTour.getText());
        C.setDate(String.valueOf(TFDate.getValue().toString()));
        C.setId(Integer.parseInt(TFId.getText()));
        if (TFNomMatch.getText().length() == 0) {
            TFNomMatch.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
            new animatefx.animation.Shake(TFNomMatch).play();
        } else {
            TFNomMatch.setStyle(null);
        }

        if (TFNomArbitre.getText().length() == 0) {
            TFNomArbitre.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
            new animatefx.animation.Shake(TFNomArbitre).play();
        } else {
            TFNomArbitre.setStyle(null);
        }
        if (TFStade.getText().length() == 0) {
            TFStade.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
            new animatefx.animation.Shake(TFStade).play();
        } else {
            TFStade.setStyle(null);
        }
        if (TFTour.getText().length() == 0) {
            TFTour.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
            new animatefx.animation.Shake(TFTour).play();
        } else {
            TFTour.setStyle(null);
        }

        if (TFNomMatch.getText().isEmpty() || TFNomArbitre.getText().isEmpty() || TFStade.getText().isEmpty() || TFTour.getText().isEmpty() || TFDate.getValue().equals(null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {

            try {

                SC.updateMatch(C);
                updateTable();

                //JOptionPane.showMessageDialog(null, "L'employé à été modifier avec succes");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);

            }
            TFNomMatch.setText("");
            TFNomArbitre.setText(" ");
            TFStade.setText(" ");
            TFTour.setText("");

            //  TFDate.setText(" ");
        }
          Notifications notificationBuilder = Notifications.create()
                    .title("Modification avec succée")
                    .text("match modifier avec succée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_LEFT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override
            public void handle(ActionEvent event) {
                System.out.println("Clicked on notification"); 
            }
        
        });
        notificationBuilder.darkStyle();
        notificationBuilder.showInformation();
    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException {

     ServiceMatch us=new ServiceMatch();
        Match u= tableview.getSelectionModel().getSelectedItem();
         
          
                us.delete(u.getId());
    
          updateTable();
    }

    private void Quitter(ActionEvent event) throws Exception {
        try {
            btn_quitter.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/FXMLMenu.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);

            mainStage.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

   

    /*
     public void searchConge(){
        col_id.setCellValueFactory(new PropertyValueFactory<Conge,Integer>("IdConge"));
        
        col_TypeConge.setCellValueFactory(new PropertyValueFactory<Conge,String>("TypeConge"));
        col_IdEmploye.setCellValueFactory(new PropertyValueFactory<Conge, ComboBox>("IdEmploye"));
        
         col_DebutConge.setCellValueFactory(new PropertyValueFactory<Conge,String>("DebutConge"));
        col_FinConge.setCellValueFactory(new PropertyValueFactory<Conge,String>("FinConge"));
         try{
             dataList=ServiceConge.getInstance().getAll();
          tableview.setItems(dataList);
          FilteredList<Conge> filtredData = new FilteredList<>(dataList, b -> true);
          TFFilter.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(person-> {
                 if(newValue == null|| newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                
                 if(person.getTypeConge().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }
                 else if(String.valueOf(person.getIdConge()).indexOf(lowerCaseFilter)!=-1)
                     return true;
                     else
                     return false;
                 });
             });
         SortedList<Conge> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(tableview.comparatorProperty());
         tableview.setItems(sortedData);

         }catch(Exception e){
             System.out.println(e.getMessage());
             
         }          
          
}*/

  

   
    
    
    @FXML
    private void interfacemétéo(ActionEvent event) {
        try{
             btn_météo.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("match/primary.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                 mainStage.setTitle("Interfce météo");
                mainStage.show();
                //JOptionPane.showMessageDialog(null, "Bienvenue dans votre interface des billets");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
     public void searchConge(){
      
        updateTable();
         try{
             dataList=ServiceMatch.getInstance().getAll();
          tableview.setItems(dataList);
          FilteredList<Match> filtredData = new FilteredList<>(dataList, b -> true);
          TFFilter.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(person-> {
                 if(newValue == null|| newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                
                 if(person.getNom_match().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }
                 else if(String.valueOf(person.getStade()).indexOf(lowerCaseFilter)!=-1)
                     return true;
                     else
                     return false;
                 });
             });
         SortedList<Match> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(tableview.comparatorProperty());
         tableview.setItems(sortedData);

         }catch(Exception e){
             System.out.println(e.getMessage());
             
         }          
          
          
     }

   @FXML
    public void trie() {
        Comparator<Match> comparator = null;
        if (triBox.getValue() == "Id") {
            comparator = Comparator.comparingInt(Match::getId);

        } else if (triBox.getValue() == "NomMatch") {
            comparator = Comparator.comparing(Match::getNom_match);

        }

        FXCollections.sort(obl, comparator);
        tableview.setItems(obl);
        updateTable();

    }
 @FXML
    private void print(ActionEvent event) {
          try {
            printNode(tableview);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(AgenceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AgenceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AgenceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(AgenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}
