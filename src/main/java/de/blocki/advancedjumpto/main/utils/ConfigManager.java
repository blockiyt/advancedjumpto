package de.blocki.advancedjumpto.main.utils;

import de.blocki.advancedjumpto.main.Main;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private static File file = new File(Main.getInstance().getDataFolder() + "config.yml");
    private static Configuration cfg;

    static {
        if(!file.exists()) {
            try{
                file.createNewFile();
                cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            }catch (Exception ignored){ }
        }else{
            try { cfg = YamlConfiguration.getProvider(YamlConfiguration.class).load(file); } catch (IOException e) { e.printStackTrace(); }
        }

    }


    public static void set(String path, String value) {
        cfg.set(path, value);
    }

    public static String get(String path){
        return cfg.getString(path);
    }

    public static void save() {
        try { ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, file); }catch (IOException ignored){ }
    }
}
