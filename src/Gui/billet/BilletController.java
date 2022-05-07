/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.billet;

import Entities.Billet;
import Entities.Match;
import Gui.SessionManager;
import Services.ServiceBillet;
import com.jfoenix.controls.JFXDialog;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author DeLL
 */
public class BilletController implements Initializable {

    @FXML
    private ScrollPane ScrollPane;
    @FXML
    private FlowPane FlowPane;
     @FXML
    private TableView<Billet> tableview;
    @FXML
    private TableColumn<Billet, Integer> col_id;
    @FXML
    private TableColumn<Billet, String> col_type;
    @FXML
    private TableColumn<Billet, String> col_prix;
    @FXML
    private StackPane container;
    @FXML
    private JFXDialog dialog;
    @FXML
    private TextField TFId;
    @FXML
    private TextField TFType;
    @FXML
    private TextField TFPrix;
    private Button btn_quitter;
    @FXML
    private TextField TFFilter;
    ObservableList<Billet> obl = FXCollections.observableArrayList();
    ObservableList<Billet> listM;
    ObservableList<Billet> dataList;
    int index = -1;
    Connection cnx = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private ComboBox<?> triBox;
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
        TFType.setText(col_type.getCellData(index).toString());
        TFPrix.setText(col_prix.getCellData(index).toString());

    }
     public void updateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Billet, Integer>("id"));
        col_type.setCellValueFactory(new PropertyValueFactory<Billet, String>("type"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Billet, String>("prix"));

        try {
            listM = ServiceBillet.getInstance().getAll();
            tableview.setItems(listM);
        } catch (SQLException ex) {
            Logger.getLogger(BilletController.class.getName()).log(Level.SEVERE, null, ex);
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
        ServiceBillet sc = new ServiceBillet();
        Billet C = new Billet();
        C.setType(TFType.getText());
        C.setPrix(TFPrix.getText());

        if (TFType.getText().length() == 0) {
            TFType.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
            new animatefx.animation.Shake(TFType).play();
        } else {
            TFType.setStyle(null);
        }
        if (TFPrix.getText().length() == 0) {
            TFPrix.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
            new animatefx.animation.Shake(TFPrix).play();
        } else {
            TFPrix.setStyle(null);
        }
        if (TFType.getText().isEmpty() || TFPrix.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        } else {
            try {
                sc.add(C);
                updateTable();

                JOptionPane.showMessageDialog(null, "Billet a ete ajouter avec succes");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            TFType.setText("");
            TFPrix.setText(" ");

        }
  Notifications notificationBuilder = Notifications.create()
                    .title("Ajout avec succée")
                    .text("billet ajouter avec succée")
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
        ServiceBillet SC = new ServiceBillet();
        Billet C = new Billet();
        C.setType(TFType.getText());
        C.setPrix(TFPrix.getText());

        C.setId(Integer.parseInt(TFId.getText()));
        if (TFType.getText().length() == 0) {
            TFType.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
            new animatefx.animation.Shake(TFType).play();
        } else {
            TFType.setStyle(null);
        }
        if (TFPrix.getText().length() == 0) {
            TFPrix.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
            new animatefx.animation.Shake(TFPrix).play();
        } else {
            TFPrix.setStyle(null);
        }
        if (TFType.getText().isEmpty() || TFPrix.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        } else {

            try {

                SC.updateBillet(C);
                updateTable();

                JOptionPane.showMessageDialog(null, "billet à été modifier avec succes");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);

            }
            TFType.setText("");
            TFPrix.setText(" ");
        }
          Notifications notificationBuilder = Notifications.create()
                    .title("Modification avec succée")
                    .text("billet modifier avec succé")
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
    private void Delete(ActionEvent event) {

        try {
            ServiceBillet SC = new ServiceBillet();
            Match C = new Match();

            SC.delete(TFId.getText());
           // JOptionPane.showMessageDialog(null, "Billet  a été supprimer avec succes");
            updateTable();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }
        TFType.setText("");
        TFPrix.setText(" ");
          Notifications notificationBuilder = Notifications.create()
                    .title("supprission avec succée")
                    .text("billet supprimer avec succée ")
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

    private void Quitter(ActionEvent event) throws Exception {
        try {
            btn_quitter.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/dashboard.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);

            mainStage.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    @FXML
    private void stats(ActionEvent event)throws Exception  {
         try{
            btn_quitter.getScene().getWindow().hide();
                Parent root =FXMLLoader.load(getClass().getResource("bilet/FXMLStats.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
               
                mainStage.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            
        }
    }
    @FXML
    public void searchConge(){
      
        updateTable();
         try{
             dataList=ServiceBillet.getInstance().getAll();
          tableview.setItems(dataList);
          FilteredList<Billet> filtredData = new FilteredList<>(dataList, b -> true);
          TFFilter.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(person-> {
                 if(newValue == null|| newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                
                 if(String.valueOf(person.getId()).indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }
                
                     else
                     return false;
                 });
             });
         SortedList<Billet> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(tableview.comparatorProperty());
         tableview.setItems(sortedData);

         }catch(Exception e){
             System.out.println(e.getMessage());
             
         }          
          
          
     }
    private void esportexcel(ActionEvent event) throws SQLException {

        try {
            String filename = "C:\\Users\\DeLL\\Desktop\\GestionMatch\\src\\data.xls";
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("Id");
            rowhead.createCell((short) 1).setCellValue("type");
            rowhead.createCell((short) 2).setCellValue("prix");
            

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `billetm`");
            int i = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);

                row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("id")));
                row.createCell((short) 1).setCellValue(rs.getString("type"));
                row.createCell((short) 2).setCellValue(rs.getString("prix"));
                

                i++;
            }
            FileOutputStream fileOut = new FileOutputStream(filename);
            hwb.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");
            File file = new File(filename);
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);

        }
    }
    
    
     @FXML
    public void trie() {
        Comparator<Billet> comparator = null;
        if (triBox.getValue() == "Id") {
            comparator = Comparator.comparingInt(Billet::getId);

        } else if (triBox.getValue() == "type") {
            comparator = Comparator.comparing(Billet::getType);

        }

        FXCollections.sort(obl, comparator);
        tableview.setItems(obl);
        updateTable();

    }
    
}
