/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entités.stade;
import Intservice.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Database;

/**
 *
 * @author ASUS
 */
public class ServiceStade implements IService<stade>{
 Connection cnx ;
    public ServiceStade() {
         cnx = Database.getInstance().getConn();
        
    }

    
    
    
    @Override
    public void add(stade v) throws SQLException{
        
        Statement st = cnx.createStatement();
          String req =" insert into voyage (id, nom , adresse, dateouverture, capacite) values (" +v.getId()+ ", '"+v.getNom()+" ' , '"+v.getAdresse() +" ' , '"+v.getDateouverture() + " ' , '"+v.getCapacite()+ "')"; 
    st.executeUpdate(req);



    }

    @Override
    public List<stade> read() throws SQLException{
    List<stade> ls = new ArrayList<stade>();
    Statement st = cnx.createStatement();
    String req = "select * from stade";
    ResultSet rs = st.executeQuery(req);
     
    while(rs.next()){
        int id = rs.getInt("id");
        String nom = rs.getString(2);
        String adresse = rs.getString(3);
        String dateouverture = rs.getString(4);
        int capacite = rs.getInt(5);
        
        stade v = new stade(id, nom, adresse, dateouverture, capacite);
        ls.add(v);
    }
    
    return ls;

    }

   @Override
    public void update(stade v) throws SQLException {
        PreparedStatement pt = cnx.prepareStatement("update stade set nom = ? , adresse = ? , dateouverture = ? , capacite = ? where id = ? ");
        pt.setString(1, v.getNom());
        pt.setString(2, v.getAdresse());
        pt.setString(3, v.getDateouverture());
        pt.setInt(4, v.getCapacite());
        pt.setInt(5, v.getId());
        pt.executeUpdate(); 


    }
      /*         @Override
    public void update(Voyage  v) {
        try {
            String req = "UPDATE voyage SET nom='" + v.getNom() + "'destination'" + v.getDestination() +  "'description'" + v.getDescription() +  "'prix'" + v.getPrix() +"' WHERE id=" + v.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            //System.out.println("Personne modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    */
    
    /*@Override
    public void delete(Voyage v) {
        try {
            String req2 = "DELETE FROM voyage where nom=" + v.getNom();
            Statement st = cnx.createStatement();
            st.executeUpdate(req2);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
*/

  /*     @Override
    public void update(Voyage v) throws SQLException {
        
        Statement st = cnx.createStatement();
          String req =" update voyage set (id, nom , destination, description, prix) (" +v.getId()+ ", '"+v.getNom()+" ' , '"+v.getDestination() +" ' , '"+v.getDescription() + " ' , '"+v.getPrix()+ "')"; 
    st.executeUpdate(req);



    }*/
   @Override
    public void delete(stade v) throws SQLException {
        PreparedStatement pt = cnx.prepareStatement("delete from stade where id = ?");
        pt.setInt(1, v.getId());
        pt.executeUpdate();    } 
    
}
