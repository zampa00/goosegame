package com.zampa.goosegame.io;

import com.zampa.goosegame.gamelogic.Game;
import com.zampa.goosegame.gamelogic.exception.InvalidDiceException;
import com.zampa.goosegame.gamelogic.exception.PlayerNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CLInput {

    Game game;

    public CLInput(Game game) {
        this.game = game;
    }

    public boolean parse(String input) {
        String[] i = input.split("[\\s,]+");
        Command command = new Command((input));

        if (command.getName().isEmpty()) {
            return false;
        }

        try {
            switch(command.getName()) {
                case "add":
                    return handleAdd(input, command.getParameters());
                case "move":
                    return handleMove(input, command.getParameters());
                case "exit":
                    return handleExit(input, command.getParameters());
                default:
                    CLOutputLogger.commandNotFound(input);
            }
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            CLOutputLogger.commandNotFound(input);
        }
        return false;
    }


    private boolean handleAdd(String originalCommand, List<String> parameters) {
        switch (parameters.size()) {
            case 1:
                return game.addPlayer(parameters.get(0));
            default:
                CLOutputLogger.commandNotFound(originalCommand);
        }
        return false;
    }

    private boolean handleMove(String originalCommand, List<String> parameters) {

        try {
            switch (parameters.size()) {
                case 1:
                    return game.movePlayer(parameters.get(0)) != null;
                case 3:
                    return game.movePlayer(parameters.get(0), parameters.get(1), parameters.get(2)) != null;
                default:
                    CLOutputLogger.commandNotFound(originalCommand);
            }
        }
        catch (PlayerNotFoundException e) {
            CLOutputLogger.playerNotFound(e.getMessage());
        }
        catch (InvalidDiceException e) {
            CLOutputLogger.invalidDie(e.getMessage());
        }
        catch (NumberFormatException e) {
            CLOutputLogger.invalidNumber(e.getMessage());
        }
        return false;
    }

    private boolean handleExit(String originalCommand, List<String> parameters) {
        switch (parameters.size()) {
            case 0:
                game.stopGame(); return true;
            default:
                CLOutputLogger.commandNotFound(originalCommand);
        }
        return false;
    }

}
