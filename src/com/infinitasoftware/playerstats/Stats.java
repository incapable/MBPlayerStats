package com.infinitasoftware.playerstats;

import java.util.Date;
import java.util.HashSet;

import com.mbserver.api.game.Player;

@SuppressWarnings( "deprecation" )
// For the date objects because java did not give me a proper alternative
/**
 * General statistic data class
 * @author incapable
 *
 */
public class Stats {
    private int               today;
    private int               playersYesterday;
    private HashSet< String > players;

    /**
     * Constructor
     */
    public Stats() {
        today = new Date().getDay();
        players = new HashSet< String >();
    }

    /**
     * Adds a player count if it's valid
     * 
     * @param player
     *            The player to add to the count
     */
    public void plusPlayer( Player player ) {
        // Check if the current count is for today
        if ( new Date().getDay() != today ) {
            //reset counters
            playersYesterday = players.size();
            players.clear();
            players.add( player.getLoginName() );
        }
        
        if ( !players.contains( player.getLoginName() ) ) {
            players.add( player.getLoginName() );
        }
    }

    /**
     * Gets the player count for today
     * 
     * @return The player count
     */
    public int getPlayersToday() {
        return this.players.size();
    }

    /**
     * Gets the player count for yesterday
     * 
     * @return The player count for yesterday
     */
    public int getPlayersYesterday() {
        return this.playersYesterday;
    }
}
