/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volcanofootballdesktop;

import Services.ReservationCrud;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Khairi
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("../Gui/user/login.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();    
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        /*
        Produit p1 = new Produit("produit test 1","lunette","XL","Noir",5,"desc",10,"photo");
        Produit p2 = new Produit("produit test 2","jackette","M","Blue",5,"desc",23,"photo");
        ProduitService ps = new ProduitService();

        ps.AjouterProduit(p1);
        ps.AjouterProduit(p2);
        ps.SupprimerProduit(13);
        System.out.println(ps.AfficherProduits().toString());
        
        
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

     
                ReservationCrud ps = new ReservationCrud();
                ps.afficher();
   */
        launch(args);
    }
    
}
