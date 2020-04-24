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
    boolean addPlayer(String playerName);


    /**
     * Check if the specified player is in the game.
     * @param playerName The name of the player to check existence of.
     * @return true if the player is in the game, false otherwise.
     */
    boolean hasPlayer(String playerName);


    /**
     * Gets the corresponding Player object from the name of the player.
     * @param playerName The name of the player to retrieve.
     * @return The object representing the player in the current game, or null if no player with this playerName
     * @throws PlayerNotFoundException if no player with this name is present
     */
    Player getPlayer(String playerName) throws PlayerNotFoundException;


    /**
     * Gets the current game board.
     * @return An object representing the current game board.
     */
    Board getBoard();


    /**
     * Rolls two dice and move the player. Does handle special effects.
     * @param playerName The name of the player to move.
     * @return The slot the player lands on.
     * @throws PlayerNotFoundException if no player with this name is present
     */
    Slot movePlayer(String playerName) throws PlayerNotFoundException, InvalidDiceException;


    /**
     * Parse the dice and move the player. It will check input validity.
     * Does handle special effects.
     * @param playerName The name of the player to move.
     * @return The slot the player lands on.
     * @throws PlayerNotFoundException if no player with this name is present.
     * @throws IllegalArgumentException if is trying to parse an invalid number.
     * @throws InvalidDiceException if the number parsed is out if range.
     */
    Slot movePlayer(String playerName, String stringDie1, String stringDie2) throws IllegalArgumentException, PlayerNotFoundException, InvalidDiceException;


    /**
     * Parse the dice and move the player. It will check input validity.
     * Does handle special effects.
     * @param playerName The name of the player to move.
     * @return The slot the player lands on.
     * @throws PlayerNotFoundException if no player with this name is present.
     * @throws InvalidDiceException if a dice roll is out if range.
     */
    Slot movePlayer(String playerName, int die1, int die2) throws PlayerNotFoundException, InvalidDiceException;


    /**
     * Check if any winning condition is met.
     * @return true if the game is over, false if ongoing.
     */
    boolean isGameOver();

    /**
     * Immediately interrupt the game and exit.
     */
    void stopGame();

    /**
     * Check if the input number is in a valid range.
     */
    boolean isDieValid(int die);

}
