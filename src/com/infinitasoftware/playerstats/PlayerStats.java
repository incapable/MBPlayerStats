package com.infinitasoftware.playerstats;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PostPlayerLoginEvent;
import com.mbserver.api.events.WorldSaveEvent;
import com.mbserver.api.events.RunMode;

@Manifest( name = "PlayerStats", config = Stats.class )// Using config storage because it's easy and we don't need no config =D

/**
 * PlayerStats plugin class
 * @author incapable
 *
 */
public class PlayerStats extends MBServerPlugin {
    private Stats stats;

    /**
     * Constructor
     */
    public PlayerStats() {

    }

    @Override
    public void onEnable() {
        stats = this.getConfig();

        // Register command
        this.getPluginManager().registerCommand( "getStats", new CommandExecutor() {
            @Override
            public void execute( String command, CommandSender sender, String[] args, String label ) {

                // Confirm permissions
                if ( sender.hasPermission( "infinitasoftware.playerstats.get" ) ) {
                    sender.sendMessage( "[PlayerStats] Players today: " + stats.getPlayersToday() );
                    sender.sendMessage( "[PlayerStats] Players yesterday: " + stats.getPlayersYesterday() );
                }
            }
        } );

        // Listen for new player logins
        this.getPluginManager().registerEventHandler( new Listener() {
            @EventHandler(concurrency=RunMode.THREADED)
            public void onLogin( PostPlayerLoginEvent event ) {
                stats.plusPlayer( event.getPlayer() );
            }
        } );

        // For periodical saving
        this.getPluginManager().registerEventHandler( new Listener() {
            @EventHandler(concurrency=RunMode.THREADED)
            public void onWorldSave( WorldSaveEvent event ) {
                if ( event.getWorld() == event.getServer().getMainWorld() )
                    PlayerStats.this.saveConfig();
            }
        } );
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }
}
