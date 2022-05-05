/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.stade;

import Entities.Favori;


import Entities.stade;
import Gui.AlertsController;

import Gui.SessionManager;

import Services.FavoriService;
import Services.StadeService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.TextField;
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
public class StadeItemController implements Initializable {

    @FXML
    private Text nom;
    
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
    stade stade;
    public int id;
    @FXML
    private Pane item;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton editButton;

    public Text cartSize;
    @FXML
    private Text adresse;
    @FXML
    private Text capacite;
    @FXML
    private Text date;
    @FXML
    private JFXButton PrintButton;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(SessionManager.get().getRole().equals("Client"))
        {
            editButton.setVisible(false);
            deleteButton.setVisible(false);
        }
        
    }    

    @FXML
    private void DeleteStade(ActionEvent event) {
        
        StadeService us = new StadeService();
        boolean test = us.SupprimerStade(id);
        if(test)
        {
            flowPane.getChildren().remove(item);
        }

    }

   
    
    public void ItemInfos(stade s, FlowPane fp, JFXDialog d, StackPane c, Text t){
        dialog = d;
        container = c;
        flowPane=fp;
        stade=s;
        id = s.getId();
        nom.setText(s.getNom());
        adresse.setText(s.getAdresse()); 
        date.setText(String.valueOf(s.getDateouverture()));
        capacite.setText(String.valueOf(s.getCapacite()));
        cartSize = t;
    }
    
    public void ItemInfos(stade s, FlowPane fp, JFXDialog d, StackPane c){
        dialog = d;
        container = c;
        flowPane=fp;
        stade=s;
        id = s.getId();
        nom.setText(s.getNom());
        adresse.setText(s.getAdresse());
        date.setText(String.valueOf(s.getDateouverture()));
        capacite.setText(String.valueOf(s.getCapacite()));
    }


    private void addFav(ActionEvent event) {
        Favori f = new Favori();
        f.setIdUser(SessionManager.get().getId());
        f.setId(id);
        FavoriService fs = new FavoriService();
        fs.AjouterFavori(f);
        AlertsController.get().Alert("information","Succès","Le stade a été ajouté à votre liste des favoris!");
    }

    private void AddToCart(ActionEvent event) {
        SessionManager.get().AddToCart(id,cartSize);
        
    }

    @FXML
    private void EditStade(ActionEvent event) throws IOException {
          StadeService cs = new StadeService();
        stade stade = cs.RecupererStade(id);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditStade.fxml"));
        AnchorPane ap = loader.load();
        EditStadeController controller = loader.getController();
        controller.setInfos(stade, flowPane, dialog , container);
        controller.getCloseButton().setOnAction(actionEvent -> {
            dialog.close();
        });

        dialog.getChildren().add(ap);

        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.setDialogContainer(container);

        dialog.show();
    }

    @FXML
    private void Print(ActionEvent event) {
          PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.item;
       
       
     if(job != null){
     job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
     Printer printer = job.getPrinter();
     PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
     boolean success = job.printPage(pageLayout, root);
     if(success){
        job.endJob();
    }
    }
    }
    
}
