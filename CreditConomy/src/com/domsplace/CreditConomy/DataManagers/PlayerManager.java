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

package com.domsplace.CreditConomy.DataManagers;

import com.domsplace.CreditConomy.Bases.DataManager;
import com.domsplace.CreditConomy.Enums.ManagerType;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author      Dominic
 * @since       11/10/2013
 */
public class PlayerManager extends DataManager {
    private YamlConfiguration yml;
    private File file;
    
    public PlayerManager() {
        super(ManagerType.PLAYER);
    }
    
    public YamlConfiguration getCFG() {
        return yml;
    }
    
    @Override
    public void tryLoad() throws IOException {
        this.file = new File(getDataFolder(), "store.yml");
        if(!this.file.exists()) file.createNewFile();
        this.yml = YamlConfiguration.loadConfiguration(file);
        
        /*** GENERATE DEFAULT CONFIG ***/
        
        //Save Data
        this.trySave();
    }
    
    @Override
    public void trySave() throws IOException {
        this.yml.save(file);
    }
}
