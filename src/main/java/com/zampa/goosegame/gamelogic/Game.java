package com.zampa.goosegame.gamelogic;

public interface Game {

    /**
     * Add the player to the current game, if not already present.
     * @param playerName The name of the player to add.
     * @return true if the player is correctly added, false if already present
     */
    public boolean addPlayer(String playerName);

    /**
     * Check if the specified player is in the game.
     * @param playerName The name of the player to check existence of.
     * @return true if the player is in the game, false otherwise.
     */
    public boolean hasPlayer(String playerName);

    /**
     * Gets the corresponding Player object from the name of the player.
     * @param playerName The name of the player to retrieve.
     * @return The object representing the player in the current game, or null if no player with this playerName
     */
    public Player getPlayer(String playerName);

    /**
     * Rolls two dice and move the player. Does not handle special effects.
     * @param playerName The name of the player to move.
     * @return The slot the player lands on.
     */
    public Slot movePlayer(String playerName);

    /**
     * Move the player of the specified dice numbers. Does not handle special effects.
     * @param playerName The name of the player to move.
     * @param die1 A valid die value.
     * @param die2 A valid die value.
     * @return The slot the player lands on.
     * @throws IllegalArgumentException when the die value is out of range.
     */
    public Slot movePlayer(String playerName, int die1, int die2) throws IllegalArgumentException ;

}
