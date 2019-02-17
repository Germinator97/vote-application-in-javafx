/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vote;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import metiers.Liste;
import metiers.Modification;

/**
 * FXML Controller class
 *
 * @author Germinator-PC
 */
public class ModificationController implements Initializable {

    @FXML
    private ImageView exit;
    @FXML
    private TextField username;
    @FXML
    private PasswordField ancien_pass;
    @FXML
    private PasswordField nouveau_pass;
    @FXML
    private PasswordField confirm_pass;
    @FXML
    private Button modifier;
    @FXML
    private ImageView help;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Liste name = new Liste();
        username.setText(name.user());
        
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
    private void modifier(ActionEvent event) throws IOException {
        
        if ((username.getText().isEmpty()) || (ancien_pass.getText().isEmpty()) || (nouveau_pass.getText().isEmpty()) || (confirm_pass.getText().isEmpty())) {
            
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.initStyle(StageStyle.UNDECORATED);
            msg.setHeaderText("Champ vide");
            msg.setContentText("Veuillez renseigner les champs avant de passer à la modification.");
            msg.show();
            
            ancien_pass.clear();
            nouveau_pass.clear();
            confirm_pass.clear();
  
        }
        
        else {
            
            if (nouveau_pass.getText().equals(confirm_pass.getText())) {
                
                Modification parametre = new Modification();
                boolean test = parametre.admin(username.getText(), ancien_pass.getText(), nouveau_pass.getText());
                
                if (test == true) {
                    
                    Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
                    msg.initStyle(StageStyle.UNDECORATED);
                    msg.setHeaderText("Confirmation de la modification");
                    msg.setContentText("Voulez-vous effectiver modifier vos informations d'adminnistartaeur ?");

                    Optional<ButtonType> choix = msg.showAndWait();

                    if (choix.get() == ButtonType.OK) {

                        Parent admin = FXMLLoader.load(getClass().getResource("Administration.fxml"));
                        Scene admin_scene = new Scene(admin);
                        Stage admin_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        admin_stage.hide();
                        admin_stage.setScene(admin_scene);
                        admin_stage.show();

                    }
                    
                }
                
                else {
                    
                    Alert msg = new Alert(Alert.AlertType.WARNING);
                    msg.initStyle(StageStyle.UNDECORATED);
                    msg.setHeaderText("Mot de passe incorrect");
                    msg.setContentText("Votre ancien mot de passe ne correspond pas.");
                    msg.show();
                    
                    ancien_pass.clear();
                    nouveau_pass.clear();
                    confirm_pass.clear();
                    
                }
            
            }
            
            else {
                
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.initStyle(StageStyle.UNDECORATED);
                msg.setHeaderText("Confirmation invalide");
                msg.setContentText("La confirmation du nouveau mot de passe ne correspond pas.");
                msg.show();
                
                ancien_pass.clear();
                nouveau_pass.clear();
                confirm_pass.clear();
                
            }
            
        }
         
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
