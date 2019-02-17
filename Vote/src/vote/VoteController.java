/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vote;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import metiers.Election;
import metiers.Liste;
import metiers.Recherche;
import modeles.Personnes;


/**
 * FXML Controller class
 *
 * @author Germinator-PC
 */
public class VoteController implements Initializable {

    @FXML
    private ImageView exit;
    @FXML
    private TableView<Personnes> liste;
    @FXML
    private TableColumn<Personnes, Integer> id;
    @FXML
    private TableColumn<Personnes, String> noms_col;
    @FXML
    private TableColumn<Personnes, String> prenoms_col;
    @FXML
    private Label noms;
    @FXML
    private Label prenoms;
    @FXML
    private Label sexe;
    @FXML
    private Label email;
    @FXML
    private Label cni;
    @FXML
    private Label contact;
    @FXML
    private ImageView photo;
    @FXML
    private ImageView help;
    @FXML
    private Button voter;
    @FXML
    private Label idCandidat;
    @FXML
    private Label idElecteur;
    @FXML
    private TextField recherche;
    @FXML
    private Button rechercher;
    @FXML
    private ImageView refresh;
    
    private ObservableList<Personnes> candidats = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        remplir();
    }
    
    public void remplir() {
        
        candidats = getCandidats();
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        noms_col.setCellValueFactory(new PropertyValueFactory<>("noms"));
        prenoms_col.setCellValueFactory(new PropertyValueFactory<>("prenoms"));
        
        liste.setItems(candidats);
        
        candidatsDetails(null);
        
        liste.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> candidatsDetails(newValue));
        
    }
    
    public void initData(int id) {
        idElecteur.setText(String.valueOf(id));
    }
    
    public VoteController() {
        
    }
    
    public ObservableList<Personnes> getCandidats() {
        
        Liste candidatsListe = new Liste();
        List<Personnes> listeCandidats = candidatsListe.candidature();
        
        listeCandidats.forEach(
            (candidat) -> {
                candidats.add(
                    new Personnes(
                        candidat.getId(),
                        candidat.getNoms(),
                        candidat.getPrenoms(),
                        candidat.getSexe(),
                        candidat.getEmail(),
                        candidat.getCni(),
                        candidat.getContact()
                    )
                );
            }
        );
        
        return candidats;
        
    }
    
    public ObservableList<Personnes> getRecherche() {
        
        Recherche personnesListe = new Recherche();
        List<Personnes> listePersonnes = personnesListe.candidats(recherche.getText());
        
        listePersonnes.forEach(
            (candidat) -> {
                candidats.add(
                    new Personnes(
                        candidat.getId(),
                        candidat.getNoms(),
                        candidat.getPrenoms(),
                        candidat.getSexe(),
                        candidat.getEmail(),
                        candidat.getCni(),
                        candidat.getContact()
                    )
                );
            }
        );
        
        return candidats;
    
    }
    
    private void candidatsDetails(Personnes candidat) {
        
        if (candidat != null) {
            
            photo.setVisible(true);
            
            Liste image = new Liste();
            String name = image.photos(candidat.getId());
            
            if (name != null) {
                
                File fichier = new File("src/photos/" + name);
                File source = fichier.getAbsoluteFile();
                String imageURL = source.toURI().toString();
                Image img = new Image(imageURL);
                photo.setImage(img);
                
            }
            
            else {
                
                URL imageURL = getClass().getResource("/images/photo.png");
                Image fichier = new Image(imageURL.toExternalForm());
                photo.setImage(fichier);
                
            }
            
            noms.setText(candidat.getNoms());
            prenoms.setText(candidat.getPrenoms());
            sexe.setText(candidat.getSexe());
            email.setText(candidat.getEmail());
            cni.setText(candidat.getCni());
            contact.setText(candidat.getContact());
            voter.setDisable(false);
            idCandidat.setText(String.valueOf(candidat.getId()));
            
        }
        
        else {
            
            photo.setVisible(false);
            noms.setText("");
            prenoms.setText("");
            sexe.setText("");
            email.setText("");
            cni.setText("");
            contact.setText("");
            voter.setDisable(true);
            
        }
        
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
    private void aide(MouseEvent event) throws IOException {
    }

    @FXML
    private void voter(ActionEvent event) throws IOException {
        
        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.initStyle(StageStyle.UNDECORATED);
        msg.setHeaderText("Confirmation du choix");
        msg.setContentText("Voulez-vous effectivement voter pour ce candiat ? "
                + "Si oui, votre choix sera enregistré. Cependant vous n'aurez plus la possibilité de refaire le vote. "
                + "Assurez vous donc de votre choix de candidat.");
            
        Optional<ButtonType> choix = msg.showAndWait();
        
        if (choix.get() == ButtonType.OK) {
            
            Election election = new Election();
            election.vote(Integer.parseInt(idElecteur.getText()), Integer.parseInt(idCandidat.getText()));
            
            Parent accueil = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            Scene accueil_scene = new Scene(accueil);
            Stage accueil_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            accueil_stage.hide();
            accueil_stage.setScene(accueil_scene);
            accueil_stage.show();
            
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
    private void rechercher(ActionEvent event) {
        
        if(recherche.getText().isEmpty()) {
            
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.initStyle(StageStyle.UNDECORATED);
            msg.setHeaderText("Champ vide");
            msg.setContentText("Veuillez renseigner le champ avant de passer à la recherche.");
            msg.show();
            
        }
        
        else {
            
            liste.getItems().clear();
            
            candidats = getRecherche();
            
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            noms_col.setCellValueFactory(new PropertyValueFactory<>("noms"));
            prenoms_col.setCellValueFactory(new PropertyValueFactory<>("prenoms"));

            liste.setItems(candidats);
            
            recherche.clear();

            candidatsDetails(null);

            liste.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> candidatsDetails(newValue));
            
        }
        
    }

    @FXML
    private void refreshExited(MouseEvent event) {
        
        URL imageURL = getClass().getResource("/images/refresh.png");
        Image fichier = new Image(imageURL.toExternalForm());
        refresh.setImage(fichier);
        
    }

    @FXML
    private void refreshEntered(MouseEvent event) {
        
        URL imageURL = getClass().getResource("/images/refresh1.png");
        Image fichier = new Image(imageURL.toExternalForm());
        refresh.setImage(fichier);
        
    }

    @FXML
    private void refresh(MouseEvent event) {
        
        liste.getItems().clear();
        remplir();
        
    }
    
}
