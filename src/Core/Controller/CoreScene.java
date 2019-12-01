/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Controller;

import Controller.OnBoardingController;
import Main.Main;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.StageStyle;

/**
 *
 * @author Muttabi Hudaya
 */
public abstract class CoreScene {
    private double offset_x;
    private double offset_y;
    private Scene scene;
    private String sceneName;
    private Pane contentPane;
    
    public void setContentPane(Pane contentPane){
        this.contentPane = contentPane;
    }
    
    public Pane getContentPane(){
        return this.contentPane;
    }
    
    public void setSceneName(String sceneName){
        this.sceneName = sceneName;
    }
    
    public void show(){
        this.changeScene(this.sceneName);
    }
    
    public void changeScene(String sceneName){
        try{
            System.out.println("/View/"+sceneName+".fxml");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/"+sceneName+".fxml"));
            Parent newSceneParent = loader.load();
            CoreScene targetScene = loader.getController();
            Main.mainScene = new Scene(newSceneParent);
            Main.mainStage.setScene(Main.mainScene);
            targetScene.onBeforeShown();
            Main.mainStage.show();
            targetScene.onShown();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void changePage(String viewName){
        System.out.println("/View/"+viewName+".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/"+viewName+".fxml"));
        try {
            Pane targetPane = fxmlLoader.load();
            CorePage targetController = (CorePage) fxmlLoader.getController();
            targetController.setParentController(this);
            this.contentPane.getChildren().setAll(targetPane);
            targetController.onShown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public abstract void onShown();
    public abstract void onBeforeShown();
    
    public void setWindowsInCenter(){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        Main.mainStage.setX((primScreenBounds.getWidth() - Main.mainStage.getWidth()) / 2);
        Main.mainStage.setY((primScreenBounds.getHeight() - Main.mainStage.getHeight()) / 2);
        
    }
    
    public void setDraggedWindows(){
        //Dragged windows
        Main.mainScene.setOnMousePressed(event -> {
            offset_x = event.getSceneX();
            offset_y = event.getSceneY();
        });
        
        Main.mainScene.setOnMouseDragged(event -> {
            Main.mainStage.setX(event.getScreenX() - offset_x);
            Main.mainStage.setY(event.getScreenY() - offset_y);
        });
    }
    
    public Scene getMainScene(){
        return scene;
    }
}
