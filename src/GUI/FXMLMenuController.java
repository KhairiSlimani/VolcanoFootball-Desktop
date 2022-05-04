/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Packard bell
 */
public class FXMLMenuController implements Initializable {

    private Button btn_Conge;
    @FXML
    private Button btn_Employe;
    @FXML
    private Button btn_quitter;
    
    @FXML
    private Button btn_match;
    @FXML
    private Button btn_billet;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
   
    @FXML
    void interfaceMatch(ActionEvent event)throws Exception {
         try{
             btn_match.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXMLMatch.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                 mainStage.setTitle("Interfce Match");
                mainStage.show();
                //JOptionPane.showMessageDialog(null, "Bienvenue dans votre interface des matchs");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void Quitter(ActionEvent event) {
     Stage stage = (Stage) btn_quitter.getScene().getWindow();
    stage.close();
    }

    @FXML
    private void interfaceBillet(ActionEvent event) {
        try{
             btn_billet.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXMLBillet.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                 mainStage.setTitle("Interfce Billet");
                mainStage.show();
                //JOptionPane.showMessageDialog(null, "Bienvenue dans votre interface des billets");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    
 
 
    
}
