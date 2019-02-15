/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vote;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Germinator-PC
 */
public class AccueilController implements Initializable {

    @FXML
    private ImageView exit;   
    @FXML
    private ImageView help;
    @FXML
    private ImageView admin;

    @FXML
    private void sortie(MouseEvent event) {
        
        Platform.exit();
        System.exit(0);
        
    }
    
    @FXML
    private void administrateur(MouseEvent event) throws IOException {
        
        Parent administrateur = FXMLLoader.load(getClass().getResource("Administrateur.fxml"));
        Scene admin_scene = new Scene(administrateur);
        Stage admin_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        admin_stage.hide();
        admin_stage.setScene(admin_scene);
        admin_stage.show();
        
    }
    
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
    private void electeur(ActionEvent event) throws IOException {
        
        Parent electeur = FXMLLoader.load(getClass().getResource("Electeur.fxml"));
        Scene electeur_scene = new Scene(electeur);
        Stage electeur_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        electeur_stage.hide();
        electeur_stage.setScene(electeur_scene);
        electeur_stage.show();
        
    }

    @FXML
    private void aide(MouseEvent event) throws IOException {
        
        Parent aide = FXMLLoader.load(getClass().getResource("Aide.fxml"));
        Scene aide_scene = new Scene(aide);
        Stage aide_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        aide_stage.hide();
        aide_stage.setScene(aide_scene);
        aide_stage.show();
        
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
    private void adminExited(MouseEvent event) {
        
        URL imageURL = getClass().getResource("/images/admin.png");
        Image fichier = new Image(imageURL.toExternalForm());
        admin.setImage(fichier);
        
    }

    @FXML
    private void adminEntered(MouseEvent event) {
        
        URL imageURL = getClass().getResource("/images/admin1.png");
        Image fichier = new Image(imageURL.toExternalForm());
        admin.setImage(fichier);
        
    }
    
}
