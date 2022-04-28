/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class kiosqueEnregistreController implements Initializable {


    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<kiosque, Integer> colId;

    @FXML
    private TableColumn<kiosque, String> colNom;

    @FXML
    private TableColumn<kiosque, Integer> colStade_id;

    @FXML
    private TableColumn<kiosque, String> colType;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfStade_id;

    @FXML
    private TextField tfType;

    @FXML
    private TableView<kiosque> tvkiosque;


    /**
     * Initializes the controller class.
     */
@FXML
    private void handleButtonAction(ActionEvent event) {        
        
       
         if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }
            
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showkiosque();
    }    
     public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vfootball", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
         public ObservableList<kiosque> getReservationList(){
        ObservableList<kiosque> kiosqueList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM kiosque";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            kiosque kiosque;
            while(rs.next()){
               // voyage = new Voyage(rs.getInt("id"), rs.getString("nom"), rs.getString("destination"), rs.getString("description"),rs.getInt("prix"));
                kiosque = new kiosque(rs.getInt("id"), rs.getString("nom"), rs.getString("type"), rs.getInt("stade_id"));
                kiosqueList.add(kiosque);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return kiosqueList;
    }
             public void showkiosque(){
        ObservableList<kiosque> list = getReservationList();
        
        colId.setCellValueFactory(new PropertyValueFactory<kiosque, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<kiosque, String>("nom"));
        colType.setCellValueFactory(new PropertyValueFactory<kiosque, String>("type"));
        colStade_id.setCellValueFactory(new PropertyValueFactory<kiosque, Integer>("stade_id"));

        
        tvkiosque.setItems(list);
    }
             
    
             
  
          private void updateRecord(){
       // String query = "UPDATE  voyage SET nom  = '" + tfNom.getText() + "', destination = '" + tfDestination.getText() + "', description = '" + tfDescription.getText() + "', prix = '" + tfPrix.getText() + "' WHERE id = '" + tfId.getText() + "'";
        String query = "UPDATE  kiosque SET nom  = '" + tfNom.getText() + "', type = '" + tfType.getText() + "' WHERE id = '" + tfId.getText() + "'";
        executeQuery(query);
        showkiosque();
    }
          private void deleteButton(){
        String query = "DELETE FROM kiosque WHERE id =" + tfId.getText() + "";
        executeQuery(query);
        showkiosque();
    }
        private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
            @FXML
    private void handleMouseClicked(MouseEvent event) {
        kiosque kiosque = tvkiosque.getSelectionModel().getSelectedItem();
        tfId.setText(""+kiosque.getId());
        tfNom.setText("" +kiosque.getNom());
        tfType.setText("" +kiosque.getType());
        tfStade_id.setText(""+kiosque.getStade_id());
    }

}

