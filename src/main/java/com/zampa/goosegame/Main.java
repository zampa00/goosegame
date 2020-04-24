package com.zampa.goosegame;

import com.zampa.goosegame.gamelogic.Game;
import com.zampa.goosegame.gamelogic.exception.InvalidDiceException;
import com.zampa.goosegame.gamelogic.exception.PlayerNotFoundException;
import com.zampa.goosegame.io.CLInput;
import com.zampa.goosegame.io.CLOutputLogger;

import java.io.*;

public class Main {

    public static void  main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        CLOutputLogger.init();
        Game gooseGame = new GooseGame();
        CLInput cli = new CLInput(gooseGame);

        try {
            while (!gooseGame.isGameOver()) {
                String input = reader.readLine();
                cli.parse(input);
                CLOutputLogger.printOutput();
            }
            reader.close();

            CLOutputLogger.gameEnd();
            CLOutputLogger.printOutput();
            CLOutputLogger.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
