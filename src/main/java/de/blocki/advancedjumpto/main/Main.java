package de.blocki.advancedjumpto.main;

import de.blocki.advancedjumpto.commands.jumptoCMD;
import de.blocki.advancedjumpto.listener.PlayerListener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Main extends Plugin {

    private static final Logger LOG = Logger.getLogger("JumpTO");

    public static String prefix;

    private static Main instance;

    public static Configuration cfg = null;

    @Override
    public void onEnable() {
        //instance the Class
        Main.instance = this;

        //register events and commands
        getProxy().getPluginManager().registerCommand(this, new jumptoCMD("jumpto", "jumpto.use"));
        getProxy().getPluginManager().registerListener(this, new PlayerListener());


        // Handle configuration.
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File config = new File(getDataFolder(),"config.yml");

        /*
         * Create configuration file if it does not exists; otherwise, load it
         */
        if (!config.exists()) {
            try {
                // First time run - do some initialization.
                LOG.info("Configuring JumpTO module for the first time...");

                // Initialize the configuration file.
                config.createNewFile();
                cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config);

                cfg.set("MessagePrefix", "§7[§6JumpTo§7]");
                cfg.set("MessagePlayerNotFound", "The player you specified got not found!");
                cfg.set("MessageNoPlayer", "You did not specify a player!");
                cfg.set("MessageNoTeleportYourself", "You cannot jump to yourself!");
                cfg.set("MessageAlreadyConnectedToServer", "You are already connected to the Server %SERVERNAME%!");
                cfg.set("MessageConnectingToServer", "You are connecting to the Server %SERVERNAME%!");
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, config);
                prefix = cfg.get("MessagePrefix", "§7[§6JumpTo§7]") + " ";

            } catch (Exception ex) {
                LOG.log(Level.SEVERE, "Error creating configuration file", ex);
                return;
            }
        } else {
            // Load configuration.
            try { cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config); } catch (IOException e) { e.printStackTrace(); }
        }
    }

    public static Main getInstance() {
        return instance;
    }
}
