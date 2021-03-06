 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Resteau;
import utils.DataSource;

/**
 *
 * @author hp
 */
public class ServiceResteau   implements Iresteau<Resteau> {

    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Resteau t) {
      
    
          try {
            String req = "INSERT INTO resteau( typeR,nomR,adressR,paysR,telR,imgR,idR) VALUES ('" + t.getTypeR()+ "' , '" + t.getNomR()+ "' , '" + t.getAdressR()+ "'  , '" +t.getPaysR()+ "' , '" +t.getTelR()+ "','" +t.getImgR()+ "',NULL)";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("resteau  ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    @Override

    public List<Resteau> getAll() {
        List<Resteau> list = new ArrayList<>();
        
        try {
            String req = "Select * from resteau";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Resteau R = new Resteau(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
                list.add(R);
            }
                System.out.println("Notre resteau");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
        
    }

    @Override
      public boolean modifier(Resteau t) {
       boolean modif=true;
       try {
           String req = "UPDATE resteau SET  typeR=?, nomR=?, adressR=?, paysR=?, telR=? WHERE idR =?";
           PreparedStatement ps = cnx.prepareStatement(req);
         //  super.modifier(t);
            ps.setString(1, t.getTypeR());
            ps.setString(2, t.getNomR());
            ps.setString(3, t.getAdressR());
            ps.setString(4, t.getPaysR());
            ps.setString(5, t.getTelR());
           

            ps.setInt(6, t.getIdR());

            ps.executeUpdate(); 
            System.out.println("resteau Modifièè  sahyyyyt!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            modif=false;
        }
        return modif;
    }
    

    @Override
    public boolean supprimerR(Resteau t) {
        boolean modif=false;

        try {

            Statement st = cnx.createStatement();
            String req = "DELETE FROM resteau WHERE idR=?";
               PreparedStatement ps =  cnx.prepareStatement(req);
            ps.setInt(1, t.getIdR());
            ps.executeUpdate();
            System.out.println("restau supprimer!");

        } catch (SQLException ex) {
                        System.err.println(ex.getMessage());

        }
                return modif;

       
    }
   
    @Override
    public void TRIRestau(){
        List<Resteau> list = new ArrayList<>();
         List<Resteau> list2= getAll();
         
         list= list2.stream().sorted((o1,o2)->o1.getNomR().compareTo(o2.getNomR())).collect(Collectors.toList());
         System.out.println(list);
               
     }
   public ObservableList<Resteau> getListResteau() {
        ObservableList<Resteau> ResteaulList = FXCollections.observableArrayList();
        try {
            String req = "Select * from resteau";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Resteau R = new Resteau(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
                ResteaulList.add(R);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ResteaulList; 
    }
    public String getNomByIDResteau(int idR) {
        String nom  ="";
        try {
            String req = "Select nomR from resteau  WHERE idR ="+idR;
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                 nom = rs.getString("nomR");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nom;
    }

}
