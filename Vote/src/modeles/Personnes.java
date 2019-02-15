/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Germinator-PC
 */
public class Personnes {
    
    private final IntegerProperty id;
    private final StringProperty noms;
    private final StringProperty prenoms;
    private final StringProperty sexe;
    private final StringProperty email;
    private final StringProperty cni;
    private final StringProperty contact;
    
    public Personnes(int id, String noms, String prenoms, String sexe, String email, String cni, String contact) {
        this.id = new SimpleIntegerProperty(id);
        this.noms = new SimpleStringProperty(noms);
        this.prenoms = new SimpleStringProperty(prenoms);
        this.sexe = new SimpleStringProperty(sexe);
        this.email = new SimpleStringProperty(email);
        this.cni = new SimpleStringProperty(cni);
        this.contact = new SimpleStringProperty(contact);
    }

    public int getId() {
        return id.get();
    }
    
    public void setId(int id) {
        this.id.set(id);
    }
    
    public IntegerProperty idProperty() {
        return id;
    }

    public String getNoms() {
        return noms.get();
    }
    
    public void setNoms(String noms) {
        this.noms.set(noms);
    }
    
    public StringProperty nomsProperty() {
        return noms;
    }

    public String getPrenoms() {
        return prenoms.get();
    }
    
    public void setPrenoms(String prenoms) {
        this.prenoms.set(prenoms);
    }
    
    public StringProperty prenomsProperty() {
        return prenoms;
    }

    public String getSexe() {
        return sexe.get();
    }
    
    public void setSexe(String sexe) {
        this.sexe.set(sexe);
    }
    
    public StringProperty sexeProperty() {
        return sexe;
    }

    public String getEmail() {
        return email.get();
    }
    
    public void setEmail(String email) {
        this.email.set(email);
    }
    
    public StringProperty emailProperty() {
        return email;
    }

    public String getCni() {
        return cni.get();
    }
    
    public void setCni(String cni) {
        this.cni.set(cni);
    }
    
    public StringProperty cniProperty() {
        return cni;
    }

    public String getContact() {
        return contact.get();
    }
    
    public void setContact(String contact) {
        this.contact.set(contact);
    }
    
    public StringProperty contactProperty() {
        return contact;
    }
    
    
}
