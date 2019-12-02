/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Pengurus;

import Controller.MainController;
import Core.Controller.CorePage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class TransaksiPinjamController extends CorePage implements Initializable {

    MainController mc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setPageHeader() {
        mc.setPageHeader("Transaksi Pinjaman", "Form Transaksi Pinjaman");
    }

    @Override
    public void onShown() {
        this.mc = (MainController) super.getParentController();
        this.setPageHeader();
    }

    
    
    @Override
    public void onChangeAnyFormValue() {
        //TODO
    }
}
