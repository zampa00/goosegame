package com.zampa.goosegame.gamelogic;

public interface Game {

    /**
     * @param playerName
     * @return true if the player is correctly added, false if already present
     */
    public boolean addPlayer(String playerName);

    public boolean hasPlayer(String playerName);

    /**
     * @param playerName
     * @return The object representing the player in the current game, or null if no player with this playerName
     */
    public Player getPlayer(String playerName);

}
