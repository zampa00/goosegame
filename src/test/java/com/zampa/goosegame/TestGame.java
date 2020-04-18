package com.zampa.goosegame;

import com.zampa.goosegame.gamelogic.Game;
import org.junit.Assert;
import org.junit.Test;

public class TestGame {


    @Test
    public void testAddPlayer() {
        Game gooseGame = new GooseGame();
        boolean addPlayerResult = gooseGame.addPlayer("Pippo");
        Assert.assertTrue("Failed to add player", addPlayerResult);
    }

    @Test
    public void testAddPlayerTwice() {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");
        boolean addPlayerTwiceResult = gooseGame.addPlayer("Pippo");
        Assert.assertFalse("Player added twice", addPlayerTwiceResult);
    }


}
