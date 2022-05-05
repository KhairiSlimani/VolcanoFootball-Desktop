/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.favori;

import Entities.Favori;
import Entities.Produit;
import Gui.SessionManager;
//import Gui.produit.ProduitItemController;
import Services.FavoriService;
import Services.ProduitService;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Khairi
 */
public class FavorisController implements Initializable {

    @FXML
    private FlowPane FlowPane;
    @FXML
    private StackPane container;
    @FXML
    private JFXDialog dialog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListFavoris();
    }   
    
    public void ListFavoris() {
        
        FavoriService fs = new FavoriService();
        List<Favori> list;
        if(SessionManager.get().getRole().equals("Admin"))
        {
            list = fs.AfficherFavoris();
        }
        else
        {
            list = fs.AfficherFavoris(SessionManager.get().getId());
        }
        try {
            for (int i = 0; i < list.size(); i++) {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FavoriItem.fxml"));
                Pane pane = loader.load();
                FavoriItemController controller = loader.getController();
                controller.ItemInfos(list.get(i), FlowPane, dialog , container);

                FlowPane.getChildren().add(pane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
