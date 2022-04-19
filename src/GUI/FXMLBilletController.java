/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Billet;
import entities.Match;





import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.ServiceBillet;

import service.ServiceMatch;

/**
 * FXML Controller class
 *
 * @author DeLL
 */
public class FXMLBilletController implements Initializable {

    @FXML
    private TextField TFId;
    @FXML
    private Button btn_quitter;
    @FXML
    private TextField TFType;
    @FXML
    private TextField TFPrix;
    @FXML
    private TableView<Billet> tableview;
    @FXML
    private TableColumn<Billet, Integer> col_id;
    @FXML
    private TableColumn<Billet, String> col_type;
    @FXML
    private TableColumn<Billet, String> col_prix;
    @FXML
    private TextField TFFilter;
    
 ObservableList<Billet> listM;
    ObservableList<Billet> dataList;
    int index=-1;
    Connection cnx=null;
    ResultSet rs=null;
    PreparedStatement pst =null;
    
    
    
    @FXML
    private TextField TFid;
   
    
 
  
      @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
         index =tableview.getSelectionModel().getSelectedIndex();
   if(index<= -1){
    return ;
    }
   TFId.setText(col_id.getCellData(index).toString());
   TFType.setText(col_type.getCellData(index).toString());
   TFPrix.setText(col_prix.getCellData(index).toString());
   
    }
    
    public void updateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Billet,Integer>("id"));
        col_type.setCellValueFactory(new PropertyValueFactory<Billet,String>("type"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Billet,String>("prix"));
        
        
         try {
             listM=ServiceBillet.getInstance().getAll();
             tableview.setItems(listM);
         } catch (SQLException ex) {
             Logger.getLogger(FXMLBilletController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        updateTable();
       
    }
    
    @FXML
    void add(ActionEvent event) {
        ServiceBillet sc =new ServiceBillet();
        Billet C =new Billet();
        C.setType(TFType.getText());
        C.setPrix(TFPrix.getText());
        
         if(TFType.getText().length()==0)
        
        { TFType.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
        new animatefx.animation.Shake(TFType).play();}
        else 
            TFType.setStyle(null);
          if(TFPrix.getText().length()==0)
        
        { TFPrix.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
        new animatefx.animation.Shake(TFPrix).play();}
        else 
            TFPrix.setStyle(null);
          if (TFType.getText().isEmpty() || TFPrix.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
          }else {
          try{
            sc.add(C);
            updateTable();
             
             JOptionPane.showMessageDialog(null, "Billet a ete ajouter avec succes");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        TFType.setText("");
        TFPrix.setText(" ");
      


           }
            
    }

    @FXML
    private void Edit(ActionEvent event) {
        ServiceBillet SC =new ServiceBillet();
        Billet C =new Billet();
        C.setType(TFType.getText());
        C.setPrix(TFPrix.getText());
        
         
        C.setId(Integer.parseInt(TFId.getText()));
         if(TFType.getText().length()==0)
        
        { TFType.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
        new animatefx.animation.Shake(TFType).play();}
        else 
            TFType.setStyle(null);
          if(TFPrix.getText().length()==0)
        
        { TFPrix.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
        new animatefx.animation.Shake(TFPrix).play();}
        else 
            TFPrix.setStyle(null);
          if (TFType.getText().isEmpty() || TFPrix.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
          }else {
                
        try{
          
          SC.updateBillet(C);
          updateTable();
       
        JOptionPane.showMessageDialog(null, "billet à été modifier avec succes");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        
        }
        TFType.setText("");
        TFPrix.setText(" ");
          }
    }
    
    @FXML
    private void Delete(ActionEvent event) {

        try{
        ServiceBillet SC =new ServiceBillet();
        Match C =new Match();
        
          SC.delete(TFId.getText());
            JOptionPane.showMessageDialog(null,"Billet  a été supprimer avec succes");
            updateTable();
             
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
      
        }
      TFType.setText("");
        TFPrix.setText(" ");
        
    }

    @FXML
    private void Quitter(ActionEvent event) throws Exception {
       try{
            btn_quitter.getScene().getWindow().hide();
                Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXMLMenu.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
               
                mainStage.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            
        }
    }
}
