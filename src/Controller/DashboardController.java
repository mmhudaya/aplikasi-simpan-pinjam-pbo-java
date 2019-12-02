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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class DashboardController extends CorePage implements Initializable {
    @Override
    public void onShown() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @Override
    public void onChangeAnyFormValue() {
        //No Form.
    }
}
