package de.blocki.advancedjumpto.main;

import de.blocki.advancedjumpto.commands.jumptoCMD;
import de.blocki.advancedjumpto.listener.listener;
import de.blocki.advancedjumpto.main.utils.ConfigManager;
import net.md_5.bungee.api.plugin.Plugin;

public final class Main extends Plugin {

    public static String prefix = "";

    private static Main instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Main.instance = this;

        getProxy().getPluginManager().registerCommand(this, new jumptoCMD("jumpto", "jumpto.use"));
        getProxy().getPluginManager().registerListener(this, new listener());

        setDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }

    private void setDefaultConfig(){
        if(ConfigManager.get("MessagePrefix") == null){ ConfigManager.set("MessagePrefix", "§7[§6Rank§7]"); }
        if(ConfigManager.get("MessagePlayerNotFound") == null){ ConfigManager.set("MessagePlayerNotFound", "The player you specified got not found!"); }
        if(ConfigManager.get("MessageNoPlayer") == null){ ConfigManager.set("MessageNoPlayer", "You did not specify an Player!"); }
        if(ConfigManager.get("MessageNoTeleportYourself") == null){ ConfigManager.set("MessageNoTeleportYourself", "You cannot jump to yourself!"); }
        if(ConfigManager.get("MessageAlreadyConnectedToServer") == null){ ConfigManager.set("MessageAlreadyConnectedToServer", "You are already connected to the Server %SERVERNAME%!"); }
        if(ConfigManager.get("MessageConnectingToServer") == null){ ConfigManager.set("MessageConnectingToServer", "You are connecting to the Server %SERVERNAME%!"); }
        ConfigManager.save();
        prefix = ConfigManager.get("MessagePrefix") + " ";
    }
}
