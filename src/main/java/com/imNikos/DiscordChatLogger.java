package com.imNikos;

import org.bukkit.plugin.java.JavaPlugin;

public class DiscordChatLogger extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ServerEventsListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}