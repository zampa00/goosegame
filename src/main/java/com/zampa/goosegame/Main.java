package com.zampa.goosegame;

import com.zampa.goosegame.gamelogic.Game;
import com.zampa.goosegame.io.CLInput;

import java.io.*;

public class Main {

    public static void  main(String[] args) {
        Game gooseGame = new GooseGame();
        /*
        leggo input
        lo passo a qualcosa che lo esegue, ottenendo in output una lista di azioni
        passo la lista di azioni a un output

         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        CLInput cli = new CLInput(gooseGame);

        try {


            String input = reader.readLine();
            cli.parse(input);


            writer.write("Writing input: "+input+" on stdout\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
