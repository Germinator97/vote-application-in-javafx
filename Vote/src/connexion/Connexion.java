/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Germinator-PC
 */
public class Connexion {
    
    private static Connection conn;
    
    public static Connection connexion() {
        
        try {
            //Connexion à la base de données
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vote", "root", "");
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return conn;
        
    }
    
}
