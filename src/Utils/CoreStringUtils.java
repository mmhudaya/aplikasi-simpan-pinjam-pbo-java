/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.text.DecimalFormat;

/**
 *
 * @author Muttabi Hudaya
 */
public abstract class CoreStringUtils {
    public static String getAmountFormat(double amount){
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(amount);
    }
}
