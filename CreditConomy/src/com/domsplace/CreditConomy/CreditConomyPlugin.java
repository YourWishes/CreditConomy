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

import com.domsplace.CreditConomy.Bases.DataManager;
import com.domsplace.CreditConomy.Bases.PluginHook;
import com.domsplace.CreditConomy.Bases.BukkitCommand;
import com.domsplace.CreditConomy.Bases.Base;
import com.domsplace.CreditConomy.Bases.DomsThread;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author      Dominic
 * @since       04/10/2013
 */
public class CreditConomyPlugin extends JavaPlugin {
    private boolean enabled = false;
    
    //Commands
    
    //Listeners
    
    //Threads
    
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
        
        //Load Listeners
        
        //Load Threads
        
        PluginHook.hookAll();
        
        this.enabled = true;
        Base.debug("Finished Loading " + this.getName() + ", " + BukkitCommand.getCommands().size() + " commands registered.");
    }
    
    @Override
    public void onDisable() {
        if(!enabled) {
            return;
        }
        
        DomsThread.stopAllThreads();
        DataManager.saveAll();
    }
    
    public void disable() {
        Bukkit.getPluginManager().disablePlugin(this);
    }
}
