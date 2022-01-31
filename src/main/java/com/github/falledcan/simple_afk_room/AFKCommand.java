package com.github.falledcan.simple_afk_room;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class AFKCommand implements CommandExecutor {

    private final FileConfiguration config = Simple_AFK_Room.getPlugin().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            Player p = ((Player) sender).getPlayer();
            if(p.hasPermission("afkroom.admin")){

                if(args.length == 0){
                    p.sendMessage("§a[afkroom] /afkroom set");
                    p.sendMessage("§a[afkroom] /afkroom tp");
                    p.sendMessage("§a[afkroom] /afkroom settime (sec)");
                }else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("set")){

                        config.set("afk-room.x", p.getLocation().getBlockX() + 0.5);
                        config.set("afk-room.y", p.getLocation().getBlockY());
                        config.set("afk-room.z", p.getLocation().getBlockZ() + 0.5);
                        Simple_AFK_Room.getPlugin().saveConfig();
                        p.sendMessage("§a[afkroom] set afkroom - " + p.getLocation().getBlockX() + "," + p.getLocation().getBlockY() + "," + p.getLocation().getBlockZ() );

                    }else if(args[0].equalsIgnoreCase("tp")){

                        Location location = new Location(p.getWorld(), config.getDouble("afk-room.x"), config.getDouble("afk-room.y"), config.getDouble("afk-room.z"));
                        p.  teleport(location);

                    }else if(args[0].equalsIgnoreCase("time")){
                        p.sendMessage("§a[afkroom] /afkroom settime (sec)");
                    }else {
                        p.sendMessage("§a[afkroom] /afkroom set");
                        p.sendMessage("§a[afkroom] /afkroom tp");
                        p.sendMessage("§a[afkroom] /afkroom time (sec)");
                    }
                } else if(args.length == 2){
                    if(args[0].equalsIgnoreCase("time")) {
                            int time = Integer.parseInt(args[1]);
                            config.set("afktime", time);
                            Simple_AFK_Room.getPlugin().saveConfig();
                            p.sendMessage("§a[afkroom] set time " + time + "sec.");
                    }else {
                        p.sendMessage("§a[afkroom] /afkroom set");
                        p.sendMessage("§a[afkroom] /afkroom tp");
                        p.sendMessage("§a[afkroom] /afkroom time (sec)");
                    }
                }
        }else {
                p.sendMessage("§a[afkroom] §cFor admin only");
            }

        }else {
            sender.sendMessage(ChatColor.RED +"Command can be executed from the player");
        }

        return true;
    }
}
