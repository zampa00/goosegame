package com.zampa.goosegame.gamelogic;

import com.zampa.goosegame.gamelogic.exception.InvalidDiceException;
import com.zampa.goosegame.gamelogic.exception.PlayerNotFoundException;

import java.util.Optional;

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
    public Player getPlayer(String playerName) throws PlayerNotFoundException;

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
    public Slot movePlayer(String playerName) throws InvalidDiceException, PlayerNotFoundException;

    Slot movePlayer(String playerName, String stringDie1, String stringDie2) throws IllegalArgumentException, InvalidDiceException, PlayerNotFoundException;

    public Slot movePlayer(String playerName, int die1, int die2) throws IllegalArgumentException, PlayerNotFoundException, InvalidDiceException;

    /**
     * Move the player of the specified dice numbers. Does NOT handle special effects.
     * @param player The player to move. Must exists as it will not be checked.
     * @param of Number of slot to advance. Must be a valid number as it will not be checked.
     * @return The slot the player lands on.
     * @throws IllegalArgumentException when the die value is out of range.
     */
    public Slot movePlayerOf(Player player, int of);



    /**
     * Check if any winning condition is met.
     * @return true if the game is over, false if ongoing.
     */
    public boolean isGameOver();


    public boolean isDieValid(int die);

}
