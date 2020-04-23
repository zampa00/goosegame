package com.zampa.goosegame;

import com.zampa.goosegame.gamelogic.Game;
import com.zampa.goosegame.io.CLInput;
import com.zampa.goosegame.io.CLOutput;
import com.zampa.goosegame.io.CLOutputFormatter;
import com.zampa.goosegame.io.IOutput;

import java.io.*;

public class Main {

    public static void  main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        IOutput clo = new CLOutput(writer);
        Game gooseGame = new GooseGame(clo);
        CLInput cli = new CLInput(gooseGame, clo);

        while (!gooseGame.isGameOver()) {
            try {
                String input = reader.readLine();
                cli.parse(input);
                clo.output();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
