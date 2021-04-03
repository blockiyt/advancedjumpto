package de.blocki.advancedjumpto.main;

import de.blocki.advancedjumpto.commands.JumptoCMD;
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

        // Handle configuration.
        if (!getDataFolder().exists()) { getDataFolder().mkdir(); }
        File config = new File(getDataFolder(),"config.yml");

        /*
         * Create configuration file if it does not exists; otherwise, load it
         */
        if (!config.exists()) {
            try {
                // First time run - do some initialization.
                LOG.info("Configuring JumpTo for the first time...");

                // Initialize the configuration file.
                config.createNewFile();
                cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config);
                cfg.set("Message.Prefix", "§7[§6JumpTo§7]");
                cfg.set("Message.PlayerNotFound", "The player you specified got not found!");
                cfg.set("Message.NoPlayer", "You did not specify a player!");
                cfg.set("Message.NoTeleportYourself", "You cannot jump to yourself!");
                cfg.set("Message.AlreadyConnectedToServer", "You are already connected to the Server %SERVERNAME%!");
                cfg.set("Message.ConnectingToServer", "You are connecting to the Server %SERVERNAME%!");
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, config);
                prefix = cfg.get("Message.Prefix", "§7[§6JumpTo§7]") + " ";

            } catch (Exception ex) {
                LOG.log(Level.SEVERE, "Error creating configuration file", ex);
                return;
            }
        } else {
            // Load configuration.
            try { cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config); } catch (IOException e) { e.printStackTrace(); }
            prefix = cfg.get("Message.Prefix", "§7[§6JumpTo§7]") + " ";
        }

        getProxy().getPluginManager().registerCommand(this, new JumptoCMD("jumpto", "jumpto.use"));
        getProxy().getPluginManager().registerListener(this, new PlayerListener());
    }

    public static Main getInstance() {
        return instance;
    }
}
