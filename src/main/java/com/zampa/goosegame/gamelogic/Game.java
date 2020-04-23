package com.zampa.goosegame.gamelogic;

import java.util.Optional;

public interface Game {

    /**
     * Add the player to the current game, if not already present.
     * @param playerName The name of the player to add.
     */
    public void addPlayer(String playerName);

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
     * Gets the current game board.
     * @return An object representing the current game board.
     */
    public Board getBoard();

    /**
     * Rolls two dice and move the player. Does handle special effects.
     * @param playerName The name of the player to move.
     * @return The slot the player lands on.
     */
    public Slot movePlayer(String playerName);

    /**
     * Move the player of the specified dice numbers. Does NOT handle special effects.
     * @param player The player to move. Must exists as it will not be checked.
     * @param of Number of slot to advance. Must be a valid number as it will not be checked.
     * @return The slot the player lands on.
     * @throws IllegalArgumentException when the die value is out of range.
     */
    public Slot movePlayerOf(Player player, int of);

    Slot movePlayer(String playerName, int die1, int die2) throws IllegalArgumentException;


    /**
     * Returns the player on the specified slot.
     * @param newPlayer The player who just arrived on this slot
     * @param slotNum A number between 1 and 63
     * @return An Optional containing the player on the specified slot, or nothing if empty.
     */
    public Optional<Player> getOtherPlayerOnSlot(Player newPlayer, int slotNum);

    /**
     * Check if any winning condition is met.
     * @return true if the game is over, false if ongoing.
     */
    public boolean isGameOver();


    public boolean isDieValid(int die);

}
