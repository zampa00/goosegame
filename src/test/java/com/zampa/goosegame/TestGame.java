package com.zampa.goosegame;

import com.zampa.goosegame.gamelogic.Game;
import com.zampa.goosegame.gamelogic.Slot;
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

    @Test
    public void testMovePlayer() {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");
        gooseGame.addPlayer("Pluto");

        Slot newPippoSlot = gooseGame.movePlayerOf("Pippo", 1, 2);
        Assert.assertEquals("First Pippo's movement failed", newPippoSlot.getNumber(), 3);

        Slot newPlutoSlot = gooseGame.movePlayerOf("Pluto", 2, 2);
        Assert.assertEquals("First Pluto's movement failed", newPlutoSlot.getNumber(), 4);

        Slot newPippoSlot2 = gooseGame.movePlayerOf("Pippo", 3, 4);
        Assert.assertEquals("Second Pippo's movement failed", newPippoSlot2.getNumber(), 10);
    }

    @Test
    public void testGameEnd() {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");

        Slot newSlot = gooseGame.getBoard().getSlot(60);
        gooseGame.getPlayer("Pippo").setCurrentSlot(newSlot);

        gooseGame.movePlayerOf("Pippo", 1, 2);

        Assert.assertTrue("Winning condition check failed", gooseGame.isGameOver());
    }

    @Test
    public void testBounce() {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");

        Slot newSlot = gooseGame.getBoard().getSlot(60);
        gooseGame.movePlayerTo("Pippo", newSlot);

        Slot newPippoSlot = gooseGame.movePlayerOf("Pippo", 3, 2);
        Assert.assertEquals("Bounce failed", newPippoSlot.getNumber(), 61);
    }

    @Test
    public void testRollAndMove() {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");

        Slot newPippoSlot = gooseGame.rollPlayer("Pippo");
        Assert.assertNotEquals("First Pippo's automatic movement failed", newPippoSlot.getNumber(), 0);
    }

    @Test
    public void testBridge() {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");

        Slot newSlot = gooseGame.getBoard().getSlot(4);
        gooseGame.movePlayerTo("Pippo", newSlot);

        Slot newPippoSlot = gooseGame.movePlayerOf("Pippo", 1, 1);
        Assert.assertEquals("The Bridge not working", newPippoSlot.getNumber(), 12);
    }
}
