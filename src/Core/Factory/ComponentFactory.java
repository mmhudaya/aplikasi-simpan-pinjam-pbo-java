/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Factory;

import Core.Controller.CoreScene;
import Core.Interface.IPageView;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Muttabi Hudaya
 */
public abstract class ComponentFactory {
    
    public static JFXButton createSidebarBtn(String icon, String menuName, String viewName, CoreScene currentScene){
        JFXButton sidebarMenuBtn = new JFXButton(menuName);
        menuName = menuName.replaceAll("\\s+","");
        sidebarMenuBtn.setId(menuName.toLowerCase()+"SidebarMenuBtn");
        sidebarMenuBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                currentScene.changePage(viewName);
            }
        });
        sidebarMenuBtn.setPrefSize(239, 40);
        sidebarMenuBtn.graphicTextGapProperty().set(32);
        sidebarMenuBtn.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        String css = ComponentFactory.class.getResource("/ViewStyle/generic.css").toExternalForm();
        sidebarMenuBtn.getStylesheets().add(css);
//        sidebarMenuBtn.setStyle("-fx-text-fill: #dad8d8");
        sidebarMenuBtn.getStyleClass().add("sidebar-menu-btn");
        sidebarMenuBtn.setAlignment(Pos.BASELINE_LEFT);
        
        return sidebarMenuBtn;
    }
}
