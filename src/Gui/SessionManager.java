/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


/**
 *
 * @author Khairi
 */
public class SessionManager {
        
    private int id;
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String email;
    private String role;
    private boolean session;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isSession() {
        return session;
    }

    public void setSession(boolean session) {
        this.session = session;
    }
    
    public void StartSession (int id, String username, String password, String nom, String prenom, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
        setSession(true);
    }
    
    public void EndSession() {
        setSession(false);
    }
    
    private static final SessionManager instance = new SessionManager();
    public static SessionManager get(){
        return instance;
    }





    
}
