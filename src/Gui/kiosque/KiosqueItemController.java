/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.kiosque;



import Entities.kiosque;
import Entities.stade;
import Gui.SessionManager;
import Gui.produit.EditProduitController;
import Services.CommandeService;
import Services.KiosqueService;
import Services.ProduitService;
import Services.StadeService;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Khairi
 */
public class KiosqueItemController implements Initializable {

    @FXML
    private Text nom;
   
    
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    
    kiosque kiosque;
    public int id;
    @FXML
    private Pane item;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton editButton;
    private Text adresseLabel;
    @FXML
    private Text stade;
    @FXML
    private Text type;
    @FXML
    private Text nomLabel;
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        if(SessionManager.get().getRole().equals("Client"))
        {
            editButton.setVisible(false);
            deleteButton.setVisible(false);
        }
        

    }    

   
    
    public void ItemInfos(kiosque cm, FlowPane fp, JFXDialog d, StackPane c){
        
        dialog = d;
        container = c;
        flowPane=fp;
        kiosque = cm;
        id = cm.getId();
        nom.setText(cm.getNom());
        type.setText(String.valueOf(cm.getType()));
        
        
        
       
            
        StadeService ps = new StadeService();
        stade s = ps.RecupererStade(cm.getStade_id());

        
        stade.setText(s.getNom());
    }
    
    public void ItemInfos(int stade_id, int q, FlowPane fp){
        StadeService ps = new StadeService();
        stade p = ps.RecupererStade(stade_id);
        stade.setText(p.getNom());
       
       
        
        nom.setVisible(false);
        adresseLabel.setVisible(false);
        deleteButton.setVisible(false);
        editButton.setVisible(false);
    }

    @FXML
    private void DeleteKiosque(ActionEvent event) {
          KiosqueService us = new KiosqueService();
        boolean test = us.SupprimerKiosque(id);
        if(test)
        {
            flowPane.getChildren().remove(item);
        }
    }

    @FXML
    private void EditKiosque(ActionEvent event) throws IOException {
        KiosqueService cs = new KiosqueService();
        kiosque kiosque = cs.RecupererKiosque(id);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditKiosque.fxml"));
        AnchorPane ap = loader.load();
        EditKiosqueController controller = loader.getController();
        controller.setInfos(kiosque, flowPane, dialog , container);
        controller.getCloseButton().setOnAction(actionEvent -> {
            dialog.close();
        });

        dialog.getChildren().add(ap);

        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.setDialogContainer(container);

        dialog.show();
    }


    
}
