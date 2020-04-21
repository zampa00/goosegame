package com.zampa.goosegame.io;

public final class CLOutputFormatter {

    static String listPlayers = "players: $LIST. ";
    static String playersExists = "$PLAYER: already existing player. ";
    static String playerRolls = "$PLAYER rolls $DIE1, $DIE2. ";
	static String playerMoves = "$PLAYER moves from $START to $DESTINATION. ";
	static String playerJumps = "$PLAYER jumps to $DESTINATION. ";
	static String playerMovesAgain = "$PLAYER moves again and goes to $DESTINATION. ";
    static String playerBounces = "$PLAYER bounces! $PLAYER returns to $DESTINATION. ";
	static String playerPranks = "On $DESTINATION there is $PLAYER, who returns to $START. ";
	static String playerWins = "$PLAYER Wins!! ";


    public static String listPlayers(String playerNames) {
        return listPlayers.replace("$LIST", playerNames);
    }


    public static String playerExists(String playerName) {
        return playersExists.replace("$PLAYER", playerName);
    }


    public static String playerRoll(String playerName, int die1, int die2) {
        return playerRolls.replace("$PLAYER", playerName)
                .replace("$DIE1", Integer.toString(die1))
                .replace("$DIE2", Integer.toString(die2));
    }


    public static String playerMove(String playerName, String from, String to) {
        return playerMoves.replace("$PLAYER", playerName)
                .replace("$START", from)
                .replace("$DESTINATION", to);
    }


    public static String playerJump(String playerName, String to) {
        return playerJumps.replace("$PLAYER", playerName)
                .replace("$DESTINATION", to);
    }


    public static String playerMoveAgain(String playerName, String to) {
        return playerMovesAgain.replace("$PLAYER", playerName)
                .replace("$DESTINATION", to);
    }


    public static String playerBounce(String playerName, String to) {
        return playerBounces.replaceAll("$PLAYER", playerName)
                .replace("$DESTINATION", to);
    }


    public static String playerWin(String playerName) {
        return playerWins.replace("$PLAYER", playerName);
    }


    public static String playerPrank(String playerPranked, String returnsTo) {
        return playerPranks.replaceAll("$PLAYER", playerPranked)
                .replace("$START", returnsTo);
    }
}
