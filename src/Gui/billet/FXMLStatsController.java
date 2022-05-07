/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.billet;






import Entities.Billet;
import java.net.URL;
import java.sql.Connection;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Zakou
 */
public class FXMLStatsController implements Initializable {

    @FXML
    private PieChart piechart;
    private Statement st;
    private ResultSet rs;
    private Connection cnx;
    
    
    
    
    
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    @FXML
    private Button btn_quitter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cnx= MyDB.getInstance().getCon();
        stat();
    }    
     private void stat()
    {
          
           
    
           
        try {
            String query = "SELECT COUNT(*),type FROM `billetm` GROUP BY id" ;
            
            PreparedStatement PreparedStatement = cnx.prepareStatement(query);
            rs = PreparedStatement.executeQuery();
            
            
            while (rs.next()){
                data.add(new PieChart.Data(rs.getString("type"),rs.getInt("COUNT(*)")));
            }
            
            
            piechart.setTitle("*Statistiques des types des billets*");
            piechart.setLegendSide(Side.LEFT);
            piechart.setData(data);
        } catch (SQLException ex) {
               Logger.getLogger(FXMLStatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void Quitter(ActionEvent event)throws Exception {
         try {
            btn_quitter.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("billet/Billet.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);

            mainStage.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }
    
}

