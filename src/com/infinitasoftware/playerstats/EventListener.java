package com.infinitasoftware.playerstats;

import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PostPlayerLoginEvent;
import com.mbserver.api.events.WorldSaveEvent;

public class EventListener implements Listener {
    private PlayerStats playerStats;
    
    public EventListener(PlayerStats playerStats) {
        this.playerStats = playerStats;
    }
    
    @EventHandler
    public void onLogin( PostPlayerLoginEvent event ) {
        playerStats.getStats().plusPlayer( event.getPlayer() );
    }
    
    @EventHandler
    public void onWorldSave( WorldSaveEvent event ) {
        if ( event.getWorld() == event.getServer().getMainWorld() )
            playerStats.saveConfig();
    }
}
