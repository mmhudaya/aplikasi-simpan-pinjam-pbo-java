/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller.CoreScene;
import Utils.Database;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import javafx.util.Duration;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class SplashScreenController extends CoreScene implements Initializable {
    

    @FXML
    private AnchorPane mainPane;
    
    @FXML
    private Label progressText;
    
    private int seconds = 4;
    
    private int countdownText = 3;
    private final String SET_BUTTON_TEXT = "Creating Buttons...";
    private final String SET_INPUT_TEXT = "Creating Text Field...";
    private final String FINISHING_TEXT = "Finishing...";
    private final String WELCOME_TEXT = "Welcome :)";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.setSceneName("SplashScreen");
    }    
    
    public void checkApp(){
        this.checkDatabaseConnection();
    }
    
    private void checkDatabaseConnection(){
        Database db = new Database();
        try{
            db.connect();
            this.progressText.setText("Initiate Graphical Interfaces..");
            this.initApp();
        }catch(SQLException ex){
            recheckDatabase();
        }
    }
    
    private void recheckDatabase(){
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event ->{
            this.retryConnect();
            pause.play();
            if(seconds <= 0){
                pause.stop();
            }
        });
        pause.play();
    }
    
    private void retryConnect(){
        this.progressText.setText("Retring connect to database in "+seconds+" seconds.");
        seconds--;
        if(seconds == 0){
            seconds = 30;
            this.checkDatabaseConnection();
        }
    }
    
    private void initApp(){
        PauseTransition pause = new PauseTransition(Duration.seconds(new Random().nextDouble() * 0.5 + (2 - 0.5)));
        pause.setOnFinished(event ->{
            this.changeInitProgressText();
            pause.play();
            if(countdownText <= 0){
                pause.stop();
                this.changeInitProgressText();
                changeScene();
            }
        });
        pause.play();
    }
    
    private void changeInitProgressText(){
        String text = "";
        System.out.println(countdownText);
        switch(countdownText){
            case 1: text = FINISHING_TEXT; break;
            case 2: text = SET_INPUT_TEXT; break;
            case 3: text = SET_BUTTON_TEXT; break;
            default: text = WELCOME_TEXT; break;
        }
        this.progressText.setText(text);
        countdownText--;
    }
    
    private void changeScene(){
        super.changeScene("OnBoarding");
    }

    @Override
    public void onShown() {
        super.setWindowsInCenter();
        this.checkApp();
    }

    @Override
    public void onBeforeShown() {
        super.setDraggedWindows();
    }
}
