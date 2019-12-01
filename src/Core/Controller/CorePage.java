/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Muttabi Hudaya
 */
public abstract class CorePage{
    private CoreScene parentController;
    
    public abstract void onShown();
    
    protected CoreScene getParentController(){
        return this.parentController;
    }
    
    public void setParentController(CoreScene parentController){
        this.parentController = parentController;
    }
}
