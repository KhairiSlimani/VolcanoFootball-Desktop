/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entités.stade;
import Entités.kiosque;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *  + "`email`= ? WHERE id = '"+studentId+"'";
 * @author ASUS
 */
public class kiosqueFrontController implements Initializable {


    @FXML
    private Button btnInsert;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNom;
    @FXML
    private ChoiceBox<String> tfType;
    private String[] Type={"Tunisien", "Marocain" , "Brezilien"};
    
    @FXML
    private ComboBox<stade> tfStade;

    // int studentId;
    /**
     * Initializes the controller class.
     */
   @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {        
        
        if(event.getSource() == btnInsert){
            
            System.out.println("okk");
            insertRecord(event);
          //  ajoutersimple(event);
        }
            
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
              
        tfType.getItems().addAll(Type);
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
         public ObservableList<kiosque> getkiosqueList(){
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
                kiosque = new kiosque(rs.getInt("id"),rs.getString("nom"), rs.getString("type"), rs.getInt("stade_id"));
                kiosqueList.add(kiosque);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return kiosqueList;
    }
 
        
      private void insertRecord(ActionEvent event) throws IOException{
           System.out.println("okk2");
       // String query = "INSERT INTO voyage VALUES (" + tfId.getText() + ",'" + tfNom.getText() + "','" + tfDestination.getText() + "','" + tfDescription.getText() + "','" + tfPrix.getText() + "')";
       //String query = "INSERT INTO kiosque VALUES (" + tfNbr.getText() + "','" + tfDate.getValue() + "','" + tfId.getText() + "')";
       // executeQuery(query);
        //showkiosque();
        
           try{
           String query = "INSERT INTO kiosque (nom,type,stade_id)VALUES ('"+  tfNom.getText() + "','"  + tfType.getValue() +"',"+12+")";
            Connection conn = getConnection();
            PreparedStatement prst = conn.prepareStatement(query);
               prst.executeUpdate();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kiosque.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();
           
           }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        

      }
    
     /*     private void updateRecord(){
       // String query = "UPDATE  voyage SET nom  = '" + tfNom.getText() + "', destination = '" + tfDestination.getText() + "', description = '" + tfDescription.getText() + "', prix = '" + tfPrix.getText() + "' WHERE id = '" + tfId.getText() + "'";
        String query = "UPDATE  ReservationV SET nb_personnes  = '" + tfNbr.getText() + "', date = '" + tfDate.getValue() + "' WHERE idr = '" + tfId_resv.getText() + "'";
        executeQuery(query);
       // showReservationV();
    }
          private void deleteButton(){
        String query = "DELETE FROM ReservationV WHERE idr =" + tfId_resv.getText() + "";
        executeQuery(query);
        //showReservationV();
    }*/
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
    private void handleMouseClicked(MouseEvent event) throws Exception {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kiosqueEnregistre.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();

    }
    
private void ajoutersimple(ActionEvent event) throws IOException 
    {
        if ( tfNom.getText().isEmpty() | tfType.getValue().isEmpty()  )
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
        }

}
}
