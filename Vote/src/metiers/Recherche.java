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
public class Recherche {
    
    Connexion conn = new Connexion();
    Connection connect = Connexion.connexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public Recherche() {
        
    }
    
    public List<Personnes> personnes(String noms) {
        
        List<Personnes> listePersonnes = new ArrayList<>();
        
        try {
            
            ps = connect.prepareStatement("select * from personnes where noms='"+noms+"'");
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
    
    public List<Personnes> candidats(String noms) {
        
        List<Personnes> candidats;
        List<Personnes> liste = new ArrayList<>();
        candidats = new Liste().candidature();
        
        candidats.stream().filter((candidat) -> (candidat.getNoms().equals(noms))).forEachOrdered(
            (candidat) -> {
                liste.add(
                    new Personnes(
                        candidat.getId(),
                        candidat.getNoms(),
                        candidat.getPrenoms(),
                        candidat.getSexe(),
                        candidat.getEmail(),
                        candidat.getCni(),
                        candidat.getContact()
                    )
                );
            }
        );
        
        return liste;
        
    }
    
}
