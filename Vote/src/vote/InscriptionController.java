/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vote;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import metiers.Inscription;
import metiers.Verification;

/**
 * FXML Controller class
 *
 * @author Germinator-PC
 */
public class InscriptionController implements Initializable {

    @FXML
    private ImageView exit;
    @FXML
    private Button valider;
    @FXML
    private ToggleGroup sexes;
    @FXML
    private ToggleGroup statuts;
    @FXML
    private TextField noms;
    @FXML
    private TextField prenoms;
    @FXML
    private TextField email;
    @FXML
    private TextField cni;
    @FXML
    private TextField contact;
    @FXML
    private Button ajouter;
    @FXML
    private ImageView photo;
    @FXML
    private Button supprimer;
    @FXML
    private Label image;
    @FXML
    private ImageView help;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        choix(true);
        statuts.getSelectedToggle().selectedProperty().addListener((observable, oldValue, newValue) -> choix(newValue));
    
    }
    
    public void choix(boolean choix) {
        
        if (choix != true) {
            
            ajouter.setDisable(false);
            
            if (image.getText().equals("null")) {
                supprimer.setDisable(true);
            }
            
            else {
                supprimer.setDisable(false);
            }
            
        }
        
        else {
            
            ajouter.setDisable(true);
            supprimer.setDisable(true);
            
        }
        
    }
    

    @FXML
    private void sortie(MouseEvent event) throws IOException {
        
        Parent admin = FXMLLoader.load(getClass().getResource("Administration.fxml"));
        Scene admin_scene = new Scene(admin);
        Stage admin_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        admin_stage.hide();
        admin_stage.setScene(admin_scene);
        admin_stage.show();
        
    }

    @FXML
    private void valider(ActionEvent event) throws IOException {
        
        if ((noms.getText().isEmpty()) || (prenoms.getText().isEmpty()) || (email.getText().isEmpty()) || (cni.getText().isEmpty()) || (contact.getText().isEmpty())) {
            
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.initStyle(StageStyle.UNDECORATED);
            msg.setHeaderText("Champ vide");
            msg.setContentText("Veuillez renseigner les champs avant de passer à l'enregistrement.");
            msg.show();
            
        }
        
        else {
            
            Verification verification = new Verification();
            boolean mail = verification.email(email.getText());
            boolean numero = verification.cni(cni.getText());
            boolean tel = verification.contact(contact.getText());
            
            if (mail && numero && tel) {
                
                String statut = ((statuts.getSelectedToggle().toString()).split("'"))[1];
                String sexe = ((sexes.getSelectedToggle().toString()).split("'"))[1];
                
                Inscription inscrit = new Inscription();
                int i = inscrit.personnes(statut, noms.getText(), prenoms.getText(), sexe, email.getText(), cni.getText(), contact.getText(), image.getText());

                if (i != 0) {
                    
                    Parent admin = FXMLLoader.load(getClass().getResource("Administration.fxml"));
                    Scene admin_scene = new Scene(admin);
                    Stage admin_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    admin_stage.hide();
                    admin_stage.setScene(admin_scene);
                    admin_stage.show();
                    
                }
                else {
                    
                    Alert msg = new Alert(Alert.AlertType.WARNING);
                    msg.initStyle(StageStyle.UNDECORATED);
                    msg.setHeaderText("Occurrence trouvée");
                    msg.setContentText("Veuillez vérifier vos informations. Nous avons trouvé une occurrence portant ces informations.");
                    msg.show();
                    
                }
                
            }
            
            else if (!mail) {
                
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.initStyle(StageStyle.UNDECORATED);
                msg.setHeaderText("Mail incorrect");
                msg.setContentText("Le format de mail saisi ne correspondent pas.");
                msg.show();
                
            }
            
            else if (!numero) {
                
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.initStyle(StageStyle.UNDECORATED);
                msg.setHeaderText("Cni incorrect");
                msg.setContentText("Le format du cni saisi ne correspondent pas.");
                msg.show();
                
            }
            
            else if (!tel) {
                
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.initStyle(StageStyle.UNDECORATED);
                msg.setHeaderText("Contact incorrect");
                msg.setContentText("Le format du contact saisi ne correspondent pas.");
                msg.show();
                
            }
            
        }
        
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
        FileChooser fileChooser = new FileChooser ();
        fileChooser.setTitle ("Choisissez une photo de profil");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        
        fileChooser.getExtensionFilters().addAll( 
            new FileChooser.ExtensionFilter("JPG", "*.jpg"), 
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        
        File file = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
        
        if (file != null) {
            
            image.setText(file.toString());
            
            File source = file.getAbsoluteFile();
            String imageURL = source.toURI().toString();
            Image fichier = new Image(imageURL);
            photo.setImage(fichier);
            supprimer.setDisable(false);
            
        }
        
        else {
            image.setText("null");
        }
        
    }

    @FXML
    private void supprimer(ActionEvent event) {

        URL imageURL = getClass().getResource("/images/photo.png");
        Image fichier = new Image(imageURL.toExternalForm());
        photo.setImage(fichier);
        supprimer.setDisable(true);
        image.setText("null");
        
    }

    @FXML
    private void sortieExited(MouseEvent event) {
        
        URL imageURL = getClass().getResource("/images/exit.png");
        Image fichier = new Image(imageURL.toExternalForm());
        exit.setImage(fichier);
        
    }

    @FXML
    private void sortieEntered(MouseEvent event) {
        
        URL imageURL = getClass().getResource("/images/exit1.png");
        Image fichier = new Image(imageURL.toExternalForm());
        exit.setImage(fichier);
        
    }

    @FXML
    private void aideExited(MouseEvent event) {
        
        URL imageURL = getClass().getResource("/images/help.png");
        Image fichier = new Image(imageURL.toExternalForm());
        help.setImage(fichier);
        
    }

    @FXML
    private void aideEntered(MouseEvent event) {
        
        URL imageURL = getClass().getResource("/images/help1.png");
        Image fichier = new Image(imageURL.toExternalForm());
        help.setImage(fichier);
        
    }

    @FXML
    private void aide(MouseEvent event) throws IOException {
    }
    
}
