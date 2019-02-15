/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Germinator-PC
 */
public class Verification {
    
    public Verification() {
        
    }
    
    public boolean email(String email){
        
        Pattern pattern = Pattern.compile("^[A-Z0-9._-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher mail = pattern.matcher(email.toUpperCase());
        
        return mail.matches();
        
    }
    
    public boolean cni(String cni) {
        
        Pattern pattern = Pattern.compile("[A-Z]+[ ]+[0-9]{4}+[ ]+[0-9]{4}+[ ]+[0-9]{2}");
        Matcher c = pattern.matcher(cni);
        
        return c.matches();
        
    }
    
    public boolean contact(String contact) {
        
        Pattern pattern = Pattern.compile("[0-9]{8}");
        Matcher tel = pattern.matcher(contact);
        
        return tel.matches();
        
    }
    
}
