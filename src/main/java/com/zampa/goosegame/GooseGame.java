package com.zampa.goosegame;

import com.zampa.goosegame.gamelogic.*;
import com.zampa.goosegame.io.CLOutput;
import com.zampa.goosegame.io.CLOutputFormatter;
import com.zampa.goosegame.io.IOutput;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class GooseGame implements Game {

    private Map<String, Player> players;
    private Board board;
    private boolean isGameOver;
    private final int MIN_DIE_VALUE = 1;
    private final int MAX_DIE_VALUE = 6;
    private IOutput output;


    public GooseGame(IOutput output) {
        players = new HashMap<>();
        board = new Board();
        isGameOver = false;
        this.output = output;
    }

    @Override
    public boolean addPlayer(String playerName) {
        Player newPlayer = new Player(playerName);
        newPlayer.setCurrentSlot(board.getSlot(0));

        if (!hasPlayer(playerName)) {
            players.put(playerName, newPlayer);

            String playerList = players.keySet().stream().collect(Collectors.joining(", "));
            output.append(CLOutputFormatter.listPlayers(playerList));
            return true;
        }
        else {
            output.append(CLOutputFormatter.playerExists(playerName));
            return false;
        }
    }

    @Override
    public boolean hasPlayer(String playerName) {
        return players.containsKey(playerName);
    }

    @Override
    public Optional<Player> getPlayer(String playerName) {
        return Optional.ofNullable(players.get(playerName));
    }

    @Override
    public Slot rollPlayer(String playerName) {
        int die1 = ThreadLocalRandom.current().nextInt(MIN_DIE_VALUE+1, MAX_DIE_VALUE+1);
        int die2 = ThreadLocalRandom.current().nextInt(MIN_DIE_VALUE+1, MAX_DIE_VALUE+1);

        return movePlayer(playerName, die1, die2);
    }

    @Override
    public Slot movePlayerOf(Player player, int of) {

        Slot currentSlot = player.getCurrentSlot();
        Slot destination = board.advanceFromSlot(currentSlot, of);

        if (board.willBounce(currentSlot, of)) {
            output.append(CLOutputFormatter.playerMove(player.getName(), currentSlot.getName(), destination.getName()));
            output.append(CLOutputFormatter.playerBounce(player.getName(), destination.getName()));
        }
//        else {
//            output.append(CLOutputFormatter.playerMove(player.getName(), currentSlot.getName(), destination.getName()));
//        }
        player.setCurrentSlot(destination);
        handlePrank(player, destination);

        return destination;
    }

    @Override
    public Slot movePlayer(String playerName, int die1, int die2) {

        Player player = getPlayer(playerName).get();

        output.append(CLOutputFormatter.playerRoll(playerName, die1, die2));

        // muovo in avanti eventualmente rimbalzando, mi restituisce la destinazione
        // controllo se sono arrivato su uno slot con un giocatore, e se sì lo sostituisco
        Slot destination = movePlayerOf(player, die1+die2);

        output.append(CLOutputFormatter.playerMove(player.getName(), player.getPreviousSlot().getName(), destination.getName()));

        // controllo se devo muovere di nuovo
        while (destination.getType() == SlotType.GOOSE) {
            destination = movePlayerOf(player, die1+die2);
            output.append(CLOutputFormatter.playerMoveAgain(player.getName(), destination.getName()));
        }
        if (destination.getType() == SlotType.BRIDGE) {
            destination = movePlayerTo(playerName, board.getSlot(12));
            output.append(CLOutputFormatter.playerJump(player.getName(), destination.getName()));
        }

        if (destination.getType() == SlotType.FINAL) {
            this.isGameOver = true;
            output.append(CLOutputFormatter.playerWin(player.getName()));
        }

        return destination;
    }

    @Override
    public Slot movePlayerTo(String playerName, Slot destination) {
        Player player = getPlayer(playerName).get();
        player.setCurrentSlot(destination);
        return destination;
    }


    @Override
    public Optional<Player> getOtherPlayerOnSlot(Player newPlayer, int slotNum) {
        return players.values().stream()
                .filter(player -> player.getCurrentSlot().getNumber() == slotNum)
                .filter(player -> !player.equals(newPlayer))
                .findFirst();
    }

    private void handlePrank(Player newPlayer, Slot destination) {
        getOtherPlayerOnSlot(newPlayer, destination.getNumber())
                .ifPresent(
                        oldPlayer -> {
                            switchPlayers(newPlayer, oldPlayer);
                            output.append(CLOutputFormatter.playerPrank(
                                    oldPlayer.getName(),
                                    newPlayer.getPreviousSlot().getName()));
                        }
                );
    }

    private void switchPlayers(Player newPlayer, Player oldPlayer) {
        oldPlayer.setCurrentSlot(newPlayer.getPreviousSlot());
        newPlayer.setCurrentSlot(oldPlayer.getCurrentSlot());
    }

    @Override
    public boolean isGameOver() {
        return this.isGameOver;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public boolean isDieValid(int die) {
        return die <= MAX_DIE_VALUE && die >= MIN_DIE_VALUE;
    }
}
