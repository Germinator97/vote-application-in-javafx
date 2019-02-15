/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vote;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import metiers.Identification;

/**
 * FXML Controller class
 *
 * @author Germinator-PC
 */
public class AdministrateurController implements Initializable {

    @FXML
    private ImageView exit;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView help;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sortie(MouseEvent event) throws IOException {
        
        Parent accueil = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        Scene accueil_scene = new Scene(accueil);
        Stage accueil_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        accueil_stage.hide();
        accueil_stage.setScene(accueil_scene);
        accueil_stage.show();
        
    }

    @FXML
    private void identification(ActionEvent event) throws IOException {
        
        if ((username.getText().isEmpty()) || (password.getText().isEmpty())) {
            
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.initStyle(StageStyle.UNDECORATED);
            msg.setHeaderText("Champ vide");
            msg.setContentText("Veuillez renseigner les champs avant de passer à l'identification.");
            msg.show();
            
            password.clear();
            
        }
        
        else {
            
            Identification electeur = new Identification();
            int id = electeur.administrateur(username.getText(), password.getText());

            if (id == 1) {
                
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
                msg.setHeaderText("Informations incorrectes");
                msg.setContentText("Vos informations saisies ne correspondent pas.");
                msg.show();
                
                password.clear();
                
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
