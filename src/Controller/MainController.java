/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Factory.ComponentFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Muttabi Hudaya
 */
public class MainController implements Initializable {
    @FXML
    private VBox sidebarMenuVbox;
    
    @FXML
    private void onSidebarMenuClick(ActionEvent ev){
        this.addSidebarMenu("NOT Real Menu");
    }
    
    private void addSidebarMenu(String menuName){
        this.sidebarMenuVbox.getChildren().add(ComponentFactory.createSidebarBtn("", menuName));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        this.sidebarMenuVbox.getChildren().clear();
    }    
    
}
