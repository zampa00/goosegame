package com.zampa.goosegame;

import com.zampa.goosegame.gamelogic.Game;
import com.zampa.goosegame.gamelogic.exception.InvalidDiceException;
import com.zampa.goosegame.gamelogic.exception.PlayerNotFoundException;
import com.zampa.goosegame.io.CLInput;
import com.zampa.goosegame.io.CLOutputLogger;

import java.io.*;

public class Main {

    public static void  main(String[] args) throws PlayerNotFoundException, InvalidDiceException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        CLOutputLogger.init();
        Game gooseGame = new GooseGame();
        CLInput cli = new CLInput(gooseGame);

        gooseGame.addPlayer("Pippo");
        gooseGame.addPlayer("Pluto");

        gooseGame.getPlayer("Pippo").setCurrentSlot(gooseGame.getBoard().getSlot(19));
        gooseGame.getPlayer("Pluto").setCurrentSlot(gooseGame.getBoard().getSlot(4));

        gooseGame.movePlayer("Pluto", 2, 3);

        System.out.println("Ho mosso tutto, ora Pippo si trova in "+gooseGame.getPlayer("Pippo").getCurrentSlot().getNumber());

        try {
            while (!gooseGame.isGameOver()) {
                String input = reader.readLine();
                cli.parse(input);
                CLOutputLogger.printOutput();
            }
            reader.close();
            CLOutputLogger.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
