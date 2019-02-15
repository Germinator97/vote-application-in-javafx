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

/**
 *
 * @author Germinator-PC
 */
public class Identification {
    
    Connexion conn = new Connexion();
    Connection connect = Connexion.connexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public Identification() {
        
    }
    
    public int electeur(String cni, String email) {
        
        int id = 0;
        
        try {
            
            ps = connect.prepareStatement("select * from personnes where cni='"+cni+"' and email='"+email+"'");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                
                if (rs.getInt("candidat") == 0) {
                    id = rs.getInt("id");
                }
                
                else {
                    id = -1;
                }
                
            }
            
            connect.close();
            rs.close();
            
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return id;
        
    }
    
    public int administrateur(String username, String password) {
        
        int i = 0;
        
        try {
            
            ps = connect.prepareStatement("select count(*) from administrateur where username='"+username+"' and password='"+password+"'");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                i = rs.getInt("count(*)");
            }
            
            connect.close();
            rs.close();
  
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return i;
        
    }
    
}
