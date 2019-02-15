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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import metiers.Identification;
import metiers.Verification;

/**
 * FXML Controller class
 *
 * @author Germinator-PC
 */
public class ElecteurController implements Initializable {

    @FXML
    private ImageView exit;
    @FXML
    private TextField email;
    @FXML
    private TextField cni;
    @FXML
    private ImageView help;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
        
        if ((cni.getText().isEmpty()) || (email.getText().isEmpty())) {
            
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.initStyle(StageStyle.UNDECORATED);
            msg.setHeaderText("Champ vide");
            msg.setContentText("Veuillez renseigner les champs avant de passer à l'identification.");
            msg.show();
            
        }
        
        else {
            
            Verification verification = new Verification();
            boolean mail = verification.email(email.getText());
            boolean numero = verification.cni(cni.getText());
            
            if (mail && numero) {
                
                Identification electeur = new Identification();
                int id = electeur.electeur(cni.getText(), email.getText());
                
                if ((id != 0) && (id != -1)) {
                    
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("Vote.fxml"));
                    loader.load();
                    
                    VoteController controller = loader.getController();
                    controller.initData(id);
                    
                    Parent vote = loader.getRoot();
                    Scene vote_scene = new Scene(vote);
                    Stage vote_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    vote_stage.hide();
                    vote_stage.setScene(vote_scene);
                    vote_stage.show();

                }
                
                else if (id == -1) {
                    
                    Alert msg = new Alert(Alert.AlertType.WARNING);
                    msg.initStyle(StageStyle.UNDECORATED);
                    msg.setHeaderText("Vote déjà éffectué");
                    msg.setContentText("Vous aviez déjà voté. Vous n'êtes donc plus habilité à voter.");
                    msg.show();
                    
                }
                
                else {
                    
                    Alert msg = new Alert(Alert.AlertType.WARNING);
                    msg.initStyle(StageStyle.UNDECORATED);
                    msg.setHeaderText("Informations incorrectes");
                    msg.setContentText("Vos informations saisies ne correspondent pas.");
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
