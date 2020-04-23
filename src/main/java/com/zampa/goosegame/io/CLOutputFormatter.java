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

	static String playerNotFound = "Player $PLAYER does not exists. ";
	static String commandNotFound = "Unknown command: \"$COMMAND\". ";
	static String invalidNumber = "$NUMBER is not a valid number. ";
	static String dieOutOfRange = "$NUMBER is not a valid roll. ";

	static String help =
            "add <PlayerName> - adds <Playername> if not already in game.\n" +
            "move <PlayerName> - rolls dice and move.\n" +
            "move <PlayerName> <die>, <die> - move of the specified dice.";


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
        return playerBounces.replace("$PLAYER", playerName)
                .replace("$DESTINATION", to);
    }


    public static String playerWin(String playerName) {
        return playerWins.replace("$PLAYER", playerName);
    }


    public static String playerPrank(String playerPranked, String returnsTo) {
        return playerPranks.replaceAll("$PLAYER", playerPranked)
                .replace("$START", returnsTo);
    }


    public static String playerNotFound(String player) {
        return playerNotFound.replace("$PLAYER", player);
    }


    public static String invalidNumber(String number) {
        return invalidNumber.replace("$NUMBER", number);
    }


    public static String invalidDie(int number) {
        return dieOutOfRange.replace("$NUMBER", Integer.toString(number));
    }


    public static String commandNotFound(String command) {
        return commandNotFound.replace("$COMMAND", command)
                + "\n" + help;
    }


    public static String help() {
        return help;
    }

}
