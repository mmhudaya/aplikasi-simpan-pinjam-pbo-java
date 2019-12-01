/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller.CoreScene;
import Core.Interface.IPageView;
import Main.Main;
import com.jfoenix.controls.JFXSpinner;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class OnBoardingController extends CoreScene implements Initializable {
    
    @FXML
    private Label titleTest;
    
    @FXML
    private VBox contentVBox; //790 720
    
        /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.setSceneName("OnBoarding");
        super.setContentPane(this.contentVBox);
        super.changePage("Login");
    }    
    
    public VBox getContentVBox(){
        return contentVBox;
    }


    @Override
    public void onShown() {
        super.setWindowsInCenter();
    }

    @Override
    public void onBeforeShown() {
        super.setDraggedWindows();
    }
    
}
