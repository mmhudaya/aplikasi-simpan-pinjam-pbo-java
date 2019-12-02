/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller.CorePage;
import Core.Interface.IPageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class CreditsController extends CorePage implements Initializable {
    
    @FXML
    private Label backLabel;
    
    @FXML
    private void onClickBackToLogin(Event ev){
        super.getParentController().changePage("Login");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void onShown() {
        //nothing
    }
    
    @Override
    public void onChangeAnyFormValue() {
        //No Form.
    }
}
