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
public class Modification {
    
    Connexion conn = new Connexion();
    Connection connect = Connexion.connexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public Modification() {
        
    }
    
    public int personnes(int identifiant, String statut, String noms, String prenoms, String sexe, String email, String cni, String contact, String file) throws IOException {
        
        int id = 0;
        int i = 0;
        String img = null;
        
        try {
            
            ps = connect.prepareStatement("select count(*) from personnes where cni='"+cni+"' or email='"+email+"' or contact='"+contact+"'");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                i = rs.getInt(1);
            }
            
            if ((i == 1) || (i == 0)) {
                
                ps = connect.prepareStatement("update personnes set noms='"+noms+"', prenoms='"+prenoms+"', sexe='"+sexe+"', email='"+email+"', cni='"+cni+"', contact='"+contact+"' where id=" + identifiant);
                ps.executeUpdate();
                
                ps = connect.prepareStatement("select * from candidat where id=" + identifiant);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    
                    id = rs.getInt(1);
                    img = rs.getString(2);
                    
                }
                
                if (id != 0) {

                    switch (statut) {
                        
                        case "Electeur":
                            
                            ps = connect.prepareStatement("delete from candidat where id=" + identifiant);
                            ps.executeUpdate();
                            
                            break;
                            
                        case "Candidat":

                            if (file.equals(img)) {
                                
                            }

                            else if (!file.equals("null")) {
                                
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

                                ps = connect.prepareStatement("update candidat set photo='"+name+"' where id=" + identifiant);
                                ps.executeUpdate();
                                
                            }
                            
                            else {
                                ps = connect.prepareStatement("update candidat set photo=null where id=" + identifiant);
                                ps.executeUpdate();
                            }
                            
                            break;
                            
                        default:
                            break;
                            
                    }

                }
            
                else {
                    
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

                            ps = connect.prepareStatement("insert into candidat values("+identifiant+", '"+name+"')");
                            ps.executeUpdate();
                            
                        }
                        
                        else {
                            
                            ps = connect.prepareStatement("insert into candidat values("+identifiant+", null)");
                            ps.executeUpdate();
                        
                        }
                        
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
    
    public boolean admin(String username, String ancien, String nouveau) {
        
        boolean test = false;
        String pass = null;
        
        try {
            
            ps = connect.prepareStatement("select * from administrateur");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                pass = rs.getString(2);
            }
            
            if (ancien.equals(pass)) {
                
                ps = connect.prepareStatement("update administrateur set username='"+username+"', password='"+nouveau+"'");
                ps.executeUpdate();
                
                test = true;
                
            }
            
            connect.close();
            rs.close();
            
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return test;
        
    }
    
}
