package com.zampa.goosegame;

import com.zampa.goosegame.gamelogic.Game;
import com.zampa.goosegame.gamelogic.Slot;
import com.zampa.goosegame.io.CLInput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TestGame {

    @Before
    public void setUp() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        Game gooseGame = new GooseGame();
        CLInput cli = new CLInput(gooseGame);
    }

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

        Slot newPippoSlot = gooseGame.movePlayer("Pippo", 1, 2);
        Assert.assertEquals("First Pippo's movement failed", 3, newPippoSlot.getNumber());

        Slot newPlutoSlot = gooseGame.movePlayer("Pluto", 2, 2);
        Assert.assertEquals("First Pluto's movement failed", 4, newPlutoSlot.getNumber());

        Slot newPippoSlot2 = gooseGame.movePlayer("Pippo", 3, 4);
        Assert.assertEquals("Second Pippo's movement failed", 10, newPippoSlot2.getNumber());
    }

    @Test
    public void testGameEnd() {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");

        Slot newSlot = gooseGame.getBoard().getSlot(60);
        gooseGame.getPlayer("Pippo").get().setCurrentSlot(newSlot);

        gooseGame.movePlayer("Pippo", 1, 2);

        Assert.assertTrue("Winning condition check failed", gooseGame.isGameOver());
    }

    @Test
    public void testBounce() {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");

        Slot newSlot = gooseGame.getBoard().getSlot(60);
        gooseGame.getPlayer("Pippo").get().setCurrentSlot(newSlot);

        Slot newPippoSlot = gooseGame.movePlayer("Pippo", 3, 2);
        Assert.assertEquals("Bounce failed", 61, newPippoSlot.getNumber());
    }

    @Test
    public void testRollAndMove() {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");

        Slot newPippoSlot = gooseGame.movePlayer("Pippo");
        Assert.assertNotEquals("First Pippo's automatic movement failed", 0, newPippoSlot.getNumber());
    }

    @Test
    public void testBridge() {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");

        Slot newSlot = gooseGame.getBoard().getSlot(4);
        gooseGame.getPlayer("Pippo").get().setCurrentSlot(newSlot);

        Slot newPippoSlot = gooseGame.movePlayer("Pippo", 1, 1);
        Assert.assertEquals("The Bridge not working", 12, newPippoSlot.getNumber());
    }

    @Test
    public void testGooseSingle() {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");

        Slot newPippoSlot = gooseGame.movePlayer("Pippo", 2, 3);
        Assert.assertEquals("The Goose (single) not working", 10, newPippoSlot.getNumber());
    }

    @Test
    public void testGooseMultiple() {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");

        Slot newPippoSlot = gooseGame.movePlayer("Pippo", 4, 5);
        Assert.assertEquals("The Goose (multiple) not working", 36, newPippoSlot.getNumber());
    }

    @Test
    public void testPrank() {
        Game gooseGame = new GooseGame();
        gooseGame.addPlayer("Pippo");
        gooseGame.addPlayer("Pluto");

        gooseGame.getPlayer("Pippo").get().setCurrentSlot(gooseGame.getBoard().getSlot(19));
        gooseGame.getPlayer("Pippo").get().setCurrentSlot(gooseGame.getBoard().getSlot(4));

        gooseGame.movePlayer("Pluto", 2, 3);
        Assert.assertEquals("Prank not working",
                14,
                gooseGame.getPlayer("Pippo").get().getCurrentSlot().getNumber());
    }
}
