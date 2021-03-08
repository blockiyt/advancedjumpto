package de.blocki.advancedjumpto.commands;

import de.blocki.advancedjumpto.main.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.TabExecutor;
import net.md_5.bungee.event.EventPriority;

import java.util.ArrayList;
import java.util.List;

public class jumptoCMD extends Command implements TabExecutor, Listener {
    public jumptoCMD(String name, String permission){ super(name, permission); }

    public static List<String> mainCMD = new ArrayList<String>();


    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if(args.length == 1) {
                ProxiedPlayer playertoget = ProxyServer.getInstance().getPlayer(args[0]);
                if(playertoget != null) {
                    if(!(playertoget.getUniqueId() == player.getUniqueId())) {
                        Server servertoget = playertoget.getServer();
                        if (player.getServer().getInfo().getName().equalsIgnoreCase(servertoget.getInfo().getName())) {
                            player.sendMessage(new ComponentBuilder(Main.prefix + Main.cfg.getString("MessageAlreadyConnectedToServer").replace("%SERVERNAME%", servertoget.getInfo().getName())).color(ChatColor.RED).create());
                        } else {
                            player.sendMessage(new ComponentBuilder(Main.prefix + Main.cfg.getString("MessageConnectingToServer").replace("%SERVERNAME%", servertoget.getInfo().getName())).create());
                            player.connect(servertoget.getInfo());
                        }
                    }else {
                        player.sendMessage(new ComponentBuilder(Main.prefix + Main.cfg.getString("MessageNoTeleportYourself")).create());
                    }
                }else {
                    //player not found
                    player.sendMessage(new ComponentBuilder(Main.prefix + Main.cfg.getString("MessagePlayerNotFound")).create());
                }
            }else {
                //kein player angegeben
                player.sendMessage(new ComponentBuilder(Main.prefix + Main.cfg.getString("MessageNoPlayer")).create());
            }
        }else {
            //is kein player
            System.out.println("You have to be an player!");
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {

        List<String> result = new ArrayList<String>();
        if(mainCMD.isEmpty()){
            for(ProxiedPlayer player : ProxyServer.getInstance().getPlayers()){
                mainCMD.add(player.getName());
            }
        }

        if(args.length == 1){
            for(String a : mainCMD){
                if(a.toLowerCase().startsWith(args[0].toLowerCase())){
                    result.add(a);
                }
            }
            return result;
        }
        return null;

        /*List<String> result = new ArrayList<String>();
        for(ProxiedPlayer player : ProxyServer.getInstance().getPlayers()){
            if(!mainCMD.contains(player.getName())) {
                mainCMD.add(player.getName());
            }
        }

        if(args.length == 1){
            for(String username : mainCMD){
                if(username.toLowerCase().startsWith(args[0].toLowerCase())){
                    result.add(username);
                }
            }
            return result;
        }
        return null;*/

    }


}
