package com.zampa.goosegame.io;

import com.zampa.goosegame.gamelogic.Game;

import java.util.Arrays;

public class CLInput {

    Game game;

    public CLInput(Game game) {
        this.game = game;
    }

    public void parse(String input) {
        String[] i = input.split("[\\s,]+");

        switch(i[0]) {
            case "add":
                parseAdd(i); break;
            case "move":
                parseMove(i); break;
            default:
        }

    }



    private void parseAdd(String[] addInput) {
        if (addInput.length != 2) {
            return;
        }
        game.addPlayer(addInput[1]);
    }

    private void parseMove(String[] moveInput) {

        switch (moveInput.length) {
            case 2:
                game.rollPlayer(moveInput[1]); break;
            case 4:
                game.movePlayerOf(moveInput[1],
                    Integer.parseInt(moveInput[2]),
                    Integer.parseInt(moveInput[3])); break;
            default:
        }
    }



}
