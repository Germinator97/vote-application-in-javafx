/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modeles.Personnes;

/**
 *
 * @author Germinator-PC
 */
public class Liste {

    Connexion conn = new Connexion();
    Connection connect = Connexion.connexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public Liste() {
        
    }
    
    public List<Personnes> liste() {
        
        List<Personnes> listePersonnes = new ArrayList<>();
        
        try {
            
            ps = connect.prepareStatement("select * from personnes");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                
                listePersonnes.add(
                    new Personnes(
                        rs.getInt("id"),
                        rs.getString("noms"),
                        rs.getString("prenoms"),
                        rs.getString("sexe"),
                        rs.getString("email"),
                        rs.getString("cni"),
                        rs.getString("contact")
                    )
                );
                
            }
            
            connect.close();
            rs.close();
            
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return listePersonnes;
        
    }
    
    public String user() {
        
        String username = null;
        
        try {
            
            ps = connect.prepareStatement("select * from administrateur");
            rs = ps.executeQuery();
            
            while(rs.next()) {
                username = rs.getString(1);
            }
            
            connect.close();
            rs.close();
            
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return username;
        
    }
    
    public String photos(int id) {
        
        String photo = null;
        
        try {
            
            ps = connect.prepareStatement("select * from candidat where id=" + id);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                photo = rs.getString(2);
            }
            
            connect.close();
            rs.close();
            
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return photo;
        
    }
    
    public int effectif() {
        
        int i = 0;
        
        try {
            
            ps = connect.prepareStatement("select count(*) from candidat");
            rs = ps.executeQuery();
                
            while(rs.next()) {
                i = rs.getInt(1);
            }
    
            connect.close();
            rs.close();
            
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return i;
        
    }
    
    public int[] electeurs() {
        
        int[] electeurs = null;
        int[] idCandidats;
        
        try {
            
            idCandidats = new Liste().idCandidats();
            int i = idCandidats.length;
            electeurs = new int[i];
            
            for (int k =0; k <i; k++) {
                
                ps = connect.prepareStatement("select count(*) from personnes where candidat=" + idCandidats[k]);
                rs = ps.executeQuery();
                
                while(rs.next()) {
                    electeurs[k] = rs.getInt(1);
                }
                
            }
            
            connect.close();
            rs.close();
            
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return electeurs;
        
    }
    
    public int[] idCandidats() {
        
        int i;
        int[] candidats = null;
        int k = 0;
        
        try {
            
            i = new Liste().effectif();
            candidats = new int[i];
            
            ps = connect.prepareStatement("select * from candidat");
            rs = ps.executeQuery();
                
            while(rs.next()) {
                
                candidats[k] = rs.getInt(1);
                k++;
                
            }
            
            connect.close();
            rs.close();
            
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return candidats;
        
    }
    
    public List<Personnes> candidature() {
        
        List<Personnes> listeCandidats = new ArrayList<>();
        int[] candidats;
        
        try {
            
            candidats = new Liste().idCandidats();
            
            for (int k =0; k <candidats.length; k++) {
                
                ps = connect.prepareStatement("select * from personnes where id=" + candidats[k]);
                rs = ps.executeQuery();
                
                while(rs.next()) {
                    
                    listeCandidats.add(
                        new Personnes(
                            rs.getInt("id"),
                            rs.getString("noms"),
                            rs.getString("prenoms"),
                            rs.getString("sexe"),
                            rs.getString("email"),
                            rs.getString("cni"),
                            rs.getString("contact")
                        )
                    );
                    
                }
                
            }
            
            connect.close();
            rs.close();
            
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return listeCandidats;
        
    }
    
    public String[] nomCandidats() {
        
        String[] candidats = null;
        int[] idCandidats;
        
        try {
            
            idCandidats = new Liste().idCandidats();
            int i = idCandidats.length;
            candidats = new String[i];
            
            for (int k =0; k <i; k++) {
                
                ps = connect.prepareStatement("select * from personnes where id=" + idCandidats[k]);
                rs = ps.executeQuery();
                
                while(rs.next()) {
                    candidats[k] = rs.getString("noms");
                }
                
            }
            
            connect.close();
            rs.close();
            
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return candidats;
        
    }
    
}
