/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller.CorePage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class AboutAppsController extends CorePage implements Initializable {

    @FXML
    private void onClickBackToLogin(Event ae){
        System.out.println("backing");
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
        //Nothing
    }
    
}
