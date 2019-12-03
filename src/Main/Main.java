/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.SplashScreenController;
import Model.Anggota;
import Model.Pengurus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.VBox;

/**
 *
 * @author Muttabi Hudaya
 */
public class Main extends Application {
    //Srage and scene
    public static Stage mainStage;
    public static Scene mainScene;
    
    //Storage
    public static Anggota anggota;
    public static Pengurus pengurus;
    private static boolean isLoggedIn = false;
    
    @Override
    public void start(Stage stage) throws Exception {
        Main.mainStage = stage;
        Main.mainStage.initStyle(StageStyle.UNDECORATED);
        SplashScreenController spc = new SplashScreenController();
        spc.setSceneName("SplashScreen");
        spc.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    //Storage
    public static void setPengurus(Pengurus pengurus){
        Main.pengurus = pengurus;
    }
    public static void setAnggota(Anggota anggota){
        Main.anggota = anggota;
    }
    
    public static String loginSebagai(){
        return Main.anggota != null ? "Anggota" : Main.pengurus != null ? "Pengurus" : "unidentified";
    }
    
    public static void setUserLoggedIn(boolean loggedIn){
        Main.isLoggedIn = true;
        if(!loggedIn){
            Main.pengurus = null;
            Main.anggota = null;
        }
    }
    
}
