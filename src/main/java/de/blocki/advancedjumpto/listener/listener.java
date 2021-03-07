package de.blocki.advancedjumpto.listener;

import de.blocki.advancedjumpto.commands.jumptoCMD;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class listener implements Listener {

    @EventHandler
    public void onServerConnect(ServerConnectedEvent e) { jumptoCMD.mainCMD.add(e.getPlayer().getName()); }

    @EventHandler
    public void onServerDisconnect(ServerDisconnectEvent e) {
        jumptoCMD.mainCMD.remove(e.getPlayer().getName());
    }
}
