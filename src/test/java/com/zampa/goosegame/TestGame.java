package com.zampa.goosegame;

import com.zampa.goosegame.gamelogic.Game;
import com.zampa.goosegame.gamelogic.GooseGame;
import com.zampa.goosegame.gamelogic.Slot;
import com.zampa.goosegame.gamelogic.exception.InvalidDiceException;
import com.zampa.goosegame.gamelogic.exception.PlayerNotFoundException;
import com.zampa.goosegame.io.CLOutputLogger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestGame {

    Game gooseGame;

    @Before
    public void setUp() {
        CLOutputLogger.init();
        this.gooseGame = new GooseGame();
    }

    @Test
    public void testAddPlayer() {
        boolean addPlayerResult = gooseGame.addPlayer("Pippo");
        Assert.assertTrue("Failed to add player", addPlayerResult);
    }

    @Test
    public void testAddPlayerTwice() {
        gooseGame.addPlayer("Pippo");
        boolean addPlayerTwiceResult = gooseGame.addPlayer("Pippo");
        Assert.assertFalse("Player added twice", addPlayerTwiceResult);
    }

    @Test
    public void testMovePlayer() throws PlayerNotFoundException, InvalidDiceException {
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
    public void testGameEnd() throws PlayerNotFoundException, InvalidDiceException {
        gooseGame.addPlayer("Pippo");

        Slot newSlot = gooseGame.getBoard().getSlot(60);
        gooseGame.getPlayer("Pippo").setCurrentSlot(newSlot);

        gooseGame.movePlayer("Pippo", 1, 2);

        Assert.assertTrue("Winning condition check failed", gooseGame.isGameOver());
    }

    @Test
    public void testBounce() throws PlayerNotFoundException, InvalidDiceException {
        gooseGame.addPlayer("Pippo");

        Slot newSlot = gooseGame.getBoard().getSlot(60);
        gooseGame.getPlayer("Pippo").setCurrentSlot(newSlot);

        Slot newPippoSlot = gooseGame.movePlayer("Pippo", 3, 2);
        Assert.assertEquals("Bounce failed", 61, newPippoSlot.getNumber());
    }

    @Test
    public void testRollAndMove() throws PlayerNotFoundException, InvalidDiceException {
        gooseGame.addPlayer("Pippo");

        Slot newPippoSlot = gooseGame.movePlayer("Pippo");
        Assert.assertNotEquals("First Pippo's automatic movement failed", 0, newPippoSlot.getNumber());
    }

    @Test
    public void testBridge() throws PlayerNotFoundException, InvalidDiceException {
        gooseGame.addPlayer("Pippo");

        Slot newSlot = gooseGame.getBoard().getSlot(4);
        gooseGame.getPlayer("Pippo").setCurrentSlot(newSlot);

        Slot newPippoSlot = gooseGame.movePlayer("Pippo", 1, 1);
        Assert.assertEquals("The Bridge not working", 12, newPippoSlot.getNumber());
    }

    @Test
    public void testGooseSingle() throws PlayerNotFoundException, InvalidDiceException {
        gooseGame.addPlayer("Pippo");

        Slot newPippoSlot = gooseGame.movePlayer("Pippo", 2, 3);
        Assert.assertEquals("The Goose (single) not working", 10, newPippoSlot.getNumber());
    }

    @Test
    public void testGooseMultiple() throws PlayerNotFoundException, InvalidDiceException {
        gooseGame.addPlayer("Pippo");

        Slot newPippoSlot = gooseGame.movePlayer("Pippo", 4, 5);
        Assert.assertEquals("The Goose (multiple) not working", 36, newPippoSlot.getNumber());
    }

    @Test
    public void testPrank() throws PlayerNotFoundException, InvalidDiceException {
        gooseGame.addPlayer("Pippo");
        gooseGame.addPlayer("Pluto");

        gooseGame.getPlayer("Pippo").setCurrentSlot(gooseGame.getBoard().getSlot(19));
        gooseGame.getPlayer("Pluto").setCurrentSlot(gooseGame.getBoard().getSlot(4));

        gooseGame.movePlayer("Pluto", 2, 3);
        Assert.assertEquals("Prank not working",
                14,
                gooseGame.getPlayer("Pippo").getCurrentSlot().getNumber());
    }
}
