/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller.CoreScene;
import Core.Factory.ComponentFactory;
import Main.Main;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Muttabi Hudaya
 */
public class MainController extends CoreScene implements Initializable {
    @FXML
    private VBox sidebarMenuVbox;
    
    @FXML
    private VBox pageContainer;
    
    @FXML
    private Pane pageHeaderPane; //Width: 970, Height: 635
    @FXML
    private Label pageName, pageSubSection, nameLabel, loggedInAsLabel;
    
    @FXML
    private JFXButton exitBtn;
    @FXML
    private void onClickExitBtn(Event ev){
        Stage stage = (Stage) this.exitBtn.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void onLogoutBtnClick(Event ev){
        Main.setUserLoggedIn(false);
        super.changeScene("OnBoarding");
    }
    
    @FXML
    private void onSidebarMenuClick(ActionEvent ev){
    }
    
    public void setPageHeader(String pageName, String subSectionName){
        this.pageName.setText(pageName);
        this.pageSubSection.setText(subSectionName);
    }
    
    private void addSidebarMenu(String menuName, String viewName){
        this.sidebarMenuVbox.getChildren().add(ComponentFactory.createSidebarBtn("", menuName, viewName, this));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.setSceneName("Main");
        super.setContentPane(this.pageContainer);
        // TODO
        this.sidebarMenuVbox.getChildren().clear();
        
        
        //Init page contennt
        if(Main.anggota != null){
            this.addSidebarMenu("Dashboard", "DashboardAnggota");
            this.addSidebarMenu("Transaksi", "LihatDaftarTransaksi");
            this.addSidebarMenu("Pinjaman", "PengajuanPeminjaman");
            
        }else if(Main.pengurus != null){
            this.addSidebarMenu("Dashboard", "DashboardPengurus");
            this.addSidebarMenu("Lihat Transaksi", "LihatTransaksi");
            this.addSidebarMenu("Transaksi Simpanan", "TransaksiSimpan");
            this.addSidebarMenu("Transaksi Pinjaman", "TransaksiPinjam");
            this.addSidebarMenu("Transaksi Angsuran", "TransaksiAngsuran");
            this.addSidebarMenu("Transaksi Pengambilan", "TransaksiPengambilan");
            this.addSidebarMenu("Anggota", "LihatAnggota");
            this.addSidebarMenu("Pendaftar", "LihatPendaftar");
            this.addSidebarMenu("Transaksi Saya", "LihatDaftarTransaksi");
            this.addSidebarMenu("Pengajuan Pinjaman", "PengajuanPeminjaman");
//            this.addSidebarMenu("Nonaktifasi Anggota", "NonAktifasiAnggota");
        }
        
            this.getSidebarMenuButton("Dashboard").fire();
    }    
    
    //Getter and setter
    public JFXButton getSidebarMenuButton(String btnName){
        btnName = btnName.toLowerCase();
        btnName += "SidebarMenuBtn";
        for(Node component: this.sidebarMenuVbox.getChildren()){
            if(component instanceof JFXButton){
                System.out.println(component.getId());
                if(component.getId().equals(btnName)) return (JFXButton) component;
            }
        }
        
        return null;
    }
    
    public VBox getSidebarMenuVbox() {
        return sidebarMenuVbox;
    }

    public VBox getPageContainer() {
        return pageContainer;
    }

    public Pane getPageHeaderPane() {
        return pageHeaderPane;
    }

    @Override
    public void onShown() {
        if(Main.anggota != null){
            this.setLoggedInNameAnRole(Main.anggota.getNama(), Main.loginSebagai());
        }else if(Main.pengurus != null){
            this.setLoggedInNameAnRole(Main.pengurus.getNama(), Main.loginSebagai());
        }
    }
    
    private void setLoggedInNameAnRole(String name, String role){
        this.nameLabel.setText(name);
        this.loggedInAsLabel.setText("Anda masuk sebagai "+role);
    }

    @Override
    public void onBeforeShown() {
        super.setDraggedWindows();
    }
}
