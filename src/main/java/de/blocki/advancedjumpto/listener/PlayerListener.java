package de.blocki.advancedjumpto.listener;

import de.blocki.advancedjumpto.commands.JumptoCMD;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerListener implements Listener {

    @EventHandler
    public void onServerConnect(ServerConnectedEvent e) { JumptoCMD.mainCMD.add(e.getPlayer().getName()); }

    @EventHandler
    public void onServerDisconnect(ServerDisconnectEvent e) {
        JumptoCMD.mainCMD.remove(e.getPlayer().getName());
    }
}
