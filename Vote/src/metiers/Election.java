/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Germinator-PC
 */
public class Election {
    
    Connexion conn = new Connexion();
    Connection connect = Connexion.connexion();
    PreparedStatement ps;
    
    public Election() {
        
    }
    
    public int vote(int electeur, int candidat) {
        
        int i = 0;
        
        try {
            
            ps = connect.prepareStatement("update personnes set candidat="+candidat+" where id=" + electeur);
            ps.executeUpdate();
            
            connect.close();
            i = 1;
            
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return i;
        
    }
    
}
