package com.infinitasoftware.playerstats;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;

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
        
        this.getPluginManager().registerEventHandler( new EventListener(this) );
    }
    
    public Stats getStats() {
        return this.stats;
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }
}
