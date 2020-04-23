package com.zampa.goosegame.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public final class CLOutputLogger {

    static String listPlayers = "players: $LIST. ";
    static String playersExists = "$PLAYER: already existing player. ";
    static String playerRolls = "$PLAYER rolls $DIE1, $DIE2. ";
	static String playerMoves = "$PLAYER moves from $START to $DESTINATION. ";
	static String playerJumps = "$PLAYER jumps to $DESTINATION. ";
	static String playerMovesAgain = "$PLAYER moves again and goes to $DESTINATION. ";
    static String playerBounces = "$PLAYER bounces! $PLAYER returns to $DESTINATION. ";
	static String playerPranks = "On $DESTINATION there is $PLAYER, who returns to $START. ";
	static String playerWins = "$PLAYER Wins!! ";

	static String playerNotFound = "Player $PLAYER does not exists. ";
	static String commandNotFound = "Unknown command: \"$COMMAND\". ";
	static String invalidNumber = "$NUMBER is not a valid number. ";
	static String dieOutOfRange = "$NUMBER is not a valid roll. ";

	static String help =
            "add <PlayerName> - adds <Playername> if not already in game.\n" +
            "move <PlayerName> - rolls dice and move.\n" +
            "move <PlayerName> <die>, <die> - move of the specified dice.";

    private static BufferedWriter writer;
    private static StringBuffer buffer;


    public static void init() {
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        buffer = new StringBuffer();
    }

    public void append(String info) {
        if (buffer == null || writer == null) {
            init();
        }
        buffer.append(info);
    }

    public static void printOutput() {
        if (writer == null || buffer == null) {
            init();
        }
        try {
            writer.write(buffer.toString());
            writer.write("\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer.setLength(0);
    }

    public static void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void listPlayers(String playerNames) {
        buffer.append(listPlayers.replace("$LIST", playerNames));
    }


    public static void playerExists(String playerName) {
        buffer.append(playersExists.replace("$PLAYER", playerName));
    }


    public static void playerRoll(String playerName, int die1, int die2) {
        buffer.append(playerRolls.replace("$PLAYER", playerName)
                .replace("$DIE1", Integer.toString(die1))
                .replace("$DIE2", Integer.toString(die2)));
    }


    public static void playerMove(String playerName, String from, String to) {
        buffer.append(playerMoves.replace("$PLAYER", playerName)
                .replace("$START", from)
                .replace("$DESTINATION", to));
    }


    public static void playerJump(String playerName, String to) {
        buffer.append(playerJumps.replace("$PLAYER", playerName)
                .replace("$DESTINATION", to));
    }


    public static void playerMoveAgain(String playerName, String to) {
        buffer.append(playerMovesAgain.replace("$PLAYER", playerName)
                .replace("$DESTINATION", to));
    }


    public static void playerBounce(String playerName, String to) {
        buffer.append(playerBounces.replace("$PLAYER", playerName)
                .replace("$DESTINATION", to));
    }


    public static void playerWin(String playerName) {
        buffer.append(playerWins.replace("$PLAYER", playerName));
    }


    public static void playerPrank(String playerPranked, String returnsTo) {
        buffer.append(playerPranks.replaceAll("$PLAYER", playerPranked)
                .replace("$START", returnsTo));
    }


    public static void playerNotFound(String player) {
        buffer.append(playerNotFound.replace("$PLAYER", player));
    }


    public static void invalidNumber(String number) {
        buffer.append(invalidNumber.replace("$NUMBER", number));
    }


    public static void invalidDie(int number) {
        buffer.append(dieOutOfRange.replace("$NUMBER", Integer.toString(number)));
    }


    public static void commandNotFound(String command) {
        buffer.append(commandNotFound.replace("$COMMAND", command)
                + "\n" + help);
    }


    public static void help() {
        buffer.append(help);
    }

}
