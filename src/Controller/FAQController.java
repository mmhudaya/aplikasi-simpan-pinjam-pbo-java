/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller.CorePage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class FAQController extends CorePage implements Initializable {
    Pane parentPane; 
    
    @FXML
    private void onClickBackToLogin(Event ae){
        super.getParentController().changePage("Login");
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @Override
    public void onShown() {
        //Nothing
    }
}
