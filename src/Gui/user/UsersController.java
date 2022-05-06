/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.user;

import Entities.Produit;
import Entities.User;
import Gui.DashboardController;
import Gui.produit.ProduitItemController;
import Services.ProduitService;
import Services.UserService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ScrollPane;
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
public class UsersController implements Initializable {

    @FXML
    private ScrollPane ScrollPane;
    @FXML
    private FlowPane FlowPane;
    @FXML
    private JFXDialog dialog;
    @FXML
    private StackPane container;
    @FXML
    private Text nbrUsers;
    @FXML
    private Text nbrAdmins;
    @FXML
    private Text nbrClients;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListUsers();
        Affichage();
    }

    public void ListUsers() {
        
        UserService us = new UserService();
        List<User> list = us.AfficherUsers();
        try {
            for (int i = 0; i < list.size(); i++) {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserItem.fxml"));
                Pane pane = loader.load();
                UserItemController controller = loader.getController();
                controller.ItemInfos(list.get(i), FlowPane, dialog , container);

                FlowPane.getChildren().add(pane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ShowAddUser(ActionEvent event) {
        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUser.fxml"));
            AnchorPane ap = loader.load();
            AddUserController controller = loader.getController();
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
    
    public void Affichage()
    {
        int admins=0;
        int clients=0;
        int users=0;
        
        UserService us = new UserService();
        List<User> list = us.AfficherUsers();
        for (int i = 0; i < list.size(); i++) {
            users++;
            if(list.get(i).getRole().equals("Admin"))
            {
                admins++;
            }
            else
            {
                clients++;
            }
        }
        nbrClients.setText(String.valueOf(clients));
        nbrAdmins.setText(String.valueOf(admins));
        nbrUsers.setText(String.valueOf(users));
        System.out.println("admins: "+admins);
        System.out.println("clients: "+clients);
    }
        
    
    

 
    
}
