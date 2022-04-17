/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Packard bell
 */
public class Maconnexion {
    private final String URL="jdbc:mysql://localhost/volcanofootball1";
    private final String LOGIN="root";
    private final String PASSWORD="";
    
    private static Maconnexion instance;
    Connection cnx;
    public Maconnexion(){
        try{
            cnx=DriverManager.getConnection(URL,LOGIN,PASSWORD);
            System.out.println("la connexion est établie");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public Connection getCnx(){
        return this.cnx;
    }
    public static Maconnexion getInstance(){
        if(instance==null)
            instance =new Maconnexion();
        
        return instance;
        
    }
}
