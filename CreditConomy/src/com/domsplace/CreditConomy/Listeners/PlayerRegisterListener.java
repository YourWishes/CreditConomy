/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.domsplace.CreditConomy.Listeners;

import com.domsplace.CreditConomy.Bases.DomsListener;
import com.domsplace.CreditConomy.Objects.CreditPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author Dominic Masters
 */
public class PlayerRegisterListener extends DomsListener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        CreditPlayer.setBalance(e.getPlayer().getName(), CreditPlayer.getBalance(e.getPlayer().getName()));
    }
}
