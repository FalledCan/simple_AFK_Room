package com.github.falledcan.simple_afk_room;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Timer {

    private FileConfiguration config = Simple_AFK_Room.getPlugin().getConfig();
    public void CountTimer(){

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {

                for(Player p: Bukkit.getServer().getOnlinePlayers()){

                    if(!AFKEvents.afkplayer.get(p)) {
                        int add = AFKEvents.playermovecount.get(p);
                        AFKEvents.playermovecount.put(p, add + 1);
                        if (AFKEvents.playermovecount.get(p) >= config.getInt("afktime")) {
                            p.setCollidable(false);
                            AFKEvents.afkloc.put(p,p.getLocation().getX()+","+p.getLocation().getY()+","+p.getLocation().getZ());
                            Location location = new Location(p.getWorld(), config.getDouble("afk-room.x"), config.getDouble("afk-room.y"), config.getDouble("afk-room.z"));
                            p.teleport(location);
                            AFKEvents.playermovecount.remove(p);
                            AFKEvents.afkplayer.put(p, true);
                        }
                    }else {
                        p.sendTitle("§c§l-*§6§lAFK-Room§c§l*-","§eMove to go back",5,10,5);
                    }

                }

            }
        };
        task.runTaskTimer(Simple_AFK_Room.getPlugin(),0L,20L);

    }

}
