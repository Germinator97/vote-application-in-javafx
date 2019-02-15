/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vote;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import metiers.Liste;

/**
 * FXML Controller class
 *
 * @author Germinator-PC
 */
public class ResultatsController implements Initializable {

    @FXML
    private ImageView help;
    @FXML
    private ImageView exit;
    @FXML
    private Label identifiant;
    @FXML
    private BarChart<String, Integer> resultats;
    @FXML
    private NumberAxis ordonnee;
    @FXML
    private CategoryAxis axe;
    
    private final ObservableList<String> listeCandidats = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Liste liste = new Liste();
        String[] candidats = liste.nomCandidats();
        
        listeCandidats.addAll(Arrays.asList(candidats));
        axe.setCategories(listeCandidats);
        
    }
    
    public void initData() {
        
        Liste liste = new Liste();
        int[] electeurs = liste.electeurs();
        
        XYChart.Series<String, Integer> barres = new XYChart.Series<>();
        Calendar calendrier = Calendar.getInstance();
        
        int annee = calendrier.get(Calendar.YEAR);
        barres.setName("Nombre de personnes ayant votées à l'élection " + Integer.toString(annee));
        
        for(int i = 0; i < electeurs.length; i++) {
            barres.getData().add(new XYChart.Data<>(listeCandidats.get(i), electeurs[i]));
        }
        
        resultats.getData().add(barres);
        
    }

    @FXML
    private void aide(MouseEvent event) throws IOException {
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
    
}
