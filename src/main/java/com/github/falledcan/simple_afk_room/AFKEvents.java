package com.github.falledcan.simple_afk_room;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class AFKEvents implements Listener {

    public static HashMap<Player, Integer> playermovecount = new HashMap<>();
    public static HashMap<Player,Boolean> afkplayer = new HashMap<>();
    public static HashMap<Player, String> afkloc = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        playermovecount.put(e.getPlayer(),1);
        afkplayer.put(e.getPlayer(),false);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        playermovecount.remove(e.getPlayer());
        afkplayer.remove(e.getPlayer());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){

        if(afkplayer.get(e.getPlayer())){
            String[] loc = afkloc.get(e.getPlayer()).split(",");
            Location location = new Location(e.getPlayer().getWorld(), Double.parseDouble(loc[0]), Double.parseDouble(loc[1]), Double.parseDouble(loc[2]));
            e.getPlayer().teleport(location);
            afkloc.remove(e.getPlayer());
        }
        playermovecount.put(e.getPlayer(),1);
        afkplayer.put(e.getPlayer(),false);
        e.getPlayer().setCollidable(true);

    }

}
