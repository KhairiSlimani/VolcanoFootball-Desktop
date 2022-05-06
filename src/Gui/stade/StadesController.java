/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.stade;


import Entities.stade;
import Gui.SessionManager;
import Gui.user.AddUserController;

import Services.StadeService;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Khairi
 */
public class StadesController implements Initializable {

    @FXML
    private StackPane container;
    @FXML
    private JFXDialog dialog;
    @FXML
    private FlowPane FlowPane;
    @FXML
    private JFXButton addPB;
    
    private Text cartSize;
    @FXML
    private JFXButton showP;
   
    @FXML
    private Text nbrStades;
    @FXML
    private JFXButton imprimer;
    @FXML
    private TextField filterField;
    @FXML
    private AnchorPane affich;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ListProduits();
        
        if(SessionManager.get().getRole().equals("Client"))
        {
            addPB.setVisible(false);
        } 
       
        Affichage();
        ListStades();

        

    }    
    
    public void ListStades() {
        
        StadeService us = new StadeService();
        List<stade> list = us.AfficherStade();
        
        
        try {
            for (int i = 0; i < list.size(); i++) {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StadeItem.fxml"));
                Pane pane = loader.load();
                StadeItemController controller = loader.getController();
                
                controller.ItemInfos(list.get(i), FlowPane, dialog , container, cartSize);

                FlowPane.getChildren().add(pane);
                
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ShowAddStade(ActionEvent event) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddStade.fxml"));
            AnchorPane ap = loader.load();
            AddStadeController controller = loader.getController();
            controller.setInfos(FlowPane, dialog , container);
            controller.getCloseButton().setOnAction(ActionEvent -> {
                dialog.close();
            });

            dialog.getChildren().add(ap);
            dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
            dialog.setDialogContainer(container);

            dialog.show();  
           
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    
    public void CartSize(Text t) {
        cartSize=t;
    }

   
    
    public void Affichage()
    {
        int stades=0;
        
        StadeService ps = new StadeService();
        List<stade> list = ps.AfficherStade();
        for (int i = 0; i < list.size(); i++) {
            stades++;
        }
        nbrStades.setText(String.valueOf(stades));
        System.out.println("stades: "+stades);
        

    }

    @FXML
    private void ShowStades(ActionEvent event) {
        ListStades();
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PieChartStade.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();
    } 

    @FXML
    private void Print(ActionEvent event) {
         PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.cartSize;
       
       
     if(job != null){
     job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
     Printer printer = job.getPrinter();
     PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
     boolean success = job.printPage(pageLayout, root);
     if(success){
        job.endJob();
    }
     }}}
