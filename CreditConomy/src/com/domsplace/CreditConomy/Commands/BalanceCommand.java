/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.domsplace.CreditConomy.Commands;

import com.domsplace.CreditConomy.Bases.Base;
import com.domsplace.CreditConomy.Bases.BukkitCommand;
import com.domsplace.CreditConomy.Objects.CreditPlayer;
import com.domsplace.CreditConomy.Objects.SubCommandOption;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Dominic Masters
 */
public class BalanceCommand extends BukkitCommand {
    public BalanceCommand() {
        super("balance");
        this.addSubCommandOption(SubCommandOption.PLAYERS_OPTION);
    }
    
    @Override
    public boolean cmd(CommandSender sender, Command cmd, String label, String[] args) {
        if(!isPlayer(sender) && args.length < 1) {
            sendMessage(sender, ChatError + "Please enter a player name.");
            return true;
        }
        
        OfflinePlayer target = null;
        if(args.length > 0) {
            target = Base.getOfflinePlayer(sender, args[0]);
        } else {
            target = Bukkit.getOfflinePlayer(sender.getName());
        }
        
        if(target == null) {
            sendMessage(sender, ChatError + "Couldn't find player.");
            return true;
        }
        
        if(!target.getName().equalsIgnoreCase(sender.getName()) && !hasPermission(sender, "CreditConomy.balance.others")) {
            sendMessage(sender, ChatError + "You don't have permission to view others' balance.");
            return true;
        }
        
        String amt = CreditPlayer.format(CreditPlayer.getBalance(target.getName()));
        
        sendMessage(sender, ChatImportant + "Balance: " + ChatDefault + amt);
        return true;
    }
}
