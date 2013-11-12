/*
 * Copyright 2013 Dominic.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.domsplace.CreditConomy;

import com.domsplace.CreditConomy.Bases.*;
import com.domsplace.CreditConomy.Commands.*;
import com.domsplace.CreditConomy.Listeners.*;
import com.domsplace.CreditConomy.Objects.*;
import com.domsplace.CreditConomy.Threads.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author      Dominic
 * @since       04/10/2013
 */
public class CreditConomyPlugin extends JavaPlugin {
    private boolean enabled = false;
    
    //Commands
    private BalanceCommand balanceCommand;
    private AddCreditsCommand addCreditsCommand;
    private PayCommand payCommand;
    
    //Listeners
    private PlayerRegisterListener playerListener;
    
    //Threads
    private ConfigSaveThread configSaveThread;
    
    @Override
    public void onEnable() {
        //Register Plugin
        Base.setPlugin(this);
        
        //Load Data
        if(!DataManager.loadAll()) {
            this.disable();
            return;
        }
        
        //Load Commands
        this.balanceCommand = new BalanceCommand();
        this.addCreditsCommand = new AddCreditsCommand();
        this.payCommand = new PayCommand();
        
        //Load Listeners
        this.playerListener = new PlayerRegisterListener();
        
        //Load Threads
        this.configSaveThread = new ConfigSaveThread();
        
        PluginHook.hookAll();
        
        //Add this to Vault
        if(PluginHook.VAULT_HOOK.isHooked()) {
            try {
                Economy ex = CreditConomyEconomy.class.getConstructor(Plugin.class).newInstance(PluginHook.VAULT_HOOK.getHookedPlugin());
                ServicesManager sm = Bukkit.getServicesManager();
                sm.register(Economy.class, ex, this, ServicePriority.Normal);
            } catch(Exception e) {
                Base.debug(e);
            } catch(Error e) {
                Base.debug(e);
            }
        }
        
        this.enabled = true;
        Base.debug("Finished Loading " + this.getName() + ", " + BukkitCommand.getCommands().size() + " commands registered.");
    }
    
    @Override
    public void onDisable() {
        if(!enabled) {
            return;
        }
        
        //Unhook Economy
        try {
            CreditConomyEconomy.instance = null;
        } catch(Exception e) {} catch(Error e) {}
        
        DomsThread.stopAllThreads();
        DataManager.saveAll();
    }
    
    public void disable() {
        Bukkit.getPluginManager().disablePlugin(this);
    }
}
