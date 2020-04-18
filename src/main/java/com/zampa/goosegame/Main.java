package com.zampa.goosegame;

import com.zampa.goosegame.gamelogic.Game;
import com.zampa.goosegame.gamelogic.Slot;

public class Main {

    public static void  main(String[] args) {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");
        gooseGame.addPlayer("Pluto");

        Slot newPippoSlot = gooseGame.movePlayer("Pippo", 4, 2);
        System.out.println(newPippoSlot.getNumber());

        Slot newPlutoSlot = gooseGame.movePlayer("Pluto", 2, 2);
        System.out.println(newPlutoSlot.getNumber());

        Slot newPippoSlot2 = gooseGame.movePlayer("Pippo", 3, 2);
        System.out.println(newPippoSlot2.getNumber());
    }

}
