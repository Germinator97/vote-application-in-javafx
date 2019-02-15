/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vote;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Germinator-PC
 */
public class SplashController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private Circle cercle1;
    @FXML
    private Circle cercle2;
    @FXML
    private Circle cercle3;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        new SplashScreen().start();
        
        rotation(cercle1, 360, 10);
        rotation(cercle2, 360, 20);
        rotation(cercle3, 360, 30);
        
    }    
    
    class SplashScreen extends Thread {
        
        @Override
        public void run() {
            
            try {
                
                Thread.sleep(5000);
                
                Platform.runLater(
                    () -> {
                        
                        Parent root = null;
                        
                        try {
                            root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
                        }
                        
                        catch (IOException ex) {
                            Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.UNDECORATED);

                        stage.setScene(scene);
                        stage.show();
                        rootPane.getScene().getWindow().hide();
                        
                    }
                );
                
            }
            
            catch (InterruptedException ex) {
                Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    public void rotation(Circle cercle, int angle, int duree) {
        
        RotateTransition rotation = new RotateTransition(Duration.seconds(duree), cercle);
        rotation.setAutoReverse(true);
        rotation.setByAngle(angle);
        rotation.setDelay(Duration.seconds(0));
        rotation.setRate(3);
        rotation.setCycleCount(18);
        rotation.play();
        
    }
    
}
