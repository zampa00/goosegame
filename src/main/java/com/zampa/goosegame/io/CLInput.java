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

    public void parse(String input) {
        String[] i = input.split("[\\s,]+");
        System.out.println(input);
        Command command = new Command((input));

        try {
            switch(command.getName()) {
                case "add":
                    parseAdd(i); break;
                case "move":
                    handleMove(input, command.getParameters()); break;
                default:
                    CLOutputLogger.commandNotFound(input);
            }
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            CLOutputLogger.commandNotFound(input);
        }

    }

    private void handleMove(String originalCommand, List<String> parameters) {

        try {
            switch (parameters.size()) {
                case 1:
                    game.movePlayer(parameters.get(0)); break;
                case 3:
                    game.movePlayer(parameters.get(0), parameters.get(1), parameters.get(2)); break;
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

    }



    private void parseAdd(String[] addInput) {
        if (addInput.length != 2) {
            CLOutputLogger.commandNotFound(addInput[0]);
            return;
        }
        game.addPlayer(addInput[1]);
    }




}
