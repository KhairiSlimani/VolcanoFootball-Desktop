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
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *  + "`email`= ? WHERE id = '"+studentId+"'";
 * @author ASUS
 */
public class ReservationFrontController implements Initializable {

    @FXML
    private DatePicker tfDate;
    @FXML
    private TextField tfId_resv;
    @FXML
    private TextField tfNbr;
    @FXML
    private TextField tfId;
    @FXML
    private Button btnInsert;

    // int studentId;
    /**
     * Initializes the controller class.
     */
   @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {        
        
        if(event.getSource() == btnInsert){
            insertRecord();
            ajoutersimple(event);
        }
            
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //showReservationV();
    }    
     public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exx", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
         public ObservableList<ReservationV> getReservationList(){
        ObservableList<ReservationV> reservationvList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM reservationv";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            ReservationV reservationv;
            while(rs.next()){
               // voyage = new Voyage(rs.getInt("id"), rs.getString("nom"), rs.getString("destination"), rs.getString("description"),rs.getInt("prix"));
                reservationv = new ReservationV(rs.getInt("idr"),rs.getInt("nb_personnes"), rs.getString("date"), rs.getInt("Id"));
                reservationvList.add(reservationv);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return reservationvList;
    }
 
        
          private void insertRecord(){
       // String query = "INSERT INTO voyage VALUES (" + tfId.getText() + ",'" + tfNom.getText() + "','" + tfDestination.getText() + "','" + tfDescription.getText() + "','" + tfPrix.getText() + "')";
       String query = "INSERT INTO ReservationV VALUES (" + tfId_resv.getText() + ",'" + tfNbr.getText() + "','" + tfDate.getValue() + "','" + tfId.getText() + "')";
       //String query = "INSERT INTO ReservationV VALUES (" + tfNbr.getText() + "','" + tfDate.getValue() + "','" + tfId.getText() + "')";
        executeQuery(query);
        //showReservationV();
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
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReservationVEnregistre.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();

    }
    
private void ajoutersimple(ActionEvent event) throws IOException 
    {
        if ( tfId_resv.getText().isEmpty() | tfNbr.getText().isEmpty() | tfId.getText().isEmpty() )
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
        }
 
         else if (tfDate.getValue().getYear() < 2022)
        {
            Alert al2 = new Alert(Alert.AlertType.ERROR);
            al2.setHeaderText(null);
            al2.setContentText("Veuillez choisir une date courante");
            al2.showAndWait();
        }

}
}
