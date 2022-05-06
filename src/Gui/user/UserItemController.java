/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.user;

import Entities.User;
import Services.UserService;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class UserItemController implements Initializable {

    @FXML
    private Text username;
    @FXML
    private Text nom;
    @FXML
    private Text prenom;
    @FXML
    private Text email;
    @FXML
    private Text role;
    
    public int id;
    
    User user;
    
    
    @FXML
    private Pane item;
    
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;
        

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void DeleteUser(ActionEvent event) {
        UserService us = new UserService();
        boolean test = us.SupprimerUser(id);
        if(test)
        {
            flowPane.getChildren().remove(item);
        }
        
    }
    
    public void ItemInfos(User u, FlowPane fp, JFXDialog d, StackPane c){
        dialog = d;
        container = c;
        flowPane=fp;
        user=u;
        id = u.getId();
        username.setText(u.getUsername());
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        email.setText(u.getEmail());
        role.setText(u.getRole());
    }

    @FXML
    private void EditUser(ActionEvent event) throws IOException {
        
        UserService us = new UserService();
        User user = us.RecupererUser(username.getText());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditUser.fxml"));
        AnchorPane ap = loader.load();
        EditUserController controller = loader.getController();
        controller.SetUserInfos(user, flowPane, dialog , container);
        controller.getCloseButton().setOnAction(actionEvent -> {
            dialog.close();
        });

        dialog.getChildren().add(ap);

        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.setDialogContainer(container);

        dialog.show();

    }
    

    
}
