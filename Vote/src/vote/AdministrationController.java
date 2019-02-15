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
import metiers.Liste;
import metiers.Recherche;
import metiers.Suppression;
import modeles.Personnes;

/**
 * FXML Controller class
 *
 * @author Germinator-PC
 */
public class AdministrationController implements Initializable {

    
    private ObservableList<Personnes> personnes = FXCollections.observableArrayList();
    
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
    private Button nouveau;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private Label identifiant;
    @FXML
    private ImageView photo;
    @FXML
    private ImageView resultats;
    @FXML
    private ImageView admin;
    @FXML
    private ImageView help;
    @FXML
    private TextField recherche;
    @FXML
    private Button rechercher;
    @FXML
    private ImageView refresh;
    
    public AdministrationController() {
        
    }
    
    public ObservableList<Personnes> getPersonnes() {
        
        Liste personnesListe = new Liste();
        List<Personnes> listePersonnes = personnesListe.liste();
        
        listePersonnes.forEach(
            (personne) -> {
                personnes.add(
                    new Personnes(
                        personne.getId(),
                        personne.getNoms(),
                        personne.getPrenoms(),
                        personne.getSexe(),
                        personne.getEmail(),
                        personne.getCni(),
                        personne.getContact()
                    )
                );
            }
        );
        
        return personnes;
        
    }
    
    public ObservableList<Personnes> getRecherche() {
        
        Recherche personnesListe = new Recherche();
        List<Personnes> listePersonnes = personnesListe.personnes(recherche.getText());
        
        listePersonnes.forEach(
            (personne) -> {
                personnes.add(
                    new Personnes(
                        personne.getId(),
                        personne.getNoms(),
                        personne.getPrenoms(),
                        personne.getSexe(),
                        personne.getEmail(),
                        personne.getCni(),
                        personne.getContact()
                    )
                );
            }
        );
        
        return personnes;
        
    }
    
    private void personnesDetails(Personnes personne) {
        
        if (personne != null) {
            
            photo.setVisible(true);
            Liste image = new Liste();
            String name = image.photos(personne.getId());
            
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
            
            noms.setText(personne.getNoms());
            prenoms.setText(personne.getPrenoms());
            sexe.setText(personne.getSexe());
            email.setText(personne.getEmail());
            cni.setText(personne.getCni());
            contact.setText(personne.getContact());
            supprimer.setDisable(false);
            modifier.setDisable(false);
            identifiant.setText(String.valueOf(personne.getId()));
            
        }
        
        else {
            
            photo.setVisible(false);
            noms.setText("");
            prenoms.setText("");
            sexe.setText("");
            email.setText("");
            cni.setText("");
            contact.setText("");
            supprimer.setDisable(true);
            modifier.setDisable(true);
            
        }
        
    }

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
        
        personnes = getPersonnes();
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        noms_col.setCellValueFactory(new PropertyValueFactory<>("noms"));
        prenoms_col.setCellValueFactory(new PropertyValueFactory<>("prenoms"));
        
        liste.setItems(personnes);
        
        personnesDetails(null);
        
        liste.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> personnesDetails(newValue));
        
    }

    @FXML
    private void aide(MouseEvent event) throws IOException {
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
    private void nouveau(ActionEvent event) throws IOException {

        Parent inscription = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
        Scene inscription_scene = new Scene(inscription);
        Stage inscription_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        inscription_stage.hide();
        inscription_stage.setScene(inscription_scene);
        inscription_stage.show();
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        Suppression del = new Suppression();
        del.personnes(Integer.parseInt(identifiant.getText()));

        int selectedIndex = liste.getSelectionModel().getSelectedIndex();
        liste.getItems().remove(selectedIndex);
        
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Modifications.fxml"));
        loader.load();
        
        ModificationsController controller = loader.getController();
        controller.initData(new Personnes(Integer.parseInt(identifiant.getText()), noms.getText(), prenoms.getText(), sexe.getText(), email.getText(), cni.getText(), contact.getText()));
        
        Parent modifications = loader.getRoot();
        Scene modifications_scene = new Scene(modifications);
        Stage modifications_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        modifications_stage.hide();
        modifications_stage.setScene(modifications_scene);
        modifications_stage.show();
        
    }

    @FXML
    private void parametres(MouseEvent event) throws IOException {
        
        Parent parametre = FXMLLoader.load(getClass().getResource("Modification.fxml"));
        Scene parametre_scene = new Scene(parametre);
        Stage parametre_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        parametre_stage.hide();
        parametre_stage.setScene(parametre_scene);
        parametre_stage.show();
        
    }

    @FXML
    private void resultats(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Resultats.fxml"));
        loader.load();
        
        ResultatsController controller = loader.getController();
        controller.initData();
        
        Parent resultat = loader.getRoot();
        Scene resultat_scene = new Scene(resultat);
        Stage resultat_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        resultat_stage.hide();
        resultat_stage.setScene(resultat_scene);
        resultat_stage.show();
        
    }

    @FXML
    private void resultatsExited(MouseEvent event) {
        
        URL imageURL = getClass().getResource("/images/result.png");
        Image fichier = new Image(imageURL.toExternalForm());
        resultats.setImage(fichier);
        
    }

    @FXML
    private void resultatsEntered(MouseEvent event) {
        
        URL imageURL = getClass().getResource("/images/result1.png");
        Image fichier = new Image(imageURL.toExternalForm());
        resultats.setImage(fichier);
        
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

            personnes = getRecherche();
            
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            noms_col.setCellValueFactory(new PropertyValueFactory<>("noms"));
            prenoms_col.setCellValueFactory(new PropertyValueFactory<>("prenoms"));

            liste.setItems(personnes);
            
            recherche.clear();

            personnesDetails(null);

            liste.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> personnesDetails(newValue));
            
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
