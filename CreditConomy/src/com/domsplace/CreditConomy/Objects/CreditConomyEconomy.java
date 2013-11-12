/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.domsplace.CreditConomy.Objects;

import com.domsplace.CreditConomy.Bases.Base;
import java.util.List;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Dominic Masters
 */
public class CreditConomyEconomy implements Economy {
    public static CreditConomyEconomy instance;
    
    private Plugin vault;
    
    public CreditConomyEconomy(Plugin vault) {
        this.vault = vault;
        instance = this;
    }
    
    @Override public boolean isEnabled() {return Base.getPlugin().isEnabled();}

    @Override public String getName() {return Base.getPlugin().getName();}

    @Override public boolean hasBankSupport() {return false;}

    @Override
    public int fractionalDigits() {return 3;}

    @Override
    public String format(double d) {
        return CreditPlayer.format(d);
    }

    @Override
    public String currencyNamePlural() {
        return CreditPlayer.plural;
    }

    @Override
    public String currencyNameSingular() {
        return CreditPlayer.singular;
    }

    @Override
    public boolean hasAccount(String string) {
        return Base.getConfig().contains(string.toLowerCase());
    }

    @Override
    public boolean hasAccount(String string, String string1) {
        return hasAccount(string.toLowerCase());
    }

    @Override
    public double getBalance(String string) {
        return CreditPlayer.getBalance(string);
    }

    @Override
    public double getBalance(String string, String string1) {
        return getBalance(string);
    }

    @Override
    public boolean has(String string, double d) {
        return CreditPlayer.has(string, d);
    }

    @Override
    public boolean has(String string, String string1, double d) {
        return has(string, d);
    }

    @Override
    public EconomyResponse withdrawPlayer(String string, double d) {
        if(d < 0) return new EconomyResponse(0, 0, ResponseType.FAILURE, "Cannot withdraw negative funs.");
        if(!hasAccount(string)) return new EconomyResponse(0, 0, ResponseType.FAILURE, "That player does not have an account!");
        if(!has(string, d)) return new EconomyResponse(0, getBalance(string), ResponseType.FAILURE, "Insufficient funds");
        CreditPlayer.setBalance(string, CreditPlayer.getBalance(string) - d);
        return new EconomyResponse(d, getBalance(string), ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse withdrawPlayer(String string, String string1, double d) {
        return withdrawPlayer(string, d);
    }

    @Override
    public EconomyResponse depositPlayer(String string, double d) {
        if(d < 0) return new EconomyResponse(0, 0, ResponseType.FAILURE, "Cannot deposit negative funs.");
        if(!hasAccount(string)) return new EconomyResponse(0, 0, ResponseType.FAILURE, "That player does not have an account!");
        CreditPlayer.setBalance(string, CreditPlayer.getBalance(string) + d);
        return new EconomyResponse(d, getBalance(string), ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse depositPlayer(String string, String string1, double d) {
        return depositPlayer(string, d);
    }

    @Override
    public EconomyResponse createBank(String string, String string1) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    @Override
    public EconomyResponse deleteBank(String string) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    @Override
    public EconomyResponse bankBalance(String string) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    @Override
    public EconomyResponse bankHas(String string, double d) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    @Override
    public EconomyResponse bankWithdraw(String string, double d) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    @Override
    public EconomyResponse bankDeposit(String string, double d) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    @Override
    public EconomyResponse isBankOwner(String string, String string1) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    @Override
    public EconomyResponse isBankMember(String string, String string1) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String string) {
        CreditPlayer.setBalance(string, CreditPlayer.getBalance(string));
        return true;
    }

    @Override
    public boolean createPlayerAccount(String string, String string1) {
        return createPlayerAccount(string);
    }
}
