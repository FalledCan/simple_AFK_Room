package com.github.falledcan.simple_afk_room;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import javax.security.auth.login.Configuration;

public final class Simple_AFK_Room extends JavaPlugin {


    private static Simple_AFK_Room plugin;
    public FileConfiguration cfg = null;
    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        saveDefaultConfig();
        getCommand("afkroom").setExecutor(new AFKCommand());
        Bukkit.getServer().getPluginManager().registerEvents(new AFKEvents(),this);
        Timer timer = new Timer();
        timer.CountTimer();

        for(Player p: Bukkit.getServer().getOnlinePlayers()) {
            AFKEvents.playermovecount.put(p,1);
            AFKEvents.afkplayer.put(p,false);
        }

    }

    public static Simple_AFK_Room getPlugin(){
        return plugin;
    }
}
