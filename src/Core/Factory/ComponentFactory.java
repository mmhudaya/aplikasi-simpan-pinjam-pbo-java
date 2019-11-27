/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Factory;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Muttabi Hudaya
 */
public abstract class ComponentFactory {
    
    public static JFXButton createSidebarBtn(String icon, String menuName){
        JFXButton sidebarMenuBtn = new JFXButton(menuName);
        sidebarMenuBtn.setPrefSize(239, 64);
        sidebarMenuBtn.graphicTextGapProperty().set(32);
        sidebarMenuBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        sidebarMenuBtn.setStyle("-fx-text-fill: #abafb2");
        sidebarMenuBtn.setAlignment(Pos.BASELINE_LEFT);
        
        return sidebarMenuBtn;
    }
}
