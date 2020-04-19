package com.zampa.goosegame;

import com.zampa.goosegame.gamelogic.Game;

public class Main {

    public static void  main(String[] args) {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");
        gooseGame.addPlayer("Pluto");


    }

}
