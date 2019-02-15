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
public class Suppression {
    
    Connexion conn = new Connexion();
    Connection connect = Connexion.connexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public Suppression() {
        
    }
    
    public void personnes(int id) {
        
        int i = 0;
        
        try {
            
            ps = connect.prepareStatement("delete from personnes where id=" + id);
            ps.executeUpdate();
                
            ps = connect.prepareStatement("select * from candidat where id=" + id);
            rs = ps.executeQuery();
                
            while (rs.next()) {
                i = rs.getInt("id");
            }
                
            if (i != 0) {
                
                ps = connect.prepareStatement("delete from candidat where id=" + i);
                ps.executeUpdate();
                
            }
            
            connect.close();
            rs.close();
            
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
