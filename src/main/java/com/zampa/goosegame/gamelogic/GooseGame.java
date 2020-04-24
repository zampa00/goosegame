package com.zampa.goosegame.gamelogic;

import com.zampa.goosegame.gamelogic.exception.InvalidDiceException;
import com.zampa.goosegame.gamelogic.exception.PlayerNotFoundException;
import com.zampa.goosegame.io.CLOutputLogger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class GooseGame implements Game {

    private Map<String, Player> players;
    private Board board;
    private boolean isGameOver;
    private final int MIN_DIE_VALUE = 1;
    private final int MAX_DIE_VALUE = 6;


    public GooseGame() {
        players = new HashMap<>();
        board = new Board();
        isGameOver = false;
    }

    @Override
    public boolean addPlayer(String playerName) {
        Player newPlayer = new Player(playerName);
        newPlayer.setCurrentSlot(board.getSlot(0));

        if (!hasPlayer(playerName)) {
            players.put(playerName, newPlayer);

            String playerList = String.join(", ", players.keySet());
            CLOutputLogger.listPlayers(playerList);
            return true;
        }
        else {
            CLOutputLogger.playerExists(playerName);
            return false;
        }
    }

    @Override
    public boolean hasPlayer(String playerName) {
        return players.containsKey(playerName);
    }

    @Override
    public Player getPlayer(String playerName) throws PlayerNotFoundException {
        Player player = players.get(playerName);
        if (player == null) {
            throw new PlayerNotFoundException(playerName);
        }
        return player;
    }


    public void setPlayerSlot(Player player, Slot destination) {
        player.setCurrentSlot(destination);

        // handle prank
        players.values().stream()
                .filter(p -> p.getCurrentSlot().equals(player.getCurrentSlot()))
                .filter(p -> !p.equals(player))
                .forEach(oldPlayer -> switchPlayers(player, oldPlayer));
    }


    @Override
    public Slot movePlayer(String playerName) throws InvalidDiceException, PlayerNotFoundException {
        int die1 = ThreadLocalRandom.current().nextInt(MIN_DIE_VALUE+1, MAX_DIE_VALUE+1);
        int die2 = ThreadLocalRandom.current().nextInt(MIN_DIE_VALUE+1, MAX_DIE_VALUE+1);

        return movePlayer(playerName, die1, die2);
    }

    @Override
    public Slot movePlayer(String playerName, String stringDie1, String stringDie2) throws NumberFormatException, InvalidDiceException, PlayerNotFoundException {
        int die1 = Integer.parseInt(stringDie1);
        int die2 = Integer.parseInt(stringDie2);
        return movePlayer(playerName, die1, die2);
    }

    @Override
    public Slot movePlayer(String playerName, int die1, int die2) throws PlayerNotFoundException, InvalidDiceException {

        // Check arguments
        Player player = getPlayer(playerName);

        if (!isDieValid(die1)) {
            throw new InvalidDiceException(""+die1);
        }
        if (!isDieValid(die2)) {
            throw new InvalidDiceException(""+die2);
        }

        CLOutputLogger.playerRoll(playerName, die1, die2);

        // get the destination from the roll
        Slot destination = board.advanceFromSlot(player.getCurrentSlot(), die1+die2);

        // message is different if bouncing
        if (board.willBounce(player.getCurrentSlot(), die1+die2)) {
            CLOutputLogger.playerBounce(player.getName(), player.getCurrentSlot().getName(), destination.getName());
        }
        else {
            CLOutputLogger.playerMove(player.getName(), player.getCurrentSlot().getName(), destination.getName());
        }

        // advance the player, pranking if other players are on that slot
        setPlayerSlot(player, destination);

        // check if the destination is a special slot, and handle it
        while (destination.getType() == SlotType.GOOSE) {
            destination = board.advanceFromSlot(player.getCurrentSlot(), die1+die2);
            setPlayerSlot(player, destination); // advance the player, pranking if other players are on that slot
            CLOutputLogger.playerMoveAgain(player.getName(), destination.getName());
        }
        if (destination.getType() == SlotType.BRIDGE) {
            destination = board.getSlot(12);
            CLOutputLogger.playerJump(player.getName(), destination.getName());
            setPlayerSlot(player, destination); // advance the player, pranking if other players are on that slot
        }

        if (destination.getType() == SlotType.FINAL) {
            this.isGameOver = true;
            CLOutputLogger.playerWin(player.getName());
        }

        return destination;
    }


    private void switchPlayers(Player newPlayer, Player oldPlayer) {
        oldPlayer.setCurrentSlot(newPlayer.getPreviousSlot());
        CLOutputLogger.playerPrank(
                oldPlayer.getName(),
                newPlayer.getCurrentSlot().getName(),
                oldPlayer.getCurrentSlot().getName());
    }

    @Override
    public boolean isGameOver() {
        return this.isGameOver;
    }

    @Override
    public void stopGame() {
        this.isGameOver = true;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean isDieValid(int die) {
        return die <= MAX_DIE_VALUE && die >= MIN_DIE_VALUE;
    }
}
