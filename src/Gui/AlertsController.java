/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import javafx.scene.control.Alert;

/**
 *
 * @author Khairi
 */
public class AlertsController {
    
    private static final AlertsController instance = new AlertsController();
    
    public static AlertsController get(){
        return instance;
    }
    
    public void Alert(String type, String title, String content){
        
        Alert alert;
        
        if(type.equals("information")) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        else{
            alert = new Alert(Alert.AlertType.WARNING);
        }
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    
}
