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
public class PayCommand extends BukkitCommand {
    public PayCommand() {
        super("pay");
        this.addSubCommandOption(new SubCommandOption(SubCommandOption.PLAYERS_OPTION, "amount"));
    }
    
    @Override
    public boolean cmd(CommandSender sender, Command cmd, String label, String[] args) {
        if(!isPlayer(sender)) {
            sendMessage(sender, ChatError + "Only players can do this.");
            return true;
        }
        
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
        
        if(target.getName().equalsIgnoreCase(sender.getName())) {
            sendMessage(sender, ChatError + "You can't send money to yourself.");
            return true;
        }
        
        if(!CreditPlayer.has(sender.getName(), amount)) {
            sendMessage(sender, ChatError + "You don't have enough Credits.");
            return true;
        }
        
        CreditPlayer.setBalance(target.getName(), CreditPlayer.getBalance(target.getName()) + amount);
        CreditPlayer.setBalance(sender.getName(), CreditPlayer.getBalance(sender.getName()) - amount);
        String amt = CreditPlayer.format(amount);
        
        sendMessage(sender, "Sent " + ChatImportant + amt + ChatDefault + " to " + ChatImportant  + getDisplayName(target));
        sendMessage(target, "Recieved " + ChatImportant + amt + ChatDefault + " from " + ChatImportant + getDisplayName(Bukkit.getOfflinePlayer(sender.getName())));
        return true;
    }
}
