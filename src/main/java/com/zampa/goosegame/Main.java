package com.zampa.goosegame;

import com.zampa.goosegame.gamelogic.Game;
import com.zampa.goosegame.gamelogic.Slot;

public class Main {

    public static void  main(String[] args) {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");
        gooseGame.addPlayer("Pluto");

        Slot newPippoSlot = gooseGame.movePlayerOf("Pippo", 1, 2);
        System.out.println(newPippoSlot.getNumber());

        Slot newPlutoSlot = gooseGame.movePlayerOf("Pluto", 2, 2);
        System.out.println(newPlutoSlot.getNumber());

        System.out.println(gooseGame.getPlayer("Pippo").getCurrentSlot().getNumber());

        Slot newPippoSlot2 = gooseGame.movePlayerOf("Pippo", 3, 4);
        System.out.println(newPippoSlot2.getNumber());
    }

}
