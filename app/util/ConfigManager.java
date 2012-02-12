/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;
import models.Configuration;
import play.db.jpa.GenericModel;
import play.test.Fixtures;

/**
 *
 * @author andreas
 */
public class ConfigManager {
   
    private static Configuration config;
    
    public static Configuration get() {
        if (config==null) {
            List<Configuration> configurations = Configuration.findAll();
            config = configurations.get(0);
            play.Logger.info("Loaded configuration: %s", config.toString());
        }
        return config;
    }
    
    public static void reload() {
        config = null;
        get();
    }
    
}
