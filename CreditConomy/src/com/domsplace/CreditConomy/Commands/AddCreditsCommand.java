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
public class AddCreditsCommand extends BukkitCommand {
    public AddCreditsCommand() {
        super("addcredits");
        this.addSubCommandOption(new SubCommandOption(SubCommandOption.PLAYERS_OPTION, "amount"));
    }
    
    @Override
    public boolean cmd(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length < 1) {
            sendMessage(sender, ChatError + "Please enter a player name.");
            return true;
        }
        
        if(args.length < 2) {
            sendMessage(sender, ChatError + "");
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
        
        if(!isDouble(args[1])) {
            sendMessage(sender, ChatError + "Amount must be a number!");
            return true;
        }
        
        double amount = getDouble(args[1]);
        
        if(amount <= 0) {
            sendMessage(sender, ChatError + "Amount must be above 0.");
            return true;
        }
        
        CreditPlayer.setBalance(target.getName(), CreditPlayer.getBalance(target.getName()) + amount);
        String amt = CreditPlayer.format(amount);
        sendMessage(sender, ChatDefault + "Added " + ChatImportant + amt +
                ChatDefault + " to " + ChatImportant + getDisplayName(target) +
                "'s " + ChatDefault + "balance.");
        return true;
    }
}
