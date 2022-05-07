/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Agence;
import Entities.Hebergement;
import IServices.IServiceHebergement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Utils.MyDB;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP USER
 */
public class HebergementCrud implements IServiceHebergement {
     Connection con;
  public HebergementCrud() {
        con = MyDB.getInstance().getCon();
    }
    

   public void ajouterHebergement()throws SQLException {
        String insert = "INSERT INTO Hebergement (nomH,type)"+" VALUES ('travel','Hotel')";
               try{

         Statement st = con.createStatement();
         st.executeUpdate(insert);
System.out.println("Hebergement ajouté avec succés");
  }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
}
     @Override
   public boolean ajouterHebergement2(Hebergement h)  {
              try{

     String insert2 = "INSERT INTO Hebergement (nomH,type,adresse,agence_id)"+" VALUES (?,?,?,?)";
    PreparedStatement prst = con.prepareStatement(insert2);
        
        prst.setString(1, h.getNomH());
        prst.setString(2, h.getType());
        prst.setString(3, h.getAdresse());

        prst.setInt(4, h.getAgence_id());


        prst.executeUpdate();
         System.out.println("Hebergement Ajouté");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(AgenceCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'ajout du Hebergement");
            return false;
        }

}
    @Override
   public List<Hebergement> afficherHebergement()  {
      List<Hebergement>myList=  new ArrayList<>();;
        try {

         String select="SELECT * FROM `hebergement`";
         Statement st =con.createStatement();
         ResultSet rs =    st.executeQuery(select);

          while(rs.next()){
                    {
                Hebergement h = new Hebergement();
                h.setId(rs.getInt(1));
                h.setNomH(rs.getString("nomH"));
                h.setType(rs.getString("type"));
                h.setAdresse(rs.getString("adresse"));
                h.setAgence_id(rs.getInt("agence_id"));

                myList.add(h);
                }
              System.out.println("affichage succées");
            }
          } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    return myList;

}
    @Override
   public boolean supprimerHebergement(int id) {
 String delete= "DELETE FROM hebergement where id= ?";
        try {
    PreparedStatement prst = con.prepareStatement(delete);
            prst.setLong(1,id);
            prst.executeUpdate();
            System.out.println("suppression hebergement avec succées");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return false;
}
       @Override
   public boolean modifierHebergement( Hebergement h) {
   try {
            String req = "UPDATE hebergement SET  nomH=? , type=? , adresse=? , agence_id=?  WHERE id=?";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, h.getNomH());
            pstm.setString(2, h.getType());
            pstm.setString(3, h.getAdresse());
            pstm.setInt(4, h.getAgence_id());
            pstm.setInt(5, h.getId());
            pstm.executeUpdate();
            System.out.println("Hebergemnt Modifié");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(HebergementCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de modification du Hebergemnt");
            return false;
        }

    }
 public List<Hebergement> afficherHebergementByAgence(int agence) {
     List<Hebergement>myList=  new ArrayList<>();;
        try {

          String requete =  "SELECT * FROM agence a, Hebergement h where h.agence_id=a.id AND a.id=" + agence;
         Statement st =con.createStatement();
         ResultSet rs =    st.executeQuery(requete);

          while(rs.next()){
                    {
                Hebergement h = new Hebergement();
                h.setId(rs.getInt(1));
                h.setNomH(rs.getString("nomH"));
                h.setType(rs.getString("type"));
                h.setAdresse(rs.getString("adresse"));
                h.setAgence_id(rs.getInt("agence_id"));

                myList.add(h);
                }
              System.out.println("affichage succées");
            }
          } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    return myList;


        
    }   
  @Override
    public Hebergement RecupererHebergement(int id) {
        
        Hebergement hebergement = new Hebergement();
        try {
            String req = "SELECT * FROM hebergement WHERE id=\""+id+"\";";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                hebergement.setId(rs.getInt("id"));
                hebergement.setNomH(rs.getString("nomh"));
                hebergement.setType(rs.getString("type"));
                hebergement.setAgence_id(rs.getInt("agence_id"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de récuperation");
        }
        return hebergement;

        
    } 
    
    public List<Hebergement> recherche(String searched) {

        List<Hebergement> lista = new ArrayList<>();
        try {

            String req = "select * from hebergement WHERE nomH LIKE '%" + searched + "%' OR adresse LIKE '%" + searched + "%' OR type LIKE '%" + searched + "%' ;";
            PreparedStatement pst = con.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            Hebergement h = new Hebergement();
            while (rs.next()) {

              h.setId(rs.getInt(1));
                h.setNomH(rs.getString("nomH"));
                h.setType(rs.getString("type"));
                h.setAdresse(rs.getString("adresse"));
                h.setAgence_id(rs.getInt("agence_id"));

                lista.add(h);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("errreur" + ex.getMessage());
        }
        return null;

    }
    /*
 public Hebergement getByID(int x) {

        try {

            String requete = "select  h.* ,a.id from hebergement p , agence a where a.id=h.agence_id and id='" + x + "'"; // statique
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                Produits p = new Produits();
                p.setIdprod(rs.getInt(1));
                p.setImage(rs.getString(3));
                p.setLibelle(rs.getString(4));
                p.setPrix(rs.getInt(5));
                p.setDescription(rs.getString(6));
                return p;

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;

    }    
   */     
}
