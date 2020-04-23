package com.zampa.goosegame.io;

import com.zampa.goosegame.gamelogic.Game;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CLInput {

    Game game;

    public CLInput(Game game) {
        this.game = game;
    }

    public void parse(String input) {
        String[] i = input.split("[\\s]+");

        try {
            switch(i[0]) {
                case "add":
                    parseAdd(i); break;
                case "move":
                    if(validateMove(input)) parseMove(i); break;
                default:
                    CLOutputLogger.commandNotFound(input);
            }
        }
        catch (IllegalArgumentException e) {
            CLOutputLogger.commandNotFound(input);
        }


    }

    public boolean validateMove(String originalCommand) {
        String[] input = originalCommand.split("[\\s]+");

        if (input.length != 2 && input.length != 4) {
            CLOutputLogger.commandNotFound(originalCommand);
            return false;
        }

        // input[1] must be a valid player
        if (!game.hasPlayer(input[1])) {
            CLOutputLogger.playerNotFound(input[1]);
            return false;
        }

        if (input.length == 4) {
            // input[2] must be a valid number followed by a comma
            if (!input[2].endsWith(",")) {
                CLOutputLogger.commandNotFound(originalCommand);
                return false;
            }

            // input[2] and input[3] must be valid numbers
            for (int i = 2; i < 4; i++) {
                try {
                    int die = Integer.parseInt(input[i].replace(",", ""));
                    if (!game.isDieValid(die)) {
                        CLOutputLogger.invalidDie(die);
                        return false;
                    }
                } catch (NumberFormatException e) {
                    CLOutputLogger.invalidNumber(input[i]);
                    return false;
                }
            }
        }

        return true;
    }



    private void parseAdd(String[] addInput) {
        if (addInput.length != 2) {
            CLOutputLogger.commandNotFound(addInput[0]);
            return;
        }
        game.addPlayer(addInput[1]);
    }

    private void parseMove(String[] moveInput) throws IllegalArgumentException {

        switch (moveInput.length) {
            case 2:
                game.movePlayer(moveInput[1]); break;
            case 4:
                game.movePlayer(moveInput[1],
                    Integer.parseInt(moveInput[2]),
                    Integer.parseInt(moveInput[3])); break;
            default:
                CLOutputLogger.commandNotFound(Arrays.stream(moveInput).collect(Collectors.joining(" ")));
        }
    }



}
