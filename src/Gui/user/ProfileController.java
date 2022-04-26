/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.user;

import Entities.User;
import Gui.SessionManager;
import Services.UserService;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Khairi
 */
public class ProfileController implements Initializable {

    @FXML
    private Text username;
    @FXML
    private Text nom;
    @FXML
    private Text prenom;
    @FXML
    private Text email;
    @FXML
    private StackPane container;
    @FXML
    private JFXDialog dialog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SetInfos();
    }    

    @FXML
    private void Login(MouseEvent event) {
    }

    @FXML
    private void EditProfile(ActionEvent event) throws IOException {
        
        UserService us = new UserService();
        User user = us.RecupererUser(SessionManager.get().getUsername());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditUser.fxml"));
        AnchorPane ap = loader.load();
        EditUserController controller = loader.getController();
        controller.SetUserInfos(user, dialog , container, true);
        controller.getCloseButton().setOnAction(actionEvent -> {
            dialog.close();
        });

        dialog.getChildren().add(ap);

        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.setDialogContainer(container);

        dialog.show();

    }
    
    public void SetInfos()
    {
        username.setText(SessionManager.get().getUsername());
        nom.setText(SessionManager.get().getNom());
        prenom.setText(SessionManager.get().getPrenom());
        email.setText(SessionManager.get().getEmail());
    }
   
    
}
