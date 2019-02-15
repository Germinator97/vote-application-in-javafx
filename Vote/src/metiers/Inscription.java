/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import connexion.Connexion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Germinator-PC
 */
public class Inscription {
    
    Connexion conn = new Connexion();
    Connection connect = Connexion.connexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public Inscription() {
        
    }
    
    public int personnes(String statut, String noms, String prenoms, String sexe, String email, String cni, String contact, String file) throws IOException {
        
        int id = 0;
        int i = 0;
        
        try {
            
            ps = connect.prepareStatement("select * from personnes where cni='"+cni+"' or email='"+email+"' or contact='"+contact+"'");
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
            
            if (id == 0) {
                
                ps = connect.prepareStatement("select id from personnes order by id desc limit 1");
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    i = rs.getInt(1);
                }
                
                i++;
                
                ps = connect.prepareStatement("insert into personnes values("+i+", '"+noms+"', '"+prenoms+"', '"+sexe+"', '"+email+"', '"+cni+"', '"+contact+"', 0)");
                ps.executeUpdate();
                
                if (statut.equals("Candidat")) {
                    
                    if (!file.equals("null")) {
                        
                        File fichier = new File(file);
                        File source = fichier.getAbsoluteFile();
                        String name = email + fichier.getName().substring(fichier.getName().indexOf("."));
                        String dest = new File("src/photos/" + name).getAbsolutePath();
                        File destination = new File(dest);

                        InputStream is = new FileInputStream(source);
                        OutputStream os = new FileOutputStream(destination);
                        byte[] buffer = new byte[1024];
                        int length;
                        
                        while ((length = is.read(buffer)) > 0) {
                            os.write(buffer, 0, length);
                        }

                        ps = connect.prepareStatement("insert into candidat values("+i+", '"+name+"')");
                        ps.executeUpdate();
                        
                    }
                    
                    else {
                        
                        ps = connect.prepareStatement("insert into candidat values("+i+", null)");
                        ps.executeUpdate();
                        
                    }
                    
                }
   
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
