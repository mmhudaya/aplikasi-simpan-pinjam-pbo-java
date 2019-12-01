/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Interface;

import javafx.scene.layout.Pane;

/**
 *
 * @author Muttabi Hudaya
 */
public interface IPageView {
    void showPage(Pane parentPane);
    void setPageHeader(String pageName, String pageSubSection);
}
