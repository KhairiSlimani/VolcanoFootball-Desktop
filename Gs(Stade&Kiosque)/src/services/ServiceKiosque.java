/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entités.kiosque;
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
public class ServiceKiosque implements IService<kiosque>{
 Connection cnx ;
    private int executeUpdate;
    public ServiceKiosque() {
         cnx = Database.getInstance().getConn();
        
    }

    
    
    
    public void addk(kiosque r) throws SQLException{
        
        Statement st = cnx.createStatement();
          String req =" insert into kiosque (id, nom , type,stade_id) values (" +r.getId()+ ", '"+r.getNom()+" ' , '"+r.getType()+" ' , '"+r.getStade_id ()+ "')"; 
    st.executeUpdate(req);



    }

    @Override
    public List<kiosque> read() throws SQLException{
    List<kiosque> ls = new ArrayList<kiosque>();
    Statement st = cnx.createStatement();
    String req = "select * from kiosque";
    ResultSet rs = st.executeQuery(req);
     
    while(rs.next()){
        int id = rs.getInt("id");
        String nom = rs.getString(2);
        String type = rs.getString(3);
        int stade_id = rs.getInt(4);

        
        kiosque r = new kiosque(id, nom, type,stade_id);
        ls.add(r);
    }
    
    return ls;

    }

 /* @Override
    public void update(ReservationV r) throws SQLException {
        PreparedStatement pt = cnx.prepareStatement("update reservationv set date = ? where id = ? ");
        pt.setString(1, "20/5/2022");
        pt.setInt(2, r.getId());
        pt.executeUpdate(); 


    }*/
       @Override
    public void update(kiosque r) throws SQLException {
        PreparedStatement pt = cnx.prepareStatement("update kiosque set nb_personnes = ? , date = ? where id = ? ");
        
        pt.setString(1, r.getNom());
        pt.setString(2, r.getType());
        pt.setInt(3, r.getId());
        pt.executeUpdate(); 


    }


  @Override
    public void delete(kiosque r) throws SQLException {
        PreparedStatement pt = cnx.prepareStatement("delete from kiosque where id = ?");
        pt.setInt(1, r.getId());
        pt.executeUpdate();    } 
    
     /*  @Override
    public void delete(ReservationV r) {
        try {
            String req = "DELETE FROM voyage where id =" + r.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            //System.out.println("Evenement supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/

    @Override
    public void add(kiosque t) throws SQLException {
         Statement st = cnx.createStatement();
          String req =" insert into kiosque ( nom , type) values (" +t.getId()+ ", '"+t.getNom()+" ' , '"+t.getType()+ "')"; 
     executeUpdate = st.executeUpdate(req);
    }

   

   
}
