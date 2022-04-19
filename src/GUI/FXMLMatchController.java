/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import service.ServiceMatch;







/**
 * FXML Controller class
 *
 * @author Packard bell
 */
public class FXMLMatchController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
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

   

    @FXML
    private TextField TFDate;

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

   
    ObservableList<Match> listM;
    ObservableList<Match> dataList;
    int index=-1;
    Connection cnx=null;
    ResultSet rs=null;
    PreparedStatement pst =null;
    
    
    private Button btn_quitter;
   
    
  @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
         index =tableview.getSelectionModel().getSelectedIndex();
   if(index<= -1){
    return ;
    }
   TFId.setText(col_id.getCellData(index).toString());
   TFNomMatch.setText(col_NomM.getCellData(index).toString());
   TFNomArbitre.setText(col_NomA.getCellData(index).toString());
   TFStade.setText(col_Stade.getCellData(index).toString());
   TFTour.setText(col_Tour.getCellData(index).toString());
   TFDate.setText(col_Date.getCellData(index).toString());
    }

  
    
    public void updateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Match,Integer>("id"));
        col_NomM.setCellValueFactory(new PropertyValueFactory<Match,String>("nom_match"));
        col_NomA.setCellValueFactory(new PropertyValueFactory<Match,String>("nom_arbitre"));
        col_Stade.setCellValueFactory(new PropertyValueFactory<Match,String>("stade"));
        col_Tour.setCellValueFactory(new PropertyValueFactory<Match,String>("tour"));
       
        col_Date.setCellValueFactory(new PropertyValueFactory<Match,String>("date"));
        
         try {
             listM=ServiceMatch.getInstance().getAll();
             tableview.setItems(listM);
         } catch (SQLException ex) {
             Logger.getLogger(FXMLMatchController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        updateTable();
       
    }
    
    @FXML
    void add(ActionEvent event) {
        ServiceMatch sc =new ServiceMatch();
        Match C =new Match();
        C.setNom_match(TFNomMatch.getText());
        C.setNom_arbitre(TFNomArbitre.getText());
        C.setStade(TFStade.getText());
        C.setTour(TFTour.getText());
        
        C.setDate(TFDate.getText());
        
         if(TFNomMatch.getText().length()==0)
        
        { TFNomMatch.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
        new animatefx.animation.Shake(TFNomMatch).play();}
        else 
            TFNomMatch.setStyle(null);
         
          if(TFNomArbitre.getText().length()==0)
        
        { TFNomArbitre.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
        new animatefx.animation.Shake(TFNomArbitre).play();}
        else 
            TFNomArbitre.setStyle(null);
           if(TFStade.getText().length()==0)
        
        { TFStade.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
        new animatefx.animation.Shake(TFStade).play();}
        else 
            TFStade.setStyle(null);
            if(TFTour.getText().length()==0)
        
        { TFTour.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
        new animatefx.animation.Shake(TFTour).play();}
        else 
            TFTour.setStyle(null);
             if(TFDate.getText().length()==0)
        
        { TFDate.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
        new animatefx.animation.Shake(TFDate).play();}
        else 
            TFDate.setStyle(null);
          
        if (TFNomMatch.getText().isEmpty() || TFNomArbitre.getText().isEmpty()|| TFStade.getText().isEmpty()|| TFTour.getText().isEmpty()|| TFDate.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
        
          try{
            sc.add(C);
            updateTable();
            
             
             JOptionPane.showMessageDialog(null, "match a ete ajouter avec succes");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        TFNomMatch.setText("");
        TFNomArbitre.setText(" ");
        TFStade.setText(" ");
        TFTour.setText("");
       
        TFDate.setText(" ");


        }
    
    }

    @FXML
    private void Edit(ActionEvent event) {
        ServiceMatch SC =new ServiceMatch();
        Match C =new Match();
        C.setNom_match(TFNomMatch.getText());
        C.setNom_arbitre(TFNomArbitre.getText());
        
        C.setStade(TFStade.getText());
        C.setTour(TFTour.getText());
       
        C.setDate(TFDate.getText());  
        C.setId(Integer.parseInt(TFId.getText()));
         if(TFNomMatch.getText().length()==0)
        
        { TFNomMatch.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
        new animatefx.animation.Shake(TFNomMatch).play();}
        else 
            TFNomMatch.setStyle(null);
         
          if(TFNomArbitre.getText().length()==0)
        
        { TFNomArbitre.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
        new animatefx.animation.Shake(TFNomArbitre).play();}
        else 
            TFNomArbitre.setStyle(null);
           if(TFStade.getText().length()==0)
        
        { TFStade.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
        new animatefx.animation.Shake(TFStade).play();}
        else 
            TFStade.setStyle(null);
            if(TFTour.getText().length()==0)
        
        { TFTour.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
        new animatefx.animation.Shake(TFTour).play();}
        else 
            TFTour.setStyle(null);
             if(TFDate.getText().length()==0)
        
        { TFDate.setStyle("-fx-border-color: red ; -fx-border-widt: 2px ;");
        new animatefx.animation.Shake(TFDate).play();}
        else 
            TFDate.setStyle(null);
          
        if (TFNomMatch.getText().isEmpty() || TFNomArbitre.getText().isEmpty()|| TFStade.getText().isEmpty()|| TFTour.getText().isEmpty()|| TFDate.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
                
        try{
          
          SC.updateMatch(C);
          updateTable();
       
        JOptionPane.showMessageDialog(null, "L'employé à été modifier avec succes");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        
        }
        TFNomMatch.setText("");
        TFNomArbitre.setText(" ");
        TFStade.setText(" ");
        TFTour.setText("");
     
        TFDate.setText(" ");
        }
    }
    
    @FXML
    private void Delete(ActionEvent event) {

        try{
        ServiceMatch SC =new ServiceMatch();
        Match C =new Match();
        
          SC.delete(TFId.getText());
            JOptionPane.showMessageDialog(null,"l'match  a été supprimer avec succes");
            updateTable();
             
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
      
        }
        TFNomMatch.setText("");
        TFNomArbitre.setText(" ");
        TFStade.setText(" ");
        TFTour.setText("");
       
        TFDate.setText(" ");
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
