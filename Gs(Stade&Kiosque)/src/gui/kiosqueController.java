/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class kiosqueController implements Initializable {


    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<kiosque, Integer> colId;

    @FXML
    private TableColumn<kiosque, String> colNom;

    @FXML
    private TableColumn<kiosque, Integer> colStade_id;

    @FXML
    private TableColumn<kiosque, String> colType;

    @FXML
    private TextField filterField;

    @FXML
    private ImageView logo;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfStade_id;

    @FXML
    private TextField tfType;

    @FXML
    private TableView<kiosque> tvKiosque;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    /**
     * Initializes the controller class.
     */
 @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {        
        
     
        if(event.getSource() == btnDelete){
            deleteButton(event);
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
        
        
        colNom.setCellValueFactory(new PropertyValueFactory<kiosque, String>("nom"));
        colType.setCellValueFactory(new PropertyValueFactory<kiosque, String>("type"));
        colId.setCellValueFactory(new PropertyValueFactory<kiosque, Integer>("id"));

        
        tvKiosque.setItems(list);
    }
             
  
             
   
          private void deleteButton(ActionEvent event){
        String query = "DELETE FROM kiosque WHERE id =" + tfId.getText() + "";
        executeQuery(query);
        showkiosque();
    }
             
      
    
          private void UpdateButton(ActionEvent event){
      
        String query = "UPDATE  kiosque SET nom  = '" + tfNom.getText() + "', type = '" + tfType.getText() + "' WHERE id = '" + tfId.getText() + "'";
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
     /*   @FXML
    void search() {          
        colId.setCellValueFactory(new PropertyValueFactory<Voyage,Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Voyage,String>("nom"));
        colDestination.setCellValueFactory(new PropertyValueFactory<Voyage,String>("destination"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Voyage,String>("description"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Voyage,Integer>("prix"));
       
        Connection conn = getConnection();
        tvVoyage.setItems(list);
        //table_users.setItems(dataList);
        FilteredList<Voyage> filteredData = new FilteredList<>(dataList, b -> true);  
 filterField.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (person.getDestination().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (person.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (String.valueOf(person.getPrix()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<voyage> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tvVoyage.comparatorProperty());  
  tvVoyage.setItems(sortedData);      
    }*/
            @FXML
    private void handleMouseClicked(MouseEvent event) {
        kiosque kiosque = tvKiosque.getSelectionModel().getSelectedItem();
        tfId.setText("" +kiosque.getId());
        tfNom.setText("" +kiosque.getNom());
        tfStade_id.setText("" +kiosque.getStade_id());
        tfType.setText("" +kiosque.getType());
       
    }
    @FXML
    private void stat(ActionEvent event)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PieChartkiosque.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void handleButtonAction2(ActionEvent event)  throws IOException {
        
        if(event.getSource() == btnUpdate){
            UpdateButton(event);
        }
    }

    
    private void InsertButton(ActionEvent event)  throws IOException {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("kiosqueFront.fxml"));
            Parent root = loader.load();
           kiosqueController controller = loader.getController();
         
         
          
          btnInsert.getScene().setRoot(root);
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void handleButtonAction1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kiosqueFront.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();
    }

    
 
}
