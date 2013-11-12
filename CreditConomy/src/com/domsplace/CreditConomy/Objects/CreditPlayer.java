/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.domsplace.CreditConomy.Objects;

import com.domsplace.CreditConomy.Bases.Base;
import com.domsplace.CreditConomy.Bases.DataManager;
import com.domsplace.CreditConomy.DataManagers.PlayerManager;

/**
 *
 * @author Dominic Masters
 */
public class CreditPlayer {
    public static String singular = "Credit";
    public static String plural = "Credits";
    
    public static PlayerManager getPlayerManager() {
        return DataManager.PLAYER_MANAGER;
    }
    
    public static double getBalance(String player) {
        return getPlayerManager().getCFG().getDouble(player.toLowerCase(), 
                Base.getConfig().getDouble("money.startingbalance", 0.0d));
    }
    
    public static void setBalance(String player, double amt) {
        getPlayerManager().getCFG().set(player, amt);
        getPlayerManager().save();
    }
    
    public static boolean has(String player, double amt) {
        return getBalance(player) >= amt;
    }
    
    public static String format(double amt) {
        return Base.twoDecimalPlaces(amt) + " " + (amt == 1 ? singular : plural);
    }
}
