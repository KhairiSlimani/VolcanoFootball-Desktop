/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.reservation;

import Entities.Agence;
import Entities.Hebergement;
import Entities.Reservation;
import Gui.agence.AgenceController;
import static Gui.print.printNode;
import Gui.produit.AddProduitController;
import Services.AgenceCrud;
import Services.HebergementCrud;
import Services.ReservationCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author HP USER
 */
public class ReservationController implements Initializable {

    @FXML
    private FlowPane FlowPane;
    @FXML
    private TableColumn<Reservation, Integer> colid;
    @FXML
    private TableColumn<Reservation, String> coldatedebut;
    @FXML
    private TableColumn<Reservation, String> coldatefin;
    @FXML
    private TableColumn<Reservation, Integer> colheb;
    @FXML
    private StackPane container;
    @FXML
    private JFXDialog dialog;
    @FXML
    private TableView<Reservation> tvheb;
    ObservableList list;
        Reservation reservation;
    @FXML
    private JFXButton print;
     private FlowPane flowPane;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     Show();
    }    
        public void Show() {
        // TODO
        try {
          
            ReservationCrud ps = new ReservationCrud();
                        HebergementCrud hb = new HebergementCrud();

            List<Reservation> res = ps.afficher();
            list = FXCollections.observableArrayList(res);
            tvheb.setItems(list);

            colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            coldatedebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            coldatefin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
       //     Hebergement h = hb.RecupererProduit(h.getProduit());

      //  user.setText(u.getUsername());
            colheb.setCellValueFactory(new PropertyValueFactory<>("hebergement"));

    
           
      

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }    

    @FXML
    private void ShowAddReservation(ActionEvent event) {
          try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddReservation.fxml"));
            AnchorPane ap = loader.load();
            AddReservationController controller = loader.getController();
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
        @FXML

     private void supprimer(ActionEvent event) throws SQLException {
            ReservationCrud ps = new ReservationCrud();
        Reservation u= tvheb.getSelectionModel().getSelectedItem();
         
        
                ps.supprimerReservation(u.getId());
    
          Show();
  }

    @FXML
    private void print(ActionEvent event) {
          try {
            printNode(tvheb);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(AgenceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AgenceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AgenceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(AgenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    private void EditR(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("EditReservation.fxml"));
        AnchorPane ap = loader.load();
        EditReservationController controller = loader.getController();
        controller.getCloseButton().setOnAction(actionEvent -> {
            dialog.close();
        });

        dialog.getChildren().add(ap);

        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.setDialogContainer(container);

        dialog.show();
    }
    
    }
  
    

