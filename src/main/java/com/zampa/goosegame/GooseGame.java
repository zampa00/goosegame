package com.zampa.goosegame;

import com.zampa.goosegame.gamelogic.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean hasPlayer(String playerName) {
        return players.containsKey(playerName);
    }

    @Override
    public Player getPlayer(String playerName) {
        return players.get(playerName);
    }

    @Override
    public Slot rollPlayer(String playerName) {
        int die1 = ThreadLocalRandom.current().nextInt(MIN_DIE_VALUE+1, MAX_DIE_VALUE+1);
        int die2 = ThreadLocalRandom.current().nextInt(MIN_DIE_VALUE+1, MAX_DIE_VALUE+1);

        return movePlayerOf(playerName, die1, die2);
    }

    @Override
    public Slot movePlayerOf(String playerName, int die1, int die2) throws IllegalArgumentException {
        if (die1 < MIN_DIE_VALUE
                || die1 > MAX_DIE_VALUE
                || die2 < MIN_DIE_VALUE
                || die2 > MAX_DIE_VALUE) {
            throw new IllegalArgumentException();
        }

        Player player = getPlayer(playerName);
        Slot currentSlot = player.getCurrentSlot();

        if (board.willBounce(currentSlot, die1+die2)) {

        }

        Slot newSlot = board.advanceFromSlot(currentSlot, die1+die2);

        if (getPlayerOnSlot(newSlot.getNumber()).isPresent()) {
            prank(player, newSlot);
        }
        else {
            player.setCurrentSlot(newSlot);
        }

        switch (newSlot.getType()) {
            case BRIDGE:
                newSlot = movePlayerTo(playerName, board.getSlot(12)); break;
            case GOOSE:
                newSlot = movePlayerOf(playerName, die1, die2); break;
            case FINAL:
                this.isGameOver = true; break;
            default:

        }

        return newSlot;
    }

    @Override
    public Slot movePlayerTo(String playerName, Slot destination) {
        Player player = getPlayer(playerName);
        player.setCurrentSlot(destination);
        return destination;
    }

    @Override
    public Optional<Player> getPlayerOnSlot(int slotNum) {
        return players.values().stream()
                .filter(player -> player.getCurrentSlot().getNumber() == slotNum)
                .findFirst();
    }

    // Prank: if another player is on the destination slot, they switch
    private void prank(Player newPlayer, Slot destination) {
        System.out.println("Prank!");
        getPlayerOnSlot(destination.getNumber())
                .ifPresent(
                        oldPlayer -> switchPlayers(newPlayer, oldPlayer)
                );
    }

    private void switchPlayers(Player newPlayer, Player oldPlayer) {
        newPlayer.setCurrentSlot(oldPlayer.getCurrentSlot());
        oldPlayer.setCurrentSlot(newPlayer.getPreviousSlot());
    }

    @Override
    public boolean isGameOver() {
        return this.isGameOver;
    }

    public Board getBoard() {
        return board;
    }
}
